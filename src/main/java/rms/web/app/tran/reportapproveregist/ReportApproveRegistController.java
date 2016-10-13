package rms.web.app.tran.reportapproveregist;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.seasar.doma.jdbc.OptimisticLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import rms.common.auth.UserInfo;
import rms.common.base.BusinessException;
import rms.common.consts.MessageTypeConst;
import rms.common.utils.BeanUtils;
import rms.common.utils.FileUtils;
import rms.common.utils.SessionUtils;
import rms.domain.app.shared.dto.ReportFileDto;
import rms.domain.app.shared.service.SharedReportFileService;
import rms.domain.app.tran.reportapproveregist.ReportApproveRegistDto;
import rms.domain.app.tran.reportapproveregist.ReportApproveRegistService;
import rms.web.app.system.menu.MenuController;
import rms.web.app.tran.reportapprovelist.ReportApproveListController;
import rms.web.app.tran.reportapproveregist.ReportApproveRegistForm.Approve;

/**
 * 月報承認画面コントローラー
 * @author
 */
@Controller
@SessionAttributes(types = ReportApproveRegistForm.class)
public class ReportApproveRegistController extends rms.common.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApproveRegistController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/reportApproveRegist";

    /** マッピングURL */
    public static final String MAPPING_URL = "/tran/reportapproveregist";

    /** 月報承認画面サービス */
    @Autowired
    ReportApproveRegistService service;

    /** 月報ファイル関連共通サービス */
    @Autowired
    SharedReportFileService sharedReportFileService;

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
     * @param userInfo
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL + "/{applyUserId}/{targetYm}", params = "init")
    public String init(ReportApproveRegistForm form,
                       @PathVariable String applyUserId,
                       @PathVariable Integer targetYm,
                       @AuthenticationPrincipal UserInfo userInfo,
                       Model model) {
        // 月報情報の取得
        ReportApproveRegistDto dto = service.getReportInfo(applyUserId, targetYm);

        // 値を反映
        BeanUtils.copyProperties(dto, form);

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
     * @throws BusinessException
     */
    @RequestMapping(value = MAPPING_URL, params = "approve")
    public String approve(@Validated(Approve.class) ReportApproveRegistForm form,
                          BindingResult bindingResult,
                          @AuthenticationPrincipal UserInfo userInfo,
                          SessionStatus sessionStatus,
                          RedirectAttributes redirectAttr,
                          Model model) throws IOException, BusinessException {
        logger.debug("入力フォーム情報 -> {}", form);

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug("入力チェックエラー -> {}", bindingResult.getAllErrors());
            return PAGE_URL;
        }

        // 承認情報の生成
        ReportApproveRegistDto dto = BeanUtils.createCopyProperties(form, ReportApproveRegistDto.class);

        // 承認処理の実行
        service.approve(dto);

        // 完了メッセージ
        redirectAttr.addFlashAttribute(MessageTypeConst.SUCCESS, message.getMessage("info.005", null, Locale.getDefault()));
        // セッション破棄
        sessionStatus.setComplete();

        return redirect(MenuController.MAPPING_URL);
    }

    /**
     * 否認処理
     * @param form
     * @param sessionStatus
     * @param redirectAttr
     * @param model
     * @return
     * @throws IOException
     * @throws BusinessException
     */
    @RequestMapping(value = MAPPING_URL, params = "deny")
    public String deny(ReportApproveRegistForm form,
                       SessionStatus sessionStatus,
                       RedirectAttributes redirectAttr,
                       Model model) throws IOException, BusinessException {
        logger.debug("入力フォーム情報 -> {}", form);

        // 否認情報の生成
        ReportApproveRegistDto dto = BeanUtils.createCopyProperties(form, ReportApproveRegistDto.class);

        // 否認処理の実行
        service.deny(dto);

        // 完了メッセージ
        redirectAttr.addFlashAttribute(MessageTypeConst.SUCCESS, message.getMessage("info.006", null, Locale.getDefault()));
        // セッション破棄
        sessionStatus.setComplete();

        return redirect(MenuController.MAPPING_URL);
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

        // 月報ファイルダウンロード情報生成
        ReportFileDto dto = sharedReportFileService.getReportFileDownloadInfo(form.getApplyUserId(),
                                                                              form.getApplyUserNm(),
                                                                              form.getTargetYm());
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
        return redirect(ReportApproveListController.MAPPING_URL, "reSearch");
    }

    // ----------------------------------------------------------------------------------------
    /**
     * 業務エラー（BusinessException）のエラーハンドリング
     * @param e
     * @param session
     * @param model
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public String handlerException(BusinessException e,
                                   HttpSession session,
                                   Model model) {
        logger.debug("業務エラー -> {}", e.getErrorMessage());

        // メッセージを反映
        model.addAttribute(MessageTypeConst.ERROR, e.getErrorMessage());
        // セッションからフォーム情報を取得して反映
        model.addAttribute(SessionUtils.getSessionForm(session, ReportApproveRegistForm.class));

        return PAGE_URL;
    }

    /**
     * 楽観的排他制御（OptimisticLockException）のエラーハンドリング
     * @param e
     * @param session
     * @param model
     * @return
     */
    @ExceptionHandler(OptimisticLockException.class)
    public String handlerException(OptimisticLockException e,
                                   HttpSession session,
                                   Model model) {
        logger.debug("楽観的排他制御エラー -> {}", e.getMessage());

        // メッセージとフォーム情報を反映
        model.addAttribute(MessageTypeConst.ERROR, message.getMessage("error.002", null, Locale.getDefault()));
        // セッションからフォーム情報を取得して反映
        model.addAttribute(SessionUtils.getSessionForm(session, ReportApproveRegistForm.class));

        return PAGE_URL;
    }

    // ----------------------------------------------------------------------------------------

}
