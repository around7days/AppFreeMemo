package rms.web.app.system.menu;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import rms.common.base.WebSecurityConfig;
import rms.common.consts.MessageTypeConst;
import rms.common.exception.BusinessException;
import rms.common.utils.RmsSessionUtils;
import rms.web.app.mst.userlist.UserListController;
import rms.web.app.mst.userregist.UserRegistController;
import rms.web.app.tran.reportapplylist.ReportApplyListController;
import rms.web.app.tran.reportapplyregist.ReportApplyRegistController;
import rms.web.app.tran.reportapprovelist.ReportApproveListController;
import rms.web.app.tran.reportlist.ReportListController;

/**
 * メニュー登録画面コントローラー
 * @author
 */
@Controller
@SessionAttributes(types = MenuForm.class)
public class MenuController extends rms.common.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/menu";

    /** マッピングURL */
    public static final String MAPPING_URL = WebSecurityConfig.MENU_MAPPING_URL;

    /** 画面ID */
    public static final String SCREEN_ID = "S002";

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
        RmsSessionUtils.remove(session);

        return PAGE_URL;
    }

    /**
     * ユーザ一覧画面に遷移
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "M001")
    public String M001(MenuForm form,
                       Model model) {
        return urlHelper.redirect(UserListController.MAPPING_URL, "init");
    }

    /**
     * ユーザ登録画面に遷移
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "M002")
    public String M002(MenuForm form,
                       Model model) {
        return urlHelper.redirect(UserRegistController.MAPPING_URL, "initInsert");
    }

    /**
     * 月報一覧画面に遷移
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "T001")
    public String T001(MenuForm form,
                       Model model) {
        return urlHelper.redirect(ReportListController.MAPPING_URL, "init");
    }

    /**
     * 月報申請状況一覧画面に遷移
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "T003")
    public String T004(MenuForm form,
                       Model model) {
        return urlHelper.redirect(ReportApplyListController.MAPPING_URL, "init");
    }

    /**
     * 月報申請画面に遷移
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "T002")
    public String T002(MenuForm form,
                       Model model) {
        return urlHelper.redirect(ReportApplyRegistController.MAPPING_URL, "initApply");
    }

    /**
     * 月報承認状況一覧画面に遷移
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "T006")
    public String T005(MenuForm form,
                       Model model) {
        return urlHelper.redirect(ReportApproveListController.MAPPING_URL, "init");
    }

    /*
     * (非 Javadoc)
     * @see rms.common.abstracts.AbstractController#getScreenId()
     */
    @Override
    protected String getScreenId() {
        return SCREEN_ID;
    }

    // ----------------------------------------------------------------------------------------
    /**
     * 業務エラー（BusinessException）のエラーハンドリング
     * @param e
     * @param session
     * @param model
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public String handlerException(BusinessException e,
                                   HttpSession session,
                                   Model model) {
        logger.debug("業務エラー -> {}", e.toString());

        // メッセージを反映
        model.addAttribute(MessageTypeConst.ERROR, e.getErrorMessage());
        // セッション情報の詰め直し
        model.addAllAttributes(RmsSessionUtils.convertSessionToMap(session));

        return PAGE_URL;
    }
    // ----------------------------------------------------------------------------------------

}
