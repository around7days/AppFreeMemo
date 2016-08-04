package rms.web.menu;

import rms.web.com.utils.SessionUtils;
import rms.web.mst.user.list.UserListController;
import rms.web.mst.user.regist.UserRegistController;
import rms.web.tran.report.application.list.ReportApplicationListController;
import rms.web.tran.report.application.regist.ReportApplicationRegistController;
import rms.web.tran.report.list.ReportListController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * メニュー登録画面コントローラー
 * @author
 */
@Controller
@SessionAttributes(types = MenuForm.class)
public class MenuController extends rms.web.com.abstracts.AbstractController {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/menu";

    /** マッピングURL */
    public static final String MAPPING_URL = "/menu";

    /**
     * メニュー画面フォームの初期化
     * @return
     */
    @ModelAttribute
    MenuForm setupForm() {
        return new MenuForm();
    }

    /**
     * メニュー画面初期表示
     * @param form
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL)
    public String init(MenuForm form,
                       HttpSession session,
                       Model model) {
        // 個別セッションの破棄
        SessionUtils.remove(session);

        return PAGE_URL;
    }

    /**
     * ユーザ一覧画面に遷移
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "m001")
    public String M001(MenuForm form,
                       Model model) {
        return redirect(UserListController.MAPPING_URL, "init");
    }

    /**
     * ユーザ登録画面に遷移
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "m002")
    public String M002(MenuForm form,
                       Model model) {
        return redirect(UserRegistController.MAPPING_URL, "initInsert");
    }

    /**
     * 月報一覧画面に遷移
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "t001")
    public String T001(MenuForm form,
                       Model model) {
        return redirect(ReportListController.MAPPING_URL, "init");
    }

    /**
     * 月報申請状況一覧画面に遷移
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "t004")
    public String T004(MenuForm form,
                       Model model) {
        return redirect(ReportApplicationListController.MAPPING_URL, "init");
    }

    /**
     * 月報申請画面に遷移
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "t002")
    public String T002(MenuForm form,
                       Model model) {
        return redirect(ReportApplicationRegistController.MAPPING_URL, "initInsert");
    }
    //
    //    /**
    //     * 月報承認画面に遷移
    //     * @param form
    //     * @param model
    //     * @return
    //     */
    //    @RequestMapping(value = MAPPING_URL, params = "t003")
    //    public String T003(MenuForm form,
    //                       Model model) {
    //        return redirect(ReportApprovalController.MAPPING_URL + "/user01/201606", "init");
    //    }

    @SuppressWarnings("unused")
    @RequestMapping(value = MAPPING_URL, params = "e001")
    public String E001(MenuForm form,
                       Model model) throws Exception {
        if (true) {
            throw new Exception("意図的な強制Exception");
        }
        return "";
    }

}
