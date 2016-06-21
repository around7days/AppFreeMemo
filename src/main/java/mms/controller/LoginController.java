package mms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
public class LoginController {

    @RequestMapping("/")
    public String init(Model model) {
        return "forward:login";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("user_id", "user1");
        return "html/ログイン";
    }

    @RequestMapping("/menu")
    public String menu(Model model) {
        return "html/メニュー";
    }
}
