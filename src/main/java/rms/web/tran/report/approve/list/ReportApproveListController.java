package rms.web.tran.report.approve.list;

import java.io.IOException;
import java.nio.file.Path;

import rms.domain.tran.report.entity.ReportApproveListConditionEntity;
import rms.domain.tran.report.entity.ReportResultEntity;
import rms.domain.tran.report.service.ReportSelectService;
import rms.web.base.SearchResultEntity;
import rms.web.base.UserInfo;
import rms.web.com.utils.FileUtils;
import rms.web.com.utils.PageInfo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

/**
 * 月報承認状況一覧画面コントローラー
 * @author
 */
@Controller
@Transactional(rollbackFor = Exception.class)
@SessionAttributes(types = ReportApproveListForm.class)
public class ReportApproveListController extends rms.web.com.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApproveListController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/reportApproveList";

    /** マッピングURL */
    public static final String MAPPING_URL = "/tran/report/approve/list";

    /** 月報情報取得サービス */
    @Autowired
    ReportSelectService reportSelectService;

    /**
     * 月報承認状況一覧画面フォームの初期化
     * @return
     */
    @ModelAttribute
    ReportApproveListForm setupForm() {
        return new ReportApproveListForm();
    }

    /**
     * 初期処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "init")
    public String init(ReportApproveListForm form,
                       Model model) {
        return PAGE_URL;
    }

    /**
     * 検索処理
     * @param form
     * @param userInfo
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "search")
    public String search(@Validated ReportApproveListForm form,
                         @AuthenticationPrincipal UserInfo userInfo,
                         BindingResult bindingResult,
                         Model model) {
        logger.debug("入力フォーム情報 -> {}", form);

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug("入力チェックエラー -> {}", bindingResult.getAllErrors());
            return PAGE_URL;
        }

        // 検索結果・ページ情報の初期化
        form.setPageInfo(new PageInfo());
        form.setResultList(null);

        // 検索条件の生成
        ReportApproveListConditionEntity condition = new ReportApproveListConditionEntity();
        BeanUtils.copyProperties(form.getCondition(), condition);
        condition.setApproveUserId(userInfo.getUserId());

        // 検索処理
        SearchResultEntity<ReportResultEntity> resultEntity = reportSelectService.getReportApproveList(condition,
                                                                                                       form.getPageInfo());
        if (resultEntity.getResultList().isEmpty()) {
            bindingResult.reject("", "検索結果は存在しません");
            return PAGE_URL;
        }

        // 検索結果をフォームに反映
        form.setResultList(resultEntity.getResultList());
        form.getPageInfo().setTotalSize(resultEntity.getCount());

        return PAGE_URL;
    }

    /**
     * 再検索処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "reSearch")
    public String reSearch(ReportApproveListForm form,
                           Model model) {
        logger.debug("フォーム情報 -> {}", form);

        // 検索条件の生成
        ReportApproveListConditionEntity condition = new ReportApproveListConditionEntity();
        BeanUtils.copyProperties(form.getCondition(), condition);

        // 検索処理
        SearchResultEntity<ReportResultEntity> resultEntity = reportSelectService.getReportApproveList(condition,
                                                                                                       form.getPageInfo());

        // 検索結果をフォームに反映
        form.setResultList(resultEntity.getResultList());
        form.getPageInfo().setTotalSize(resultEntity.getCount());

        return PAGE_URL;
    }

    /**
     * 前ページング処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "pagePrev")
    public String pagePrev(ReportApproveListForm form,
                           Model model) {
        // ページング設定
        form.getPageInfo().prev();

        return redirect(MAPPING_URL, "reSearch");
    }

    /**
     * 次ページング処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "pageNext")
    public String pageNext(ReportApproveListForm form,
                           Model model) {
        // ページング設定
        form.getPageInfo().next();

        return redirect(MAPPING_URL, "reSearch");
    }

    /**
     * 月報DL処理
     * @param form
     * @param index
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = MAPPING_URL + "/{index}", params = "download")
    public String download(ReportApproveListForm form,
                           @PathVariable int index,
                           HttpServletResponse response,
                           Model model) throws IOException {
        logger.debug("選択値 -> {}", index);

        // 選択した月報情報
        ReportResultEntity result = form.getResultList().get(index);
        logger.debug("選択月報情報 -> {}", result);

        /*
         * ファイルダウンロード処理
         */
        // ダウンロードファイルパスの生成
        Path filePath = FileUtils.createReportFilePath(properties.getString("myapp.report.storage"),
                                                       result.getApplyUserId(),
                                                       result.getTargetYm());
        // 月報ダウンロード
        FileUtils.reportDownload(response, filePath);

        return null;
    }
}
