package rms.web.tran.report.applicantion;

import java.io.IOException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import rms.com.consts.PageIdConst;
import rms.web.com.base.UserInfo;

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

    /** デフォルトマッピングURL */
    public static final String DEFAULT_URL = "/tran/report/applicantion";

    /** デフォルトページID */
    private static final String DEFAULT_PAGE = PageIdConst.TRAN_REPROT_APPLICANTION;

    /** 月報申請画面サービス */
    @Autowired
    ReportApplicantionService reportApplicantionService;

    /** 月報申請画面フォーム */
    @ModelAttribute
    ReportApplicantionForm setupForm() {
        return new ReportApplicantionForm();
    }

    /**
     * 新規初期処理
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "initInsert")
    public String initInsert(Model model) {
        // 初期値設定
        ReportApplicantionForm form = setupForm();
        reportApplicantionService.initInsert(form);
        // 格納
        model.addAttribute(form);

        return DEFAULT_PAGE;
    }

    //    /**
    //     * 更新初期処理
    //     * @param form
    //     * @param userId
    //     * @param model
    //     * @return
    //     */
    //    @RequestMapping(value = DEFAULT_URL + "/{userId}", params = "initUpdate")
    //    public String initUpdate(ReportApplicantForm form,
    //                             @PathVariable String userId,
    //                             Model model) {
    //        // 初期値設定
    //        form.setViewMode(ReportApplicantForm.VIEW_MODE_UPDATE);
    //
    //        // 更新初期画面表示情報の取得
    //        reportApplicantService.initUpdate(form, userId);
    //
    //        logger.debug("フォーム情報 -> {}", form.toString());
    //
    //        return DEFAULT_PAGE;
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
     * @throws IllegalStateException
     */
    @RequestMapping(value = DEFAULT_URL, params = "insert")
    public String insert(@AuthenticationPrincipal UserInfo userInfo,
                         @Validated(ReportApplicantionForm.Insert.class) ReportApplicantionForm form,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttr,
                         Model model) throws IllegalStateException, IOException {
        logger.debug("フォーム情報 -> {}", form.toString());

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug(bindingResult.getAllErrors().toString());
            return DEFAULT_PAGE;
        }

        // 登録処理
        reportApplicantionService.insert(form, userInfo);
        // ファイル保存処理
        reportApplicantionService.saveFile(form.getFile());

        // 完了メッセージ
        redirectAttr.addFlashAttribute("successMessage", message.getMessage("info.001", null, Locale.getDefault()));

        return redirect("/menu");
    }

    //    /**
    //     * 更新処理
    //     * @param form
    //     * @param bindingResult
    //     * @param redirectAttr
    //     * @param model
    //     * @return
    //     */
    //    @RequestMapping(value = DEFAULT_URL, params = "update")
    //    public String update(@Validated(ReportApplicantForm.Update.class) ReportApplicantForm form,
    //                         BindingResult bindingResult,
    //                         RedirectAttributes redirectAttr,
    //                         Model model) {
    //        logger.debug("フォーム情報 -> {}", form.toString());
    //
    //        // 入力チェック
    //        if (bindingResult.hasErrors()) {
    //            logger.debug(bindingResult.getAllErrors().toString());
    //            return DEFAULT_PAGE;
    //        }
    //
    //        // 更新処理
    //        reportApplicantService.update(form);
    //
    //        // 完了メッセージ
    //        redirectAttr.addFlashAttribute("successMessage", message.getMessage("info.002", null, Locale.getDefault()));
    //
    //        return redirect("/mst/user/search", "reSearch");
    //    }

    /**
     * 戻る処理
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "back")
    public String back() {
        return redirect("/menu");
    }

}
