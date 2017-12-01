package rms.web.app.tran.reportapplyregist;

import java.io.IOException;

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
import rms.domain.app.tran.reportapplyregist.ReportApplyRegistDto;
import rms.domain.app.tran.reportapplyregist.ReportApplyRegistService;
import rms.web.app.system.menu.MenuController;
import rms.web.app.tran.reportapplyregist.ReportApplyRegistForm.Apply;
import rms.web.app.tran.reportapplyregist.ReportApplyRegistForm.ReApply;

/**
 * 月報申請画面コントローラー
 * 役割：管理者、申請者
 * @author
 */
@Controller
@SessionAttributes(types = ReportApplyRegistForm.class)
@Secured(value = { MRoleConst.ADMIN, MRoleConst.APPLY })
public class ReportApplyRegistController extends rms.common.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApplyRegistController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/reportApplyRegist";

    /** マッピングURL */
    public static final String MAPPING_URL = "/tran/reportapplyregist";

    /** 画面ID */
    public static final String SCREEN_ID = "T002";

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
     * 初期表示処理（申請時）
     * @param form
     * @param userInfo
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "initApply")
    public String initApply(ReportApplyRegistForm form,
                            @AuthenticationPrincipal UserInfo userInfo,
                            Model model) {
        // 申請者の初期表示用情報を取得
        ReportApplyRegistDto dto = service.initDisplayApply(userInfo.getUserId());

        // 値を設定
        RmsBeanUtils.copyProperties(dto, form);

        // 画面表示モードを「申請」に設定
        form.setViewMode(ReportApplyRegistForm.VIEW_MODE_APPLY);

        logger.debug("出力フォーム情報 -> {}", form);

        return PAGE_URL;
    }

    /**
     * 初期表示処理（再申請時）
     * @param form
     * @param applyUserId
     * @param targetYm
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "initReApply")
    public String initReApply(ReportApplyRegistForm form,
                              @ModelAttribute("applyUserId") String applyUserId,
                              @ModelAttribute("targetYm") Integer targetYm,
                              Model model) {
        // 申請者の月報情報を取得
        ReportApplyRegistDto dto = service.initDisplayReApply(applyUserId, targetYm);

        // 値を設定
        RmsBeanUtils.copyProperties(dto, form);

        // 画面表示モードを「再申請」に設定
        form.setViewMode(ReportApplyRegistForm.VIEW_MODE_REAPPLY);

        logger.debug("出力フォーム情報 -> {}", form);

        return PAGE_URL;
    }

    /**
     * 申請処理
     * @param form
     * @param bindingResult
     * @param sessionStatus
     * @param redirectAttr
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = MAPPING_URL, params = "apply")
    public String apply(@Validated(Apply.class) ReportApplyRegistForm form,
                        BindingResult bindingResult,
                        SessionStatus sessionStatus,
                        RedirectAttributes redirectAttr,
                        Model model) throws IOException {
        logger.debug("入力フォーム情報 -> {}", form);

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug("入力チェックエラー -> {}", bindingResult.getAllErrors());
            return PAGE_URL;
        }

        // 申請情報の生成
        ReportApplyRegistDto dto = RmsBeanUtils.createCopyProperties(form, ReportApplyRegistDto.class);

        try {
            // 申請処理
            service.apply(dto);
        } catch (BusinessException e) {
            // 業務エラー
            logger.debug("業務エラー -> {}", e.toString());
            model.addAttribute(MessageTypeConst.ERROR, e.getErrorMessage());
            return PAGE_URL;
        }

        // 完了メッセージ
        redirectAttr.addFlashAttribute(MessageTypeConst.SUCCESS, message.getMessage(MessageEnum.info004));
        // セッション破棄
        sessionStatus.setComplete();

        return urlHelper.redirect(MenuController.MAPPING_URL);
    }

    /**
     * 再申請処理
     * @param form
     * @param bindingResult
     * @param sessionStatus
     * @param redirectAttr
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = MAPPING_URL, params = "reApply")
    public String reApply(@Validated(ReApply.class) ReportApplyRegistForm form,
                          BindingResult bindingResult,
                          SessionStatus sessionStatus,
                          RedirectAttributes redirectAttr,
                          Model model) throws IOException {
        logger.debug("入力フォーム情報 -> {}", form);

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug("入力チェックエラー -> {}", bindingResult.getAllErrors());
            return PAGE_URL;
        }

        // 再申請情報の生成
        ReportApplyRegistDto dto = RmsBeanUtils.createCopyProperties(form, ReportApplyRegistDto.class);

        try {
            // 再申請処理
            service.reApply(dto);
        } catch (BusinessException e) {
            logger.debug("業務エラー -> {}", e.toString());
            model.addAttribute(MessageTypeConst.ERROR, e.getErrorMessage());
            return PAGE_URL;
        }

        // 完了メッセージ
        redirectAttr.addFlashAttribute(MessageTypeConst.SUCCESS, message.getMessage(MessageEnum.info004));
        // セッション破棄
        sessionStatus.setComplete();

        return urlHelper.redirect(MenuController.MAPPING_URL);
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
