package rms.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import rms.common.base.ProjectProperties;
import rms.common.utils.RmsStringUtils;

/**
 * CustomControllerAdviceクラス
 * @author
 */
@ControllerAdvice
public class ControllerAdviceCustom {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ControllerAdviceCustom.class);

    /** application.properties */
    @Autowired
    private ProjectProperties properties;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        // Stringクラスのフィールドに対して値にtrimを掛ける
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

    @ModelAttribute
    public void addAttribute(HttpServletRequest request,
                             HttpSession session,
                             Model model) {
        // クライアント入力チェック有無
        model.addAttribute("novalidate", properties.getHtml5Novalidate());

        // CSSテーマのデフォルトを設定
        // XXX 最終的にはAjaxに変更したい
        String theme = null;
        String requestTheme = request.getParameter("theme");
        Object sessionTheme = session.getAttribute("theme");
        String propertyTheme = properties.getCssThemeDefault();
        if (!RmsStringUtils.isEmpty(requestTheme)) {
            theme = requestTheme;
        } else if (!RmsStringUtils.isEmpty(sessionTheme)) {
            theme = sessionTheme.toString();
        } else {
            theme = propertyTheme;
        }
        model.addAttribute("theme", theme);
        session.setAttribute("theme", theme);
    }

    // @ExceptionHandler
    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // public String handleSystemException(Exception e) {
    // // 例外ハンドリングを行う
    // logger.error("System Error occurred.", e);
    // return "error/system";
    // }

}