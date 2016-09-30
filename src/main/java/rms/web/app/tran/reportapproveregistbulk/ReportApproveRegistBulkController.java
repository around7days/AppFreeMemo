package rms.web.app.tran.reportapproveregistbulk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import rms.common.base.BusinessException;
import rms.common.consts.MessageConst;
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
    //    @Autowired
    //    ReportApproveRegistService service;

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
     * @param sessionStatus
     * @param redirectAttr
     * @param model
     * @return
     * @throws IOException
     * @throws BusinessException
     * @throws NumberFormatException
     */
    @RequestMapping(value = MAPPING_URL, params = "approveBulk")
    public String approveBulk(ReportApproveRegistBulkForm form,
                              BindingResult bindingResult,
                              SessionStatus sessionStatus,
                              RedirectAttributes redirectAttr,
                              Model model) throws IOException, BusinessException {
        logger.debug("入力フォーム情報 -> {}", form);

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug("入力チェックエラー -> {}", bindingResult.getAllErrors());
            return PAGE_URL;
        }

        List<Map<String, String>> list = new ArrayList<>();
        {
            Map<String, String> map = new HashMap<>();
            map.put("fileNm", "201609_user01.xlsx");
            map.put("targetYm", "201609");
            map.put("applyUserId", "user01");
            map.put("applyUserNm", "申請太郎");
            map.put("status", "○");
            map.put("statusComment", "");
            list.add(map);
        }
        {
            Map<String, String> map = new HashMap<>();
            map.put("fileNm", "201609_user02.xlsx");
            map.put("targetYm", "201609");
            map.put("applyUserId", "user02");
            map.put("applyUserNm", "申請花子");
            map.put("status", "×");
            map.put("statusComment", "承認権限がありません");
            list.add(map);
        }
        form.resultList = list;

        // 完了メッセージ
        model.addAttribute(MessageConst.SUCCESS, message.getMessage("info.001", null, Locale.getDefault()));
        // セッション破棄
        sessionStatus.setComplete();

        return PAGE_URL;
    }

    //    /**
    //     * 月報DL処理
    //     * @param form
    //     * @param response
    //     * @param model
    //     * @return
    //     * @throws IOException
    //     */
    //    @RequestMapping(value = MAPPING_URL, params = "download")
    //    public String download(ReportApproveRegistBulkForm form,
    //                           HttpServletResponse response,
    //                           Model model) throws IOException {
    //        /*
    //         * ファイルダウンロード処理
    //         */
    //        // ダウンロードファイルパスの生成
    //        Path filePath = FileUtils.createReportFilePath(properties.getString("myapp.report.storage"),
    //                                                       form.getApplyUserId(),
    //                                                       form.getTargetYm());
    //        // 月報ダウンロード
    //        FileUtils.reportDownload(response, filePath);
    //
    //        return null;
    //    }
    //
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
    //    // ----------------------------------------------------------------------------------------
    //    /**
    //     * 業務エラー（BusinessException）のエラーハンドリング
    //     * @param e
    //     * @param session
    //     * @param model
    //     * @return
    //     */
    //    @ExceptionHandler(BusinessException.class)
    //    public String handlerException(BusinessException e,
    //                                   HttpSession session,
    //                                   Model model) {
    //        logger.debug("業務エラー -> {}", e.getErrorMessage());
    //
    //        // メッセージを反映
    //        model.addAttribute(MessageConst.ERROR, e.getErrorMessage());
    //        // セッションからフォーム情報を取得して反映
    //        model.addAttribute(SessionUtils.getSessionForm(session, ReportApproveRegistBulkForm.class));
    //
    //        return PAGE_URL;
    //    }
    //
    //    /**
    //     * 楽観的排他制御（OptimisticLockException）のエラーハンドリング
    //     * @param e
    //     * @param session
    //     * @param model
    //     * @return
    //     */
    //    @ExceptionHandler(OptimisticLockException.class)
    //    public String handlerException(OptimisticLockException e,
    //                                   HttpSession session,
    //                                   Model model) {
    //        logger.debug("楽観的排他制御エラー -> {}", e.getMessage());
    //
    //        // メッセージとフォーム情報を反映
    //        model.addAttribute(MessageConst.ERROR, message.getMessage("error.002", null, Locale.getDefault()));
    //        // セッションからフォーム情報を取得して反映
    //        model.addAttribute(SessionUtils.getSessionForm(session, ReportApproveRegistBulkForm.class));
    //
    //        return PAGE_URL;
    //    }
    //
    //    // ----------------------------------------------------------------------------------------

}
