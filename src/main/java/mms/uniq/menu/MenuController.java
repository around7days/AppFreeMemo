package mms.uniq.menu;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import mms.com.consts.PageIdConst;
import mms.com.security.UserInfo;

/**
 * メニュー登録画面コントローラー
 * @author
 */
@Controller
@SessionAttributes(types = MenuForm.class)
public class MenuController extends mms.com.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    /** デフォルトマッピングURL */
    public static final String DEFAULT_URL = "/menu";

    /** デフォルトページID */
    private static final String DEFAULT_PAGE = PageIdConst.MENU;

    /** メニュー画面フォーム */
    @ModelAttribute
    MenuForm setupForm() {
        return new MenuForm();
    }

    /**
     * メニュー画面初期表示
     * @param userInfo
     * @param form
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL)
    public String init(@AuthenticationPrincipal UserInfo userInfo,
                       MenuForm form,
                       HttpSession session,
                       Model model) {
        logger.debug("ユーザID：{}", userInfo.getUserId());
        logger.debug("ユーザ名：{}", userInfo.getUserNm());

        // TODO どっかのタイミングできれいにする
        // 個別セッションの破棄
        Enumeration<String> e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            logger.info("session key: {}", key);
            if (!"SPRING_SECURITY_CONTEXT".equals(key)
                && !"org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN".equals(key)) {
                session.removeAttribute(key);
            }
        }

        return DEFAULT_PAGE;
    }

    /**
     * ユーザ一覧画面に遷移
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "m001")
    public String M001(Model model) {
        return redirect("/mst/user/search", "init");
    }

    /**
     * ユーザ登録画面に遷移
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "m002")
    public String M002(Model model) {
        return redirect("/mst/user/regist", "initInsert");
    }

    @RequestMapping(value = DEFAULT_URL, params = "t001")
    public String T001(Model model) {
        return redirect("/tran/report/search", "init");
    }

    @RequestMapping(value = DEFAULT_URL, params = "t002")
    public String T002(Model model) {
        return redirect("/tran/report/applicant", "initInsert");
    }

    @RequestMapping(value = DEFAULT_URL, params = "t003")
    public String T003(Model model) {
        return "html/月報承認";
    }

    @SuppressWarnings("unused")
    @RequestMapping(value = DEFAULT_URL, params = "e001")
    public String T004(Model model) throws Exception {
        if (true) {
            throw new Exception("意図的な強制Exception");
        }
        return "";
    }

}
