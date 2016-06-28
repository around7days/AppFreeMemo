package mms.uniq.mst.user.regist;

import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mms.com.consts.PageIdConst;
import mms.com.doma.dao.MUserDao;
import mms.com.doma.entity.MUser;
import mms.com.exception.ValidateException;

/**
 * ユーザー登録画面コントローラー
 * @author
 */
@Controller
@Transactional(rollbackFor = Exception.class)
@SessionAttributes(value = "userRegistForm")
public class UserRegistController extends mms.com.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserRegistController.class);

    /** デフォルトマッピングURL */
    public static final String MAPPING_URL = "/mst/user/regist";

    /** デフォルトエラーView名 */
    public static final String ERROR_VIEW = "redirect:" + MAPPING_URL + "/?reDisplay";

    /** MUserDao */
    @Autowired
    MUserDao mUserDao;

    /** ユーザー登録画面フォーム */
    @ModelAttribute
    UserRegistForm setupForm() {
        return new UserRegistForm();
    }

    /**
     * 新規初期処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "initNew")
    public String initNew(UserRegistForm form,
                          Model model) {
        // 初期値設定
        form.setViewMode(UserRegistForm.ViewMode.NEW);

        form.setUserId("xxxxxxxxxxxxxxxxxxxxx");

        return PageIdConst.Mst.USER_REGIST;
    }

    /**
     * 更新初期処理
     * @param form
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL + "/{userId}", params = "initUpdate")
    public String initUpdate(UserRegistForm form,
                             @PathVariable String userId,
                             Model model) {
        // 初期値設定
        form.setViewMode(UserRegistForm.ViewMode.UPDATE);

        // ユーザー情報の取得
        MUser mUser = mUserDao.selectById(userId);

        // 画面表示設定
        // 詰め替え
        BeanUtils.copyProperties(mUser, form);

        logger.debug("ユーザ情報：{}", form.toString());

        return PageIdConst.Mst.USER_REGIST;
    }

    /**
     * 登録処理
     * @param form
     * @param bindingResult
     * @param redirectAttr
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "insert")
    public String insert(@Valid UserRegistForm form,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttr,
                         Model model) {
        logger.debug("フォーム情報：{}", form.toString());

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug(bindingResult.getAllErrors().toString());
            return PageIdConst.Mst.USER_REGIST;
        }

        // 登録処理
        // 値の設定
        MUser mUser = new MUser();
        BeanUtils.copyProperties(form, mUser);
        mUser.setPassword("pass");
        logger.debug("登録情報：{}", mUser.toString());

        // 登録
        mUserDao.insert(mUser);

        // TODO MessageResorceが使いにくい。どこかで改良。
        // TODO 完了メッセージをどこかで定数にする。
        // 完了メッセージ
        redirectAttr.addFlashAttribute("successMessages", message.getMessage("info.001", null, Locale.getDefault()));

        return "redirect:/mst/user/search?reSearch";
    }

    /**
     * 更新処理
     * @param form
     * @param bindingResult
     * @param redirectAttr
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "update")
    public String update(@Valid UserRegistForm form,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttr,
                         Model model) {
        logger.debug("フォーム情報：{}", form.toString());

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug(bindingResult.getAllErrors().toString());
            return PageIdConst.Mst.USER_REGIST;
        }

        // 更新処理
        // 値の設定
        MUser mUser = new MUser();
        BeanUtils.copyProperties(form, mUser);
        logger.debug("更新情報：{}", mUser.toString());

        // 更新
        mUserDao.update(mUser);

        // 完了メッセージ
        redirectAttr.addFlashAttribute("successMessages", message.getMessage("info.002", null, Locale.getDefault()));

        return "redirect:/mst/user/search?reSearch";
    }

    /**
     * 戻る処理
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "back")
    public String back() {
        return "redirect:/mst/user/search?reSearch";
    }

    // TODO ここはもう少しどうにかしないと・・・エラーメッセージをどう表示するかな
    /**
     * 再表示処理
     * @param form
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "reDisplay")
    public String reDisplay(UserRegistForm form,
                            BindingResult bindingResult,
                            Model model) {
        logger.debug("フォーム情報：{}", form.toString());
        bindingResult.reject("", "ああああああああああああ");
        return PageIdConst.Mst.USER_REGIST;
    }

    /**
     * ValidateExceptionのエラーハンドリング
     * @param e
     * @return
     */
    @ExceptionHandler(ValidateException.class)
    public ModelAndView handlerException(ValidateException e) {
        String viewName = StringUtils.isEmpty(e.getViewName()) ? ERROR_VIEW : e.getViewName();

        ModelAndView mv = new ModelAndView();
        mv.setViewName(viewName);

        return mv;
    }
}
