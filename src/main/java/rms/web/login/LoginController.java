package rms.web.login;

import rms.web.base.SecurityConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * ログイン画面コントローラー
 * @author
 */
@Controller
@SessionAttributes(types = LoginForm.class)
public class LoginController extends rms.web.com.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/login";

    /** マッピングURL */
    public static final String MAPPING_URL = "/login";

    /**
     * ログイン画面フォームの初期化
     * @return
     */
    @ModelAttribute
    LoginForm setupForm() {
        return new LoginForm();
    }

    /**
     * 初期処理
     * @param form
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL)
    public String init(LoginForm form,
                       HttpSession session,
                       Model model) {
        // セッションが存在する場合は一度ログアウト処理を実施
        if (!session.isNew()) {
            return forward(SecurityConfig.LOGOUT_MAPPING_URL);
        }

        // 初期処理
        // XXX ダミー値
        form.setUserId("user01");
        form.setPassword("pass");

        return PAGE_URL;
    }

    /**
     * ログイン処理<br>
     * 備考：ログイン認証/ログアウト処理はSecurityConfigで実施
     * @param form
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "validate")
    public String login(@Validated LoginForm form,
                        BindingResult bindingResult,
                        Model model) {
        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug(bindingResult.getAllErrors().toString());
            return PAGE_URL;
        }

        // ログイン認証処理にフォワード
        return forward(SecurityConfig.AUTH_MAPPING_URL);
    }

    /**
     * ログイン失敗処理<br>
     * 備考：SpringConfigで設定したログインできなかった場合の処理を定義する
     * @param form
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "error")
    public String loginError(LoginForm form,
                             BindingResult bindingResult,
                             Model model) {
        bindingResult.reject("", "ログインに失敗しました");
        logger.debug(bindingResult.getAllErrors().toString());
        return PAGE_URL;
    }
}
