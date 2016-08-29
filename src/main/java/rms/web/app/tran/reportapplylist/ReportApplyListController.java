package rms.web.app.tran.reportapplylist;

import java.io.IOException;
import java.nio.file.Path;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import rms.common.auth.UserInfo;
import rms.common.utils.FileUtils;
import rms.common.utils.PageInfo;
import rms.domain.app.shared.entity.SearchResultEntity;
import rms.domain.app.tran.reportapplylist.ReportApplyListEntityCondition;
import rms.domain.app.tran.reportapplylist.ReportApplyListEntityResult;
import rms.domain.app.tran.reportapplylist.ReportApplyListService;
import rms.web.app.tran.reportapplyregist.ReportApplyRegistController;

/**
 * 月報申請状況一覧画面コントローラー
 * @author
 */
@Controller
@Transactional(rollbackFor = Exception.class)
@SessionAttributes(types = ReportApplyListForm.class)
public class ReportApplyListController extends rms.common.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApplyListController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/reportApplyList";

    /** マッピングURL */
    public static final String MAPPING_URL = "/tran/report/apply/list";

    /** 月報申請状況一覧画面サービス */
    @Autowired
    ReportApplyListService service;

    /**
     * 月報承認状況一覧画面フォームの初期化
     * @return
     */
    @ModelAttribute
    ReportApplyListForm setupForm() {
        return new ReportApplyListForm();
    }

    /**
     * 初期処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "init")
    public String initInsert(ReportApplyListForm form,
                             Model model) {
        // 検索結果・ページ情報の初期化
        form.setPageInfo(new PageInfo());
        form.setResultList(null);

        return redirect(MAPPING_URL, "search");
    }

    /**
     * 検索処理
     * @param form
     * @param userInfo
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "search")
    public String search(ReportApplyListForm form,
                         @AuthenticationPrincipal UserInfo userInfo,
                         Model model) {
        logger.debug("入力フォーム情報 -> {}", form);

        // 検索条件の生成
        ReportApplyListEntityCondition condition = new ReportApplyListEntityCondition();
        condition.setApplyUserId(userInfo.getUserId());

        // 検索処理
        SearchResultEntity<ReportApplyListEntityResult> resultEntity = service.search(condition, form.getPageInfo());

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
    public String pagePrev(ReportApplyListForm form,
                           Model model) {
        // ページング設定
        form.getPageInfo().prev();

        return redirect(MAPPING_URL, "search");
    }

    /**
     * 次ページング処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "pageNext")
    public String pageNext(ReportApplyListForm form,
                           Model model) {
        // ページング設定
        form.getPageInfo().next();

        return redirect(MAPPING_URL, "search");
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
    public String download(ReportApplyListForm form,
                           @PathVariable int index,
                           HttpServletResponse response,
                           Model model) throws IOException {
        logger.debug("選択値 -> {}", index);

        // 選択した月報情報
        ReportApplyListEntityResult result = form.getResultList().get(index);
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

    /**
     * 月報選択処理
     * @param form
     * @param index
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL + "/{index}", params = "select")
    public String select(ReportApplyListForm form,
                         @PathVariable int index,
                         Model model) {
        logger.debug("選択値 -> {}", index);

        // 選択した月報情報
        ReportApplyListEntityResult result = form.getResultList().get(index);
        logger.debug("選択月報情報 -> {}", result);

        // 月報申請画面
        return redirect(ReportApplyRegistController.MAPPING_URL + "/" + result.getApplyUserId() + "/"
                        + result.getTargetYm(), "initUpdate");
    }

}
