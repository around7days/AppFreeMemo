package rms.web.app.tran.reportlist;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import rms.common.dto.SearchResultDto;
import rms.common.utils.BeanUtils;
import rms.common.utils.FileUtils;
import rms.common.utils.PageInfo;
import rms.domain.app.shared.dto.ReportFileDto;
import rms.domain.app.shared.service.SharedReportFileService;
import rms.domain.app.tran.reportlist.ReportListDtoCondition;
import rms.domain.app.tran.reportlist.ReportListEntityResult;
import rms.domain.app.tran.reportlist.ReportListService;
import rms.web.app.system.menu.MenuController;

/**
 * 月報一覧画面コントローラー
 * @author
 */
@Controller
@SessionAttributes(types = ReportListForm.class)
public class ReportListController extends rms.common.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportListController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/reportList";

    /** マッピングURL */
    public static final String MAPPING_URL = "/tran/reportlist";

    /** 月報一覧画面サービス */
    @Autowired
    ReportListService service;

    /** 月報ファイル関連共通サービス */
    @Autowired
    SharedReportFileService sharedReportFileService;

    /**
     * 月報一覧画面フォームの初期化
     * @return
     */
    @ModelAttribute
    ReportListForm setupForm() {
        return new ReportListForm();
    }

    /**
     * 初期処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "init")
    public String init(ReportListForm form,
                       Model model) {
        return PAGE_URL;
    }

    /**
     * 検索処理
     * @param form
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "search")
    public String search(@Validated ReportListForm form,
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
        ReportListDtoCondition condition = BeanUtils.createCopyProperties(form.getCondition(),
                                                                          ReportListDtoCondition.class);

        // 検索処理
        SearchResultDto<ReportListEntityResult> resultDto = service.search(condition, form.getPageInfo());
        if (resultDto.getResultList().isEmpty()) {
            // 検索結果が見つかりません
            bindingResult.reject("error.006", message.getMessage("error.006", null, Locale.getDefault()));
            return PAGE_URL;
        }

        // 検索結果をフォームに反映
        form.setResultList(resultDto.getResultList());
        form.getPageInfo().setTotalSize(resultDto.getCount());

        return PAGE_URL;
    }

    /**
     * 再検索処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "reSearch")
    public String reSearch(ReportListForm form,
                           Model model) {
        logger.debug("フォーム情報 -> {}", form);

        // 検索条件の生成
        ReportListDtoCondition condition = BeanUtils.createCopyProperties(form.getCondition(),
                                                                          ReportListDtoCondition.class);

        // 検索処理
        SearchResultDto<ReportListEntityResult> resultDto = service.search(condition, form.getPageInfo());

        // 検索結果をフォームに反映
        form.setResultList(resultDto.getResultList());
        form.getPageInfo().setTotalSize(resultDto.getCount());

        return PAGE_URL;
    }

    /**
     * 前ページング処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "pagePrev")
    public String pagePrev(ReportListForm form,
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
    public String pageNext(ReportListForm form,
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
    public String download(ReportListForm form,
                           @PathVariable int index,
                           HttpServletResponse response,
                           Model model) throws IOException {
        logger.debug("選択値 -> {}", index);

        // 選択した月報情報
        ReportListEntityResult entity = form.getResultList().get(index);
        logger.debug("選択月報情報 -> {}", entity);

        // 月報ファイルダウンロード情報生成
        ReportFileDto dto = sharedReportFileService.createReportFileDownloadInfo(entity.getApplyUserId(),
                                                                                 entity.getApplyUserNm(),
                                                                                 entity.getTargetYm());
        // 月報ダウンロード
        FileUtils.fileDownload(response, dto.getFilePath(), dto.getFileNm());

        return null;
    }

    /**
     * 戻る処理
     * @param sessionStatus
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "back")
    public String back(SessionStatus sessionStatus) {
        // セッション破棄
        sessionStatus.setComplete();
        return redirect(MenuController.MAPPING_URL);
    }

}
