package rms.web.tran.report.approval;

import java.io.IOException;
import java.util.Locale;

import rms.com.consts.MessageConst;
import rms.domain.tran.report.service.ReportApprovalService;
import rms.web.base.UserInfo;
import rms.web.menu.MenuController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

/**
 * 月報承認画面コントローラー
 * @author
 */
@Controller
@Transactional(rollbackFor = Exception.class)
@SessionAttributes(types = ReportApprovalForm.class)
public class ReportApprovalController extends rms.web.com.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApprovalController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/reportApproval";

    /** マッピングURL */
    public static final String MAPPING_URL = "/tran/report/approval";

    /** 月報承認画面サービス */
    @Autowired
    ReportApprovalService reportApprovalService;

    /**
     * 月報承認画面フォームの初期化
     * @return
     */
    @ModelAttribute
    ReportApprovalForm setupForm() {
        return new ReportApprovalForm();
    }

    /**
     * 初期処理
     * @param applicantId
     * @param targetYm
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL + "/{applicantId}/{targetYm}", params = "init")
    public String init(@PathVariable String applicantId,
                       @PathVariable String targetYm,
                       Model model) {
        // 初期値設定
        ReportApprovalForm form = setupForm();
        form.setApplicantId(applicantId);
        form.setTargetYm(targetYm);
        reportApprovalService.init(form);
        // 格納
        model.addAttribute(form);
        logger.debug("承認情報 -> {}", form.toString());

        return PAGE_URL;
    }

    /**
     * 承認処理
     * @param userInfo
     * @param form
     * @param redirectAttr
     * @param model
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping(value = MAPPING_URL, params = "approval")
    public String approval(@AuthenticationPrincipal UserInfo userInfo,
                           ReportApprovalForm form,
                           RedirectAttributes redirectAttr,
                           Model model) throws IllegalStateException, IOException {
        logger.debug("フォーム情報 -> {}", form.toString());

        // 承認処理
        reportApprovalService.approval(form, userInfo);

        // 完了メッセージ
        redirectAttr.addFlashAttribute(MessageConst.SUCCESS, message.getMessage("info.004", null, Locale.getDefault()));

        return redirect(MenuController.MAPPING_URL);
    }

    /**
     * 否認処理
     * @param userInfo
     * @param form
     * @param redirectAttr
     * @param model
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping(value = MAPPING_URL, params = "denial")
    public String denial(@AuthenticationPrincipal UserInfo userInfo,
                         ReportApprovalForm form,
                         RedirectAttributes redirectAttr,
                         Model model) throws IllegalStateException, IOException {
        logger.debug("フォーム情報 -> {}", form.toString());

        // 否認処理
        reportApprovalService.denial(form, userInfo);

        // 完了メッセージ
        redirectAttr.addFlashAttribute(MessageConst.SUCCESS, message.getMessage("info.005", null, Locale.getDefault()));

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
    public String download(ReportApprovalForm form,
                           HttpServletResponse response,
                           Model model) throws IOException {

        //        // 選択した月報情報
        //        SearchReportEntity result = form.getResultList().get(index);
        //        logger.debug("選択月報情報 -> {}", result.toString());
        //
        //        /*
        //         * ファイルダウンロード処理
        //         */
        //        // ダウンロードファイルパス・ファイル名の生成
        //        Path filePath = Paths.get("./upload_file", result.getFilePath());
        //        String encodeFileNm = URLEncoder.encode(filePath.toFile().getName(), StandardCharsets.UTF_8.name());
        //        logger.debug("ダウンロードファイル -> {}", filePath.toAbsolutePath().toString());
        //
        //        // ヘッダ設定
        //        response.addHeader("Content-Disposition", "attachment; filename*=UTF-8''" + encodeFileNm);
        //
        //        // ファイル出力
        //        Files.copy(filePath, response.getOutputStream());

        return null;
    }

    /**
     * 戻る処理
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "back")
    public String back() {
        return redirect(MenuController.MAPPING_URL);
    }

}
