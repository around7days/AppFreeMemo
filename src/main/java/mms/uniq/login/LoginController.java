package mms.uniq.login;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import mms.com.doma.dao.MUserDao;

/**
 * ログイン画面コントローラー
 * @author
 */
@Controller
@Transactional
public class LoginController {
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /** ログイン画面フォーム */
    @ModelAttribute
    LoginForm setupForm() {
        return new LoginForm();
    }

    /** ユーザーマスタDao */
    @Autowired
    MUserDao mUserDao;

    /**
     * 初期処理
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String init(Model model) {
        // 初期処理

        // ログイン画面表示
        return "html/ログイン";
    }

    /**
     * ログイン処理
     * @param loginForm
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping("/login_auth_validate")
    public String login(@Valid LoginForm loginForm,
                        BindingResult bindingResult,
                        Model model) {
        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug(bindingResult.getAllErrors().toString());
            return "html/ログイン";
        }

        // ログイン認証処理にフォワード
        return "forward:/login_auth";
    }

    /**
     * ログアウト処理
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        // ログアウト処理

        // ログイン画面初期処理にフォワード
        return "forward:/";
    }

    /**
     * ログイン失敗処理<br>
     * SpringConfigで設定したログインできなかった場合の処理を定義する
     * @param loginForm
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = "/login_error")
    public String loginError(LoginForm loginForm,
                             BindingResult bindingResult,
                             Model model) {
        bindingResult.reject("", "ログインに失敗しました");
        logger.debug(bindingResult.getAllErrors().toString());
        return "html/ログイン";
    }
}
