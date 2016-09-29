package rms.web.app.tran.reportapplyregist;

import java.io.IOException;
import java.util.Locale;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import rms.common.auth.UserInfo;
import rms.common.base.BusinessException;
import rms.common.consts.MCodeConst;
import rms.common.consts.MessageConst;
import rms.common.entity.VMUser;
import rms.common.utils.BeanUtils;
import rms.common.utils.SessionUtils;
import rms.domain.app.tran.reportapplyregist.ReportApplyRegistDto;
import rms.domain.app.tran.reportapplyregist.ReportApplyRegistService;
import rms.web.app.system.menu.MenuController;

/**
 * 月報申請画面コントローラー
 * @author
 */
@Controller
@SessionAttributes(types = ReportApplyRegistForm.class)
public class ReportApplyRegistController extends rms.common.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApplyRegistController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/reportApplyRegist";

    /** マッピングURL */
    public static final String MAPPING_URL = "/tran/reportapplyregist";

    /** 月報申請画面サービス */
    @Autowired
    ReportApplyRegistService service;

    /**
     * 月報申請画面フォームの初期化
     * @return
     */
    @ModelAttribute
    ReportApplyRegistForm setupForm() {
        return new ReportApplyRegistForm();
    }

    /**
     * 初期表示処理（新規時）
     * @param form
     * @param userInfo
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "initInsert")
    public String initInsert(ReportApplyRegistForm form,
                             @AuthenticationPrincipal UserInfo userInfo,
                             Model model) {
        // 申請者のユーザ情報を取得
        VMUser entity = service.getApplyUserInfo(userInfo.getUserId());

        // 値を設定
        BeanUtils.copyProperties(entity, form);
        // ユーザIDの設定
        form.setApplyUserId(userInfo.getUserId());
        // 初期値設定：公開有無を「公開」に設定
        form.setPublishFlg(MCodeConst.B001_1);
        // 画面表示モードを「新規」に設定
        form.setViewMode(ReportApplyRegistForm.VIEW_MODE_INSERT);

        logger.debug("出力フォーム情報 -> {}", form);

        return PAGE_URL;
    }

    //    /**
    //     * 初期表示処理（更新時）
    //     * @param form
    //     * @param applyUserId
    //     * @param targetYm
    //     * @param userInfo
    //     * @param model
    //     * @return
    //     */
    //    @RequestMapping(value = MAPPING_URL + "/{applyUserId}/{targetYm}", params = "initUpdate")
    //    public String initUpdate(ReportApplyRegistForm form,
    //                             @PathVariable String applyUserId,
    //                             @PathVariable String targetYm,
    //                             @AuthenticationPrincipal UserInfo userInfo,
    //                             Model model) {
    //        // 画面表示モードを「更新」に設定
    //        form.setViewMode(ReportApplyRegistForm.VIEW_MODE_UPDATE);
    //
    //        // 月報情報の取得
    //        VTReport vTReport = service.getReportInfo(applyUserId, targetYm);
    //
    //        // 値を設定
    //        BeanUtils.copyProperties(vTReport, form);
    //
    //        logger.debug("出力フォーム情報 -> {}", form);
    //
    //        return PAGE_URL;
    //    }

    /**
     * 申請処理
     * @param userInfo
     * @param form
     * @param bindingResult
     * @param sessionStatus
     * @param redirectAttr
     * @param model
     * @return
     * @throws BusinessException
     * @throws IOException
     * @throws NumberFormatException
     */
    @RequestMapping(value = MAPPING_URL, params = "insert")
    public String apply(@Validated(ReportApplyRegistForm.Insert.class) ReportApplyRegistForm form,
                        BindingResult bindingResult,
                        @AuthenticationPrincipal UserInfo userInfo,
                        SessionStatus sessionStatus,
                        RedirectAttributes redirectAttr,
                        Model model) throws NumberFormatException, IOException, BusinessException {
        logger.debug("入力フォーム情報 -> {}", form);

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug("入力チェックエラー -> {}", bindingResult.getAllErrors());
            return PAGE_URL;
        }

        // 申請情報の生成
        ReportApplyRegistDto entity = BeanUtils.createCopyProperties(form, ReportApplyRegistDto.class);

        // 申請処理
        service.apply(entity);

        // 完了メッセージ
        redirectAttr.addFlashAttribute(MessageConst.SUCCESS, message.getMessage("info.001", null, Locale.getDefault()));
        // セッション破棄
        sessionStatus.setComplete();

        return redirect(MenuController.MAPPING_URL);
    }

    /**
     * 戻る処理
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "back")
    public String back() {
        return redirect(MenuController.MAPPING_URL);
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
        model.addAttribute(MessageConst.ERROR, e.getErrorMessage());
        // セッションからフォーム情報を取得して反映
        model.addAttribute(SessionUtils.getSessionForm(session, ReportApplyRegistForm.class));

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
        model.addAttribute(MessageConst.ERROR, message.getMessage("error.002", null, Locale.getDefault()));
        // セッションからフォーム情報を取得して反映
        model.addAttribute(SessionUtils.getSessionForm(session, ReportApplyRegistForm.class));

        return PAGE_URL;
    }

    // ----------------------------------------------------------------------------------------

}
