/**
 *
 */
package mms.com.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author
 */
@Component
public class ValidateException extends RuntimeException {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(ValidateException.class);

    //    /** View名 */
    //    private String viewName;

    /** エラーメッセージ */
    private String[] errorMessages;

    /** BindingResult */
    private BindingResult bindingResult;

    /**
     *
     */
    public ValidateException() {
        super();
    }

    /**
     * @param errorMessages
     */
    public ValidateException(String... errorMessages) {
        super();
        this.errorMessages = errorMessages;
        logger.debug("errorMessages:{}", errorMessages.toString());
    }

    /**
     * @param bindingResult
     */
    public ValidateException(BindingResult bindingResult) {
        super();
        this.bindingResult = bindingResult;
        logger.debug("errorMessages:{}", bindingResult.getAllErrors().toString());
    }

    /**
     * エラーメッセージを取得します。
     * @return エラーメッセージ
     */
    public String[] getErrorMessages() {
        return errorMessages;
    }

    /**
     * BindingResultを取得します。
     * @return BindingResult
     */
    public BindingResult getBindingResult() {
        return bindingResult;
    }

}
