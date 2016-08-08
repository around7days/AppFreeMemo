package rms.web.tran.report.approve.regist;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;

import rms.com.base.BusinessException;
import rms.com.consts.MessageConst;
import rms.domain.com.entity.TReport;
import rms.domain.com.entity.VTReport;
import rms.domain.mst.user.service.UserSelectService;
import rms.domain.tran.report.service.ReportRegistService;
import rms.domain.tran.report.service.ReportSelectService;
import rms.domain.tran.report.service.ReportValidateService;
import rms.web.base.UserInfo;
import rms.web.com.utils.FileUtils;
import rms.web.com.utils.SessionUtils;
import rms.web.menu.MenuController;
import rms.web.tran.report.approve.list.ReportApproveListController;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.seasar.doma.jdbc.OptimisticLockException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 月報承認画面コントローラー
 * @author
 */
@Controller
@Transactional(rollbackFor = Exception.class)
@SessionAttributes(types = ReportApproveRegistForm.class)
public class ReportApproveRegistController extends rms.web.com.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApproveRegistController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/reportApproveRegist";

    /** マッピングURL */
    public static final String MAPPING_URL = "/tran/report/approve/regist";

    /** ユーザ情報取得サービス */
    @Autowired
    UserSelectService userSelectService;

    /** 月報情報取得サービス */
    @Autowired
    ReportSelectService reportSelectService;

    /** 月報情報登録サービス */
    @Autowired
    ReportRegistService reportRegistService;

    /** 月報情報検証サービス */
    @Autowired
    ReportValidateService reportValidateService;

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
                       @PathVariable String targetYm,
                       @AuthenticationPrincipal UserInfo userInfo,
                       Model model) {
        // 月報情報の取得
        VTReport reportEntity = reportSelectService.getReportInfo(applyUserId, targetYm);

        /*
         * 値を設定
         */
        // 画面表示用
        BeanUtils.copyProperties(reportEntity, form);
        // 更新制御用
        form.setUpdateEntity(reportEntity);

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
     * @throws NumberFormatException
     */
    @RequestMapping(value = MAPPING_URL, params = "approve")
    public String approve(ReportApproveRegistForm form,
                          BindingResult bindingResult,
                          @AuthenticationPrincipal UserInfo userInfo,
                          SessionStatus sessionStatus,
                          RedirectAttributes redirectAttr,
                          Model model) throws IOException, NumberFormatException, BusinessException {
        logger.debug("入力フォーム情報 -> {}", form);

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug("入力チェックエラー -> {}", bindingResult.getAllErrors());
            return PAGE_URL;
        }

        /*
         * 業務ロジックチェック
         */

        /*
         * 承認処理
         */
        // 承認処理
        TReport entity = new TReport();
        BeanUtils.copyProperties(form.getUpdateEntity(), entity);
        reportRegistService.approve(entity);

        /*
         * 月報ファイル保存処理
         */
        // 月報保存ファイルパスの生成
        Path filePath = FileUtils.createReportFilePath(properties.getString("myapp.report.storage"),
                                                       userInfo.getUserId(),
                                                       form.getTargetYm());
        // 月報保存処理
        FileUtils.reportSave(form.getFile().getInputStream(), filePath);

        // 完了メッセージ
        redirectAttr.addFlashAttribute(MessageConst.SUCCESS, message.getMessage("info.001", null, Locale.getDefault()));
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
        /*
         * ファイルダウンロード処理
         */
        // ダウンロードファイルパスの生成
        Path filePath = FileUtils.createReportFilePath(properties.getString("myapp.report.storage"),
                                                       form.getApplyUserId(),
                                                       form.getTargetYm());
        // 月報ダウンロード
        FileUtils.reportDownload(response, filePath);

        return null;
    }

    /**
     * 戻る処理
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "back")
    public String back() {
        return redirect(ReportApproveListController.MAPPING_URL, "search");
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
        model.addAttribute(MessageConst.ERROR, message.getMessage("error.002", null, Locale.getDefault()));
        // セッションからフォーム情報を取得して反映
        model.addAttribute(SessionUtils.getSessionForm(session, ReportApproveRegistForm.class));

        return PAGE_URL;
    }

    // ----------------------------------------------------------------------------------------

}
