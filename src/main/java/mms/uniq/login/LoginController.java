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
    LoginForm form;

    /**
     * 初期処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String init(LoginForm form,
                       Model model) {
        // 初期処理
        form.setUserId("user1");
        model.addAttribute("form", form);

        // ログイン画面表示
        return "html/ログイン";
    }

    /**
     * ログイン処理
     * @param form
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(@ModelAttribute @Valid LoginForm form,
                        BindingResult bindingResult,
                        Model model) {
        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug("validate error");
            // model.addAttribute("form", form);
            return "html/ログイン";
        }

        // ログイン処理
        logger.info(form.getUserId());
        logger.info(form.getPassword());

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
