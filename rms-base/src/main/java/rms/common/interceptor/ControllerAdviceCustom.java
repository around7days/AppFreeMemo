package rms.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.seasar.doma.jdbc.OptimisticLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import rms.common.consts.MessageEnum;
import rms.common.consts.MessageTypeConst;
import rms.common.utils.MessageSourceEnumAccessor;
import rms.common.utils.ProjectProperties;
import rms.common.utils.RmsSessionInfo;
import rms.common.utils.RmsSessionUtils;
import rms.common.utils.RmsStringUtils;
import rms.common.utils.UrlCreateHelper;
import rms.web.app.system.menu.MenuController;

/**
 * CustomControllerAdviceクラス
 * @author
 */
@ControllerAdvice
public class ControllerAdviceCustom {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ControllerAdviceCustom.class);

    /** application.properties */
    @Autowired
    private ProjectProperties properties;

    /** UrlCreateHelper */
    @Autowired
    private UrlCreateHelper urlHelper;

    /** MessageSource */
    @Autowired
    private MessageSourceEnumAccessor message;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        // Stringクラスのフィールドに対して値にtrimを掛ける
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

    @ModelAttribute
    public void addAttribute(HttpServletRequest request,
                             HttpSession session,
                             Model model) {
        // アプリケーション固有セッション情報の取得
        RmsSessionInfo rmsSessionInfo = RmsSessionUtils.getRmsSessionInfo(session);

        /*
         * クライアント入力チェック有無
         */
        // レスポンスに追加
        model.addAttribute("novalidate", properties.getHtml5Novalidate());

        /*
         * CSSテーマのデフォルトを設定
         */
        String theme = null;
        String requestTheme = request.getParameter("theme");
        String sessionTheme = rmsSessionInfo.getTheme();
        String propertyTheme = properties.getCssThemeDefault();
        if (!RmsStringUtils.isEmpty(requestTheme)) {
            theme = requestTheme;
        } else if (!RmsStringUtils.isEmpty(sessionTheme)) {
            theme = sessionTheme;
        } else {
            theme = propertyTheme;
        }

        // レスポンスに追加
        model.addAttribute("theme", theme);
        // セッションに保存
        rmsSessionInfo.setTheme(theme);
    }

    /**
     * 楽観的排他制御（OptimisticLockException）のエラーハンドリング
     * @param e
     * @param session
     * @param redirectAttr
     * @param model
     * @return
     */
    @ExceptionHandler(OptimisticLockException.class)
    public String handlerException(OptimisticLockException e,
                                   HttpSession session,
                                   RedirectAttributes redirectAttr,
                                   Model model) {
        logger.debug("楽観的排他制御エラー -> {}", e.getMessage());

        // エラーメッセージを設定
        redirectAttr.addAttribute(MessageTypeConst.ERROR, message.getMessage(MessageEnum.error002));

        // メニュー画面に戻る
        return urlHelper.redirect(MenuController.MAPPING_URL);
    }
}