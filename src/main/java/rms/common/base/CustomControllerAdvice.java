package rms.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * CustomControllerAdviceクラス
 * @author
 */
@ControllerAdvice
public class CustomControllerAdvice {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(CustomControllerAdvice.class);

    /** application.properties */
    private static final ApplicationProperties properties = ApplicationProperties.INSTANCE;

    // @InitBinder
    // public void initBinder(WebDataBinder dataBinder) {
    // // WebDataBinderのメソッドを呼び出してカスタマイズする
    // dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    // }

    @ModelAttribute
    public void addAttribute(Model model) {
        // クライアント入力チェック有無
        model.addAttribute("novalidate", properties.getBoolean("myapp.html5.novalidate"));
    }

    // @ExceptionHandler
    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // public String handleSystemException(Exception e) {
    // // 例外ハンドリングを行う
    // logger.error("System Error occurred.", e);
    // return "error/system";
    // }

}