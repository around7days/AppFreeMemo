package rms.web.tran.report.applicantion;

import java.io.IOException;
import java.util.Locale;

import rms.com.base.UserInfo;
import rms.com.consts.MessageConst;
import rms.domain.tran.report.service.ReportApplicantionService;
import rms.web.menu.MenuController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 月報申請画面コントローラー
 * @author
 */
@Controller
@Transactional(rollbackFor = Exception.class)
@SessionAttributes(types = ReportApplicantionForm.class)
public class ReportApplicantionController extends rms.com.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApplicantionController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/reportApplication";

    /** マッピングURL */
    public static final String MAPPING_URL = "/tran/report/applicantion";

    /** 月報申請画面サービス */
    @Autowired
    ReportApplicantionService reportApplicantionService;

    /** 月報申請画面フォーム */
    @ModelAttribute
    ReportApplicantionForm setupForm() {
        return new ReportApplicantionForm();
    }

    /**
     * 初期表示処理（新規時）
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "initInsert")
    public String initInsert(Model model) {
        // 初期値設定
        ReportApplicantionForm form = setupForm();
        reportApplicantionService.initInsert(form);
        // 格納
        model.addAttribute(form);

        return PAGE_URL;
    }

    /**
     * 新規登録処理
     * @param userInfo
     * @param form
     * @param bindingResult
     * @param redirectAttr
     * @param model
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping(value = MAPPING_URL, params = "insert")
    public String insert(@AuthenticationPrincipal UserInfo userInfo,
                         @Validated(ReportApplicantionForm.Insert.class) ReportApplicantionForm form,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttr,
                         Model model) throws IllegalStateException, IOException {
        logger.debug("フォーム情報 -> {}", form.toString());

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug(bindingResult.getAllErrors().toString());
            return PAGE_URL;
        }

        // 登録処理
        reportApplicantionService.insert(form, userInfo);
        // ファイル保存処理
        reportApplicantionService.saveFile(form.getFile());

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

}
