package rms.com.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CustomControllerAdviceクラス
 * @author
 */
@ControllerAdvice
public class CustomControllerAdvice {

    /** クライアント入力チェック有無 */
    @Value("${myapp.html5.novalidate}")
    boolean html5novalidate;

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(CustomControllerAdvice.class);

    //    @InitBinder
    //    public void initBinder(WebDataBinder dataBinder) {
    //        // WebDataBinderのメソッドを呼び出してカスタマイズする
    //        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    //    }

    @ModelAttribute
    public void addOneObject(Model model) {
        // クライアント入力チェック有無
        model.addAttribute("novalidate", html5novalidate);
    }

    //    @ExceptionHandler
    //    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    //    public String handleSystemException(Exception e) {
    //        // 例外ハンドリングを行う
    //        logger.error("System Error occurred.", e);
    //        return "error/system";
    //    }

}