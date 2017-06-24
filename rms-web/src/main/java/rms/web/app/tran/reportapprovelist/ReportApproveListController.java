package rms.web.app.tran.reportapprovelist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import rms.common.auth.UserInfo;
import rms.common.consts.Const.ReportNmPattern;
import rms.common.consts.MRoleConst;
import rms.common.consts.MessageEnum;
import rms.common.consts.MessageTypeConst;
import rms.common.dto.SearchResultDto;
import rms.common.utils.PageInfo;
import rms.common.utils.RmsBeanUtils;
import rms.common.utils.RmsFileUtils;
import rms.domain.app.shared.dto.SharedFileDto;
import rms.domain.app.shared.dto.SharedSubmitReportFileDto;
import rms.domain.app.shared.service.SharedReportFileService;
import rms.domain.app.tran.reportapprovelist.ReportApproveListDtoCondition;
import rms.domain.app.tran.reportapprovelist.ReportApproveListEntityResult;
import rms.domain.app.tran.reportapprovelist.ReportApproveListService;
import rms.web.app.system.menu.MenuController;
import rms.web.app.tran.reportapprovelist.ReportApproveListForm.BulkDownload;
import rms.web.app.tran.reportapprovelist.ReportApproveListForm.Search;
import rms.web.app.tran.reportapproveregist.ReportApproveRegistController;
import rms.web.app.tran.reportapproveregistbulk.ReportApproveRegistBulkController;

/**
 * 月報承認状況一覧画面コントローラー
 * 役割：管理者、承認者
 * @author
 */
@Controller
@SessionAttributes(types = ReportApproveListForm.class)
@Secured(value = { MRoleConst.ADMIN, MRoleConst.APPROVE })
public class ReportApproveListController extends rms.common.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApproveListController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/reportApproveList";

    /** マッピングURL */
    public static final String MAPPING_URL = "/tran/reportapprovelist";

    /** 画面ID */
    public static final String SCREEN_ID = "T006";

    /** 月報情報取得サービス */
    @Autowired
    private ReportApproveListService service;

    /** 月報ファイル関連共通サービス */
    @Autowired
    private SharedReportFileService sharedReportFileService;

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
        // 初期表示用情報を取得
        ReportApproveListDtoCondition dto = service.initDisplay();

        // 値を設定
        RmsBeanUtils.copyProperties(dto, form.getCondition());

        logger.debug("出力フォーム情報 -> {}", form);

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
    public String search(@Validated(Search.class) ReportApproveListForm form,
                         BindingResult bindingResult,
                         @AuthenticationPrincipal UserInfo userInfo,
                         Model model) {
        logger.debug("入力フォーム情報 -> {}", form);

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug("入力チェックエラー -> {}", bindingResult.getAllErrors());
            return PAGE_URL;
        }

        // 検索結果・ページ情報の初期化
        int pageLimit = Integer.MAX_VALUE; // 件数を無制限に設定
        form.setPageInfo(new PageInfo(pageLimit));
        form.setResultList(null);

        // 検索条件の生成
        ReportApproveListDtoCondition condition = new ReportApproveListDtoCondition();
        RmsBeanUtils.copyProperties(form.getCondition(), condition);
        condition.setApproveUserId(userInfo.getUserId());

        // 検索処理
        SearchResultDto<ReportApproveListEntityResult> resultDto = service.search(condition, form.getPageInfo());
        if (resultDto.getResultList().isEmpty()) {
            // 「検索結果が見つかりません」
            model.addAttribute(MessageTypeConst.ERROR, message.getMessage(MessageEnum.error006));
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
     * @param userInfo
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "reSearch")
    public String reSearch(ReportApproveListForm form,
                           @AuthenticationPrincipal UserInfo userInfo,
                           Model model) {
        logger.debug("フォーム情報 -> {}", form);

        if (form.getPageInfo().getTotalSize() == 0) {
            // 検索実績なしのため、再検索を行わない
            return PAGE_URL;
        }

        // 検索条件の生成
        ReportApproveListDtoCondition condition = new ReportApproveListDtoCondition();
        RmsBeanUtils.copyProperties(form.getCondition(), condition);
        condition.setApproveUserId(userInfo.getUserId());

        // 検索処理
        SearchResultDto<ReportApproveListEntityResult> resultDto = service.search(condition, form.getPageInfo());

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
    public String pagePrev(ReportApproveListForm form,
                           Model model) {
        // ページング設定
        form.getPageInfo().prev();

        return urlHelper.redirect(MAPPING_URL, "reSearch");
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

        return urlHelper.redirect(MAPPING_URL, "reSearch");
    }

    /**
     * 一括承認ボタン押下処理
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "bulkApprove")
    public String bulkApprove() {
        return urlHelper.redirect(ReportApproveRegistBulkController.MAPPING_URL, "init");
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
        ReportApproveListEntityResult entity = form.getResultList().get(index);
        logger.debug("選択月報情報 -> {}", entity);

        // 月報ファイルダウンロード情報生成
        SharedFileDto dto = sharedReportFileService.getReportFileInfo(entity.getApplyUserId(),
                                                                      entity.getApplyUserNm(),
                                                                      entity.getTargetYm());
        // 月報ダウンロード
        RmsFileUtils.fileDownload(response, dto.getFilePath(), dto.getFileNm());

        return null;
    }

    /**
     * 月報一括DL処理
     * @param form
     * @param bindingResult
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = MAPPING_URL, params = "bulkDownload")
    public String bulkDownload(@Validated(BulkDownload.class) ReportApproveListForm form,
                               BindingResult bindingResult,
                               HttpServletResponse response,
                               Model model) throws IOException {
        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug("入力チェックエラー -> {}", bindingResult.getAllErrors());
            return PAGE_URL;
        }

        // ダウンロードする月報情報リストの生成
        List<SharedSubmitReportFileDto> list = new ArrayList<>();

        // 選択した月報indexの取得
        Integer[] checks = form.getReportDLCheck();
        for (int i : checks) {
            ReportApproveListEntityResult entity = form.getResultList().get(i);

            SharedSubmitReportFileDto dto = new SharedSubmitReportFileDto();
            dto.setApplyUserId(entity.getApplyUserId());
            dto.setTargetYm(entity.getTargetYm());

            list.add(dto);
        }

        // 月報一括ダウンロードファイルの生成
        SharedFileDto dto = sharedReportFileService.createReportFileBulk(list, ReportNmPattern.NOMAL);
        // 月報ダウンロード
        RmsFileUtils.fileDownload(response, dto.getFilePath(), dto.getFileNm());

        return null;
    }

    /**
     * 選択処理
     * @param form
     * @param index
     * @param attributes
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL + "/{index}", params = "select")
    public String select(ReportApproveListForm form,
                         @PathVariable int index,
                         RedirectAttributes attributes,
                         Model model) {
        logger.debug("選択値 -> {}", index);

        // 選択した月報情報
        ReportApproveListEntityResult entity = form.getResultList().get(index);
        logger.debug("選択月報情報 -> {}", entity);

        // 月報承認画面に遷移
        attributes.addFlashAttribute("applyUserId", entity.getApplyUserId());
        attributes.addFlashAttribute("targetYm", entity.getTargetYm());
        return urlHelper.redirect(ReportApproveRegistController.MAPPING_URL, "init");
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
