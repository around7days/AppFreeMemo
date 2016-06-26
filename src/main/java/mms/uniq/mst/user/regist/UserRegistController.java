package mms.uniq.mst.user.regist;

import java.security.Principal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import mms.com.doma.dao.MUserDao;
import mms.com.doma.entity.MUser;

/**
 * ユーザー登録画面コントローラー
 * @author
 */
@Controller
@Transactional(rollbackFor = Exception.class)
@SessionAttributes(value = "userRegistForm")
public class UserRegistController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserRegistController.class);

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
    @RequestMapping(value = "/mst/user/regist/initNew")
    public String initNew(UserRegistForm form,
                          Model model) {
        // 初期値設定
        form.setViewMode(UserRegistForm.ViewMode.NEW);

        return "html/ユーザ登録";
    }

    /**
     * 更新初期処理
     * @param form
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = "/mst/user/regist/initUpdate/{userId}")
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

        return "html/ユーザ登録";
    }

    /**
     * 登録処理
     * @param form
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = "/mst/user/regist", params = "insert")
    public String insert(@Valid UserRegistForm form,
                         BindingResult bindingResult,
                         Model model) {
        logger.debug("フォーム情報：{}", form.toString());

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug(bindingResult.getAllErrors().toString());
            return "html/ユーザ登録";
        }

        // 登録処理

        return "forward:/mst/user/regist?back";
    }

    /**
     * 更新処理
     * @param form
     * @param bindingResult
     * @param principal
     * @param model
     * @return
     */
    @RequestMapping(value = "/mst/user/regist", params = "update")
    public String update(@Valid UserRegistForm form,
                         BindingResult bindingResult,
                         Principal principal,
                         Model model) {
        logger.debug("フォーム情報：{}", form.toString());

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug(bindingResult.getAllErrors().toString());
            return "html/ユーザ登録";
        }

        // 更新処理
        // 値の設定
        MUser mUser = new MUser();
        BeanUtils.copyProperties(form, mUser);
        logger.debug("更新情報：{}", mUser.toString());

        // 更新
        mUserDao.update(mUser);

        return "redirect:/mst/user/regist?back";
    }

    /**
     * 戻る処理
     * @return
     */
    @RequestMapping(value = "/mst/user/regist", params = "back")
    public String back() {
        return "redirect:/mst/user/search?reSearch";
    }

}
