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

    /**
     * 初期処理
     * @param model
     * @return
     */
    @RequestMapping("/")
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
    @RequestMapping("/login")
    public String login(@Valid LoginForm loginForm,
                        BindingResult bindingResult,
                        Model model) {
        logger.debug("userId={}", loginForm.getUserId());
        logger.debug("password={}", loginForm.getPassword());

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug("validate error");
            return "html/ログイン";
        }

        // ログイン処理

        // メニュー画面初期処理にリダイレクト
        return "redirect:/menu";
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
}
