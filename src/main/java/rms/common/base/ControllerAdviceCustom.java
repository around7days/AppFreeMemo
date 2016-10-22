package rms.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

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
    private static final ProjectProperties properties = ProjectProperties.INSTANCE;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        // Stringクラスのフィールドに対して値にtrimを掛ける
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

    @ModelAttribute
    public void addAttribute(Model model) {
        // クライアント入力チェック有無
        model.addAttribute("novalidate", properties.getBoolean("html5.novalidate"));
    }

    // @ExceptionHandler
    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // public String handleSystemException(Exception e) {
    // // 例外ハンドリングを行う
    // logger.error("System Error occurred.", e);
    // return "error/system";
    // }

}