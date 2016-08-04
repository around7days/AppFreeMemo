package rms.web.tran.report.application.regist;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Locale;

import rms.com.base.BusinessException;
import rms.com.consts.MCodeConst;
import rms.com.consts.MessageConst;
import rms.domain.com.entity.TReport;
import rms.domain.mst.user.entity.UserEntity;
import rms.domain.mst.user.service.UserSelectService;
import rms.domain.tran.report.service.ReportRegistService;
import rms.domain.tran.report.service.ReportSelectService;
import rms.domain.tran.report.service.ReportValidateService;
import rms.web.base.UserInfo;
import rms.web.com.utils.FileUtils;
import rms.web.com.utils.SessionUtils;
import rms.web.menu.MenuController;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.seasar.doma.jdbc.OptimisticLockException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * 月報申請画面コントローラー
 * @author
 */
@Controller
@Transactional(rollbackFor = Exception.class)
@SessionAttributes(types = ReportApplicationRegistForm.class)
public class ReportApplicationRegistController extends rms.web.com.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApplicationRegistController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/reportApplicationRegist";

    /** マッピングURL */
    public static final String MAPPING_URL = "/tran/report/application/regist";

    /** 月報格納ベースディレクトリ */
    @Value("${myapp.report.storage}")
    private String reportStorage;

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
     * 月報申請画面フォームの初期化
     * @return
     */
    @ModelAttribute
    ReportApplicationRegistForm setupForm() {
        return new ReportApplicationRegistForm();
    }

    /**
     * 初期表示処理（新規時）
     * @param form
     * @param userInfo
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "initInsert")
    public String initInsert(ReportApplicationRegistForm form,
                             @AuthenticationPrincipal UserInfo userInfo,
                             Model model) {
        // 画面表示モードを「新規」に設定
        form.setViewMode(ReportApplicationRegistForm.VIEW_MODE_INSERT);
        // 公開有無を「公開」に設定
        form.setPublishFlg(MCodeConst.B001_1);

        // 申請者のユーザ情報を取得
        UserEntity userEntity = userSelectService.getUserInfo(userInfo.getUserId());

        // 値を設定
        BeanUtils.copyProperties(userEntity.getUser(), form);

        logger.debug("出力フォーム情報 -> {}", form);

        return PAGE_URL;
    }

    //    /**
    //     * 初期表示処理（更新時）
    //     * @param form
    //     * @param model
    //     * @return
    //     */
    //    @RequestMapping(value = MAPPING_URL, params = "initUpdate")
    //    public String initUpdate(ReportApplicationRegistForm form,
    //                             Model model) {
    //        form.setViewMode(ReportApplicationRegistForm.VIEW_MODE_UPDATE);
    //        return PAGE_URL;
    //    }

    /**
     * 新規登録処理
     * @param userInfo
     * @param form
     * @param bindingResult
     * @param redirectAttr
     * @param model
     * @return
     * @throws IOException
     * @throws BusinessException
     * @throws NumberFormatException
     */
    @RequestMapping(value = MAPPING_URL, params = "insert")
    public String insert(@Validated(ReportApplicationRegistForm.Insert.class) ReportApplicationRegistForm form,
                         BindingResult bindingResult,
                         @AuthenticationPrincipal UserInfo userInfo,
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
        // 月報の重複チェック
        reportValidateService.validateUniquReport(userInfo.getUserId(), Integer.valueOf(form.getTargetYm()));

        /*
         * 登録処理
         */
        // 登録情報の生成
        TReport entity = new TReport();
        entity.setApplicantId(userInfo.getUserId());
        entity.setTargetYm(Integer.valueOf(form.getTargetYm()));
        entity.setApplicationDate(LocalDateTime.now());
        // entity.setStatus("");
        entity.setPublishFlg(form.getPublishFlg());
        entity.setApprover1Id(form.getApprover1Id());
        entity.setApprover2Id(form.getApprover2Id());
        entity.setApprover3Id(form.getApprover3Id());
        entity.setFilePath("");

        // 登録処理
        reportRegistService.insertReportMst(entity);

        /*
         * 月報ファイル保存処理
         */
        // TODO ファイル名の規則を決める必要がある
        String fileNm = form.getTargetYm() + "_" + userInfo.getUserId() + ".xlsx";
        Path filePath = Paths.get(reportStorage, fileNm);
        FileUtils.reportSave(form.getFile().getInputStream(), filePath);

        // 完了メッセージ
        redirectAttr.addFlashAttribute(MessageConst.SUCCESS, message.getMessage("info.001", null, Locale.getDefault()));

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
        model.addAttribute(SessionUtils.getSessionForm(session, ReportApplicationRegistForm.class));

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
        model.addAttribute(SessionUtils.getSessionForm(session, ReportApplicationRegistForm.class));

        return PAGE_URL;
    }

    // ----------------------------------------------------------------------------------------

}
