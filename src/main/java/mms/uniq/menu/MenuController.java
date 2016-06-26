package mms.uniq.menu;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mms.com.security.UserInfo;

@Controller
@Transactional(rollbackFor = Exception.class)
public class MenuController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    /**
     * メニュー画面初期表示
     * @param userInfo
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/menu")
    public String init(@AuthenticationPrincipal UserInfo userInfo,
                       HttpSession session,
                       Model model) {
        logger.debug("ユーザーID：{}", userInfo.getUserId());
        logger.debug("ユーザー名：{}", userInfo.getUserNm());

        // TODO
        Enumeration<String> e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            logger.info("session key: {}", e.nextElement());
        }

        return "html/メニュー";
    }

    /**
     * ユーザー一覧画面に遷移
     * @param model
     * @return
     */
    @RequestMapping(value = "/menu", params = "m001")
    public String M001(Model model) {
        return "redirect:/mst/user/search?init";
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

    @RequestMapping(value = "/menu", params = "e001")
    public String T004(Model model) throws Exception {
        if (true)
            throw new Exception("意図的な強制Exception");
        return "";
    }

}
