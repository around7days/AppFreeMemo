package mms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ログイン画面コントローラー
 * @author
 */
@Controller
@Transactional
public class LoginController {
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 初期処理
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String init(Model model) {
        // 初期処理
        model.addAttribute("user_id", "user1");

        // ログイン画面表示
        return "html/ログイン";
    }

    /**
     * ログイン処理
     * @param userId
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam(value = "user_id", required = false) String userId,
                        @RequestParam(value = "password", required = false) String password,
                        Model model) {
        // ログイン処理
        logger.debug(userId);
        logger.debug(password);

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
