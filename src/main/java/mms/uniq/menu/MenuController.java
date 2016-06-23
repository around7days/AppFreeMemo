package mms.uniq.menu;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
public class MenuController {

    @RequestMapping("/menu")
    public String init(Model model) {
        return "html/メニュー";
    }
}
