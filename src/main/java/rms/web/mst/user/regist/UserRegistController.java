package rms.web.mst.user.regist;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import rms.com.consts.PageIdConst;
import rms.com.exception.ValidateException;

/**
 * ユーザ登録画面コントローラー
 * @author
 */
@Controller
@Transactional(rollbackFor = Exception.class)
@SessionAttributes(types = UserRegistForm.class)
public class UserRegistController extends rms.com.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserRegistController.class);

    /** デフォルトマッピングURL */
    public static final String DEFAULT_URL = "/mst/user/regist";

    /** デフォルトページID */
    private static final String DEFAULT_PAGE = PageIdConst.MST_USER_REGIST;

    /** ユーザ登録画面サービス */
    @Autowired
    UserRegistService userRegistService;

    /** ユーザ登録画面フォーム */
    @ModelAttribute
    UserRegistForm setupForm() {
        return new UserRegistForm();
    }

    /**
     * 新規初期処理
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "initInsert")
    public String initInsert(Model model) {
        // 初期値設定
        UserRegistForm form = setupForm();
        form.setViewMode(UserRegistForm.VIEW_MODE_INSERT);
        // 格納
        model.addAttribute(form);

        return DEFAULT_PAGE;
    }

    /**
     * 更新初期処理
     * @param form
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL + "/{userId}", params = "initUpdate")
    public String initUpdate(UserRegistForm form,
                             @PathVariable String userId,
                             Model model) {
        // 初期値設定
        form.setViewMode(UserRegistForm.VIEW_MODE_UPDATE);

        // 更新初期画面表示情報の取得
        userRegistService.initUpdate(form, userId);

        logger.debug("フォーム情報 -> {}", form.toString());

        return DEFAULT_PAGE;
    }

    /**
     * 新規登録処理
     * @param form
     * @param bindingResult
     * @param redirectAttr
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "insert")
    public String insert(@Validated(UserRegistForm.Insert.class) UserRegistForm form,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttr,
                         Model model) {
        logger.debug("フォーム情報 -> {}", form.toString());

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug(bindingResult.getAllErrors().toString());
            //            throw new ValidateException("aaa");
            return DEFAULT_PAGE;
        }

        // 登録処理
        userRegistService.insert(form);

        // TODO MessageResorceが使いにくい。どこかで改良。
        // TODO 完了メッセージをどこかで定数にする。
        // 完了メッセージ
        redirectAttr.addFlashAttribute("successMessages", message.getMessage("info.001", null, Locale.getDefault()));

        return redirect("/mst/user/search", "reSearch");
    }

    /**
     * 更新処理
     * @param form
     * @param bindingResult
     * @param redirectAttr
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "update")
    public String update(@Validated(UserRegistForm.Update.class) UserRegistForm form,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttr,
                         Model model) {
        logger.debug("フォーム情報 -> {}", form.toString());

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug(bindingResult.getAllErrors().toString());
            return DEFAULT_PAGE;
        }

        // チェックボックスを更新処理に含める
        //@formatter:off
        if(form.getApplicantKbn() == null) form.setApplicantKbn("");
        if(form.getApprovalKbn() == null) form.setApprovalKbn("");
        if(form.getAdminKbn() == null) form.setAdminKbn("");
        //@formatter:on

        // 更新処理
        userRegistService.update(form);

        // 完了メッセージ
        redirectAttr.addFlashAttribute("successMessages", message.getMessage("info.002", null, Locale.getDefault()));

        return redirect("/mst/user/search", "reSearch");
    }

    /**
     * 戻る処理
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "back")
    public String back() {
        return redirect("/mst/user/search", "reSearch");
    }

    // ----------------------------------------------------------------------------------------
    // TODO ここはもう少しどうにかしないと・・・エラーメッセージをどう表示するかな
    /** デフォルトエラーView名 */
    public static final String ERROR_VIEW = "redirect:" + DEFAULT_URL + "/?reDisplay";

    /**
     * 再表示処理
     * @param form
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "reDisplay")
    public String reDisplay(UserRegistForm form,
                            BindingResult bindingResult,
                            Model model) {
        logger.debug("フォーム情報 -> {}", form.toString());
        bindingResult.reject("", "ああああああああああああ");
        return DEFAULT_PAGE;
    }

    /**
     * ValidateExceptionのエラーハンドリング
     * @param e
     * @return
     */
    @ExceptionHandler(ValidateException.class)
    public ModelAndView handlerException(ValidateException e) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(DEFAULT_PAGE);

        return mv;
    }
    // ----------------------------------------------------------------------------------------
}
