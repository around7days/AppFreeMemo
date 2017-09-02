package rms.web.app.tran.reportapproveregist;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.seasar.doma.jdbc.OptimisticLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import rms.common.auth.UserInfo;
import rms.common.consts.MRoleConst;
import rms.common.consts.MessageEnum;
import rms.common.consts.MessageTypeConst;
import rms.common.exception.BusinessException;
import rms.common.utils.RmsBeanUtils;
import rms.common.utils.RmsFileUtils;
import rms.domain.app.shared.dto.SharedFileDto;
import rms.domain.app.shared.service.SharedReportFileService;
import rms.domain.app.tran.reportapproveregist.ReportApproveRegistDto;
import rms.domain.app.tran.reportapproveregist.ReportApproveRegistService;
import rms.web.app.system.menu.MenuController;
import rms.web.app.tran.reportapprovelist.ReportApproveListController;
import rms.web.app.tran.reportapproveregist.ReportApproveRegistForm.Approve;

/**
 * 月報承認画面コントローラー<br>
 * 役割：管理者、承認者
 * @author
 */
@Controller
@SessionAttributes(types = ReportApproveRegistForm.class)
@Secured(value = { MRoleConst.ADMIN, MRoleConst.APPROVE })
public class ReportApproveRegistController extends rms.common.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApproveRegistController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/reportApproveRegist";

    /** マッピングURL */
    public static final String MAPPING_URL = "/tran/reportapproveregist";

    /** 画面ID */
    public static final String SCREEN_ID = "S004";

    /** 月報承認画面サービス */
    @Autowired
    private ReportApproveRegistService service;

    /** 月報ファイル関連共通サービス */
    @Autowired
    private SharedReportFileService sharedReportFileService;

    /**
     * 月報承認画面フォームの初期化
     * @return
     */
    @ModelAttribute
    ReportApproveRegistForm setupForm() {
        return new ReportApproveRegistForm();
    }

    /**
     * 初期表示処理
     * @param form
     * @param applyUserId
     * @param targetYm
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "init")
    public String init(ReportApproveRegistForm form,
                       @ModelAttribute(value = "applyUserId") String applyUserId,
                       @ModelAttribute(value = "targetYm") Integer targetYm,
                       Model model) {
        // 月報情報の取得
        ReportApproveRegistDto dto = service.initDisplay(applyUserId, targetYm);

        // 値を反映
        RmsBeanUtils.copyProperties(dto, form);

        logger.debug("出力フォーム情報 -> {}", form);

        return PAGE_URL;
    }

    /**
     * 承認処理
     * @param userInfo
     * @param form
     * @param bindingResult
     * @param sessionStatus
     * @param redirectAttr
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = MAPPING_URL, params = "approve")
    public String approve(@Validated(Approve.class) ReportApproveRegistForm form,
                          BindingResult bindingResult,
                          @AuthenticationPrincipal UserInfo userInfo,
                          SessionStatus sessionStatus,
                          RedirectAttributes redirectAttr,
                          Model model) throws IOException {
        logger.debug("入力フォーム情報 -> {}", form);

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug("入力チェックエラー -> {}", bindingResult.getAllErrors());
            return PAGE_URL;
        }

        // 承認情報の生成
        ReportApproveRegistDto dto = RmsBeanUtils.createCopyProperties(form, ReportApproveRegistDto.class);

        try {
            // 承認処理の実行
            service.approve(dto);
        } catch (BusinessException e) {
            logger.debug("業務エラー -> {}", e.toString());
            model.addAttribute(MessageTypeConst.ERROR, e.getErrorMessage());
            return PAGE_URL;
        }

        // 完了メッセージ
        redirectAttr.addFlashAttribute(MessageTypeConst.SUCCESS, message.getMessage(MessageEnum.info005));
        // セッション破棄
        sessionStatus.setComplete();

        return urlHelper.redirect(MenuController.MAPPING_URL);
    }

    /**
     * 否認処理
     * @param form
     * @param sessionStatus
     * @param redirectAttr
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = MAPPING_URL, params = "deny")
    public String deny(ReportApproveRegistForm form,
                       SessionStatus sessionStatus,
                       RedirectAttributes redirectAttr,
                       Model model) throws IOException {
        logger.debug("入力フォーム情報 -> {}", form);

        // 否認情報の生成
        ReportApproveRegistDto dto = RmsBeanUtils.createCopyProperties(form, ReportApproveRegistDto.class);

        try {
            // 否認処理の実行
            service.deny(dto);
        } catch (BusinessException e) {
            logger.debug("業務エラー -> {}", e.toString());
            model.addAttribute(MessageTypeConst.ERROR, e.getErrorMessage());
            return PAGE_URL;
        }

        // 完了メッセージ
        redirectAttr.addFlashAttribute(MessageTypeConst.SUCCESS, message.getMessage(MessageEnum.info006));
        // セッション破棄
        sessionStatus.setComplete();

        return urlHelper.redirect(MenuController.MAPPING_URL);
    }

    /**
     * 月報DL処理
     * @param form
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = MAPPING_URL, params = "download")
    public String download(ReportApproveRegistForm form,
                           HttpServletResponse response,
                           Model model) throws IOException {

        try {
            // 月報ファイルダウンロード情報生成
            SharedFileDto dto = sharedReportFileService.getReportFileInfo(form.getApplyUserId(),
                                                                          form.getApplyUserNm(),
                                                                          form.getTargetYm());
            // 月報ダウンロード
            RmsFileUtils.fileDownload(response, dto.getFilePath(), dto.getFileNm());
        } catch (BusinessException e) {
            logger.debug("業務エラー -> {}", e.toString());
            model.addAttribute(MessageTypeConst.ERROR, e.getErrorMessage());
            return PAGE_URL;
        }

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
        return urlHelper.redirect(ReportApproveListController.MAPPING_URL, "reSearch");
    }

    /*
     * (非 Javadoc)
     * @see rms.common.abstracts.AbstractController#getScreenId()
     */
    @Override
    protected String getScreenId() {
        return SCREEN_ID;
    }

    // ----------------------------------------------------------------------------------------

    /**
     * 楽観的排他制御（OptimisticLockException）のエラーハンドリング
     * @param e
     * @param session
     * @param redirectAttr
     * @param model
     * @return
     */
    @ExceptionHandler(OptimisticLockException.class)
    public String handlerException(OptimisticLockException e,
                                   HttpSession session,
                                   RedirectAttributes redirectAttr,
                                   Model model) {
        logger.debug("楽観的排他制御エラー -> {}", e.getMessage());

        // エラーメッセージを設定
        redirectAttr.addAttribute(MessageTypeConst.ERROR, message.getMessage(MessageEnum.error002));

        // メニュー画面に戻る
        return urlHelper.forward(MenuController.MAPPING_URL);
    }

    // ----------------------------------------------------------------------------------------

}
