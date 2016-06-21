package mms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mms.com.doma.dao.MUserDao;


@Controller
@Transactional
public class LoginController {

    @Autowired
    MUserDao dao;

    @RequestMapping("/")
    public String login(Model model) {
    	model.addAttribute("user_id", "user1");
        return "html/ログイン";
    }

}
