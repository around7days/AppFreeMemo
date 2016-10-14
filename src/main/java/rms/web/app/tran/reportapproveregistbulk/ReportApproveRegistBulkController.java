package rms.web.app.tran.reportapproveregistbulk;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import rms.common.auth.UserInfo;
import rms.common.base.BusinessException;
import rms.common.consts.MessageEnum;
import rms.common.consts.MessageTypeConst;
import rms.domain.app.tran.reportapproveregistbulk.ReportApproveRegistBulkDto;
import rms.domain.app.tran.reportapproveregistbulk.ReportApproveRegistBulkService;
import rms.web.app.tran.reportapprovelist.ReportApproveListController;

/**
 * 月報一括承認画面コントローラー
 * @author
 */
@Controller
@SessionAttributes(types = ReportApproveRegistBulkForm.class)
public class ReportApproveRegistBulkController extends rms.common.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApproveRegistBulkController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/reportApproveRegistBulk";

    /** マッピングURL */
    public static final String MAPPING_URL = "/tran/reportapproveregistbulk";

    /** 月報一括承認画面サービス */
    @Autowired
    ReportApproveRegistBulkService service;

    /**
     * 月報一括承認画面フォームの初期化
     * @return
     */
    @ModelAttribute
    ReportApproveRegistBulkForm setupForm() {
        return new ReportApproveRegistBulkForm();
    }

    /**
     * 初期表示処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "init")
    public String init(ReportApproveRegistBulkForm form,
                       Model model) {
        return PAGE_URL;
    }

    /**
     * 一括承認処理
     * @param form
     * @param bindingResult
     * @param userInfo
     * @param sessionStatus
     * @param model
     * @return
     * @throws IOException
     * @throws BusinessException
     */
    @RequestMapping(value = MAPPING_URL, params = "approveBulk")
    public String approveBulk(@Validated ReportApproveRegistBulkForm form,
                              BindingResult bindingResult,
                              @AuthenticationPrincipal UserInfo userInfo,
                              SessionStatus sessionStatus,
                              Model model) throws IOException, BusinessException {
        logger.debug("入力フォーム情報 -> {}", form);

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug("入力チェックエラー -> {}", bindingResult.getAllErrors());
            return PAGE_URL;
        }

        // 月報情報の一括承認処理
        List<ReportApproveRegistBulkDto> resultList = service.approveBulk(form.getFile(), userInfo);

        // 実行結果の反映
        form.setResultList(resultList);

        // 完了メッセージ
        model.addAttribute(MessageTypeConst.SUCCESS, message.getMessage(MessageEnum.info005));
        // セッション破棄
        sessionStatus.setComplete();

        return PAGE_URL;
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
    //
    // // ----------------------------------------------------------------------------------------
    // /**
    // * 業務エラー（BusinessException）のエラーハンドリング
    // * @param e
    // * @param session
    // * @param model
    // * @return
    // */
    // @ExceptionHandler(BusinessException.class)
    // public String handlerException(BusinessException e,
    // HttpSession session,
    // Model model) {
    // logger.debug("業務エラー -> {}", e.getErrorMessage());
    //
    // // メッセージを反映
    // model.addAttribute(MessageConst.ERROR, e.getErrorMessage());
    // // セッションからフォーム情報を取得して反映
    // model.addAttribute(SessionUtils.getSessionForm(session, ReportApproveRegistBulkForm.class));
    //
    // return PAGE_URL;
    // }
    //
    // /**
    // * 楽観的排他制御（OptimisticLockException）のエラーハンドリング
    // * @param e
    // * @param session
    // * @param model
    // * @return
    // */
    // @ExceptionHandler(OptimisticLockException.class)
    // public String handlerException(OptimisticLockException e,
    // HttpSession session,
    // Model model) {
    // logger.debug("楽観的排他制御エラー -> {}", e.getMessage());
    //
    // // メッセージとフォーム情報を反映
    // model.addAttribute(MessageTypeConst.ERROR,
    // message.getMessage(MessageEnum.error002.name(), null, Locale.getDefault()));
    // // セッションからフォーム情報を取得して反映
    // model.addAttribute(SessionUtils.getSessionForm(session, ReportApproveRegistBulkForm.class));
    //
    // return PAGE_URL;
    // }
    //
    // // ----------------------------------------------------------------------------------------

}
