package rms.web.app.tran.reportapplylist;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import rms.common.auth.UserInfo;
import rms.common.consts.MRoleConst;
import rms.common.dto.SearchResultDto;
import rms.common.utils.FileUtils;
import rms.common.utils.PageInfo;
import rms.domain.app.shared.dto.ReportFileDto;
import rms.domain.app.shared.service.SharedReportFileService;
import rms.domain.app.tran.reportapplylist.ReportApplyListDtoCondition;
import rms.domain.app.tran.reportapplylist.ReportApplyListEntityResult;
import rms.domain.app.tran.reportapplylist.ReportApplyListService;
import rms.web.app.system.menu.MenuController;
import rms.web.app.tran.reportapplyregist.ReportApplyRegistController;

/**
 * 月報申請状況一覧画面コントローラー<br>
 * 役割：管理者、申請者
 * @author
 */
@Controller
@SessionAttributes(types = ReportApplyListForm.class)
@Secured(value = { MRoleConst.ADMIN, MRoleConst.APPLY })
public class ReportApplyListController extends rms.common.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApplyListController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/reportApplyList";

    /** マッピングURL */
    public static final String MAPPING_URL = "/tran/reportapplylist";

    /** 画面ID */
    public static final String SCREEN_ID = "T003";

    /** 月報申請状況一覧画面サービス */
    @Autowired
    private ReportApplyListService service;

    /** 月報ファイル関連共通サービス */
    @Autowired
    private SharedReportFileService sharedReportFileService;

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

        return urlHelper.redirect(MAPPING_URL, "search");
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
        ReportApplyListDtoCondition condition = new ReportApplyListDtoCondition();
        condition.setApplyUserId(userInfo.getUserId());

        // 検索処理
        SearchResultDto<ReportApplyListEntityResult> resultDto = service.search(condition, form.getPageInfo());

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
    public String pagePrev(ReportApplyListForm form,
                           Model model) {
        // ページング設定
        form.getPageInfo().prev();

        return urlHelper.redirect(MAPPING_URL, "search");
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

        return urlHelper.redirect(MAPPING_URL, "search");
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
        ReportApplyListEntityResult entity = form.getResultList().get(index);
        logger.debug("選択月報情報 -> {}", entity);

        // 月報ファイルダウンロード情報生成
        ReportFileDto dto = sharedReportFileService.getReportFileDownloadInfo(entity.getApplyUserId(),
                                                                              entity.getApplyUserNm(),
                                                                              entity.getTargetYm());
        // 月報ダウンロード
        FileUtils.fileDownload(response, dto.getFilePath(), dto.getFileNm());

        return null;
    }

    /**
     * 月報選択処理
     * @param form
     * @param index
     * @param attributes
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL + "/{index}", params = "select")
    public String select(ReportApplyListForm form,
                         @PathVariable int index,
                         RedirectAttributes attributes,
                         Model model) {
        logger.debug("選択値 -> {}", index);

        // 選択した月報情報
        ReportApplyListEntityResult entity = form.getResultList().get(index);
        logger.debug("選択月報情報 -> {}", entity);

        // 月報申請画面へ遷移
        attributes.addFlashAttribute("applyUserId", entity.getApplyUserId());
        attributes.addFlashAttribute("targetYm", entity.getTargetYm());
        return urlHelper.redirect(ReportApplyRegistController.MAPPING_URL, "initReApply");
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
        return urlHelper.redirect(MenuController.MAPPING_URL);
    }

    /*
     * (非 Javadoc)
     * @see rms.common.abstracts.AbstractController#getScreenId()
     */
    @Override
    protected String getScreenId() {
        return SCREEN_ID;
    }
}
