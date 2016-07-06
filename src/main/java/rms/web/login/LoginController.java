package rms.web.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import rms.com.consts.PageIdConst;

/**
 * ログイン画面コントローラー
 * @author
 */
@Controller
@SessionAttributes(types = LoginForm.class)
public class LoginController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /** デフォルトページID */
    private static final String DEFAULT_PAGE = PageIdConst.LOGIN;

    /** ログイン画面フォーム */
    @ModelAttribute(value = "loginForm")
    LoginForm setupForm() {
        return new LoginForm();
    }

    /**
     * 初期処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String init(LoginForm form,
                       Model model) {
        // 初期処理
        form.setUserId("user01");
        form.setPassword("pass");

        return DEFAULT_PAGE;
    }

    /**
     * ログイン処理<br>
     * ログイン認証/ログアウト処理はSecurityConfigで実施
     * @param form
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping("/login_validate")
    public String login(@Validated LoginForm form,
                        BindingResult bindingResult,
                        Model model) {
        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug(bindingResult.getAllErrors().toString());
            return DEFAULT_PAGE;
        }

        // ログイン認証処理にフォワード
        return "forward:/login_auth";
    }

    /**
     * ログイン失敗処理<br>
     * SpringConfigで設定したログインできなかった場合の処理を定義する
     * @param form
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping("/login_error")
    public String loginError(LoginForm form,
                             BindingResult bindingResult,
                             Model model) {
        bindingResult.reject("", "ログインに失敗しました");
        logger.debug(bindingResult.getAllErrors().toString());
        return DEFAULT_PAGE;
    }
}
