package mms.uniq.login;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * ログイン画面コントローラー
 * @author
 */
@Controller
@Transactional(rollbackFor = Exception.class)
@SessionAttributes(value = "loginForm")
public class LoginController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

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

        // ログイン画面表示
        return "html/ログイン";
    }

    /**
     * ログイン処理<br>
     * ログイン認証/ログアウト処理はSecurityConfigで実施
     * @param form
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping("/login_auth_validate")
    public String login(@Valid LoginForm form,
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
        return "html/ログイン";
    }
}
