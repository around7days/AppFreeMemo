package mms.uniq.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mms.com.security.UserInfo;

@Controller
@Transactional
public class MenuController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    /**
     * メニュー画面初期表示
     * @param userInfo
     * @param model
     * @return
     */
    @RequestMapping("/menu")
    public String init(@AuthenticationPrincipal UserInfo userInfo,
                       Model model) {
        logger.debug("ユーザーID：{}", userInfo.getUserId());
        logger.debug("ユーザー名：{}", userInfo.getUserNm());
        return "html/メニュー";
    }

    @RequestMapping(value = "/menu", params = "t001")
    public String T001(Model model) {
        return "html/月報状況一覧";
    }

    @RequestMapping(value = "/menu", params = "t002")
    public String T002(Model model) {
        return "html/月報申請";
    }

    @RequestMapping(value = "/menu", params = "t003")
    public String T003(Model model) {
        return "html/月報承認";
    }

}
