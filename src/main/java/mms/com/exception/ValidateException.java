/**
 *
 */
package mms.com.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author
 */
@Component
public class ValidateException extends RuntimeException {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(ValidateException.class);

    /** エラーメッセージ */
    private String[] errorMessages;

    /** View名 */
    private String viewName;

    /**
     *
     */
    public ValidateException() {
        super();
    }

    /**
     * @param viewName
     */
    public ValidateException(String viewName) {
        super();
        this.viewName = viewName;
        logger.debug("ViewName:{}", viewName);
    }

    /**
     * @param viewName
     * @param errorMessages
     */
    public ValidateException(String viewName, String... errorMessages) {
        super();
        this.viewName = viewName;
        this.errorMessages = errorMessages;
        logger.debug("ViewName:{}", viewName);
        logger.debug("errorMessages:{}", errorMessages.toString());
    }

    /**
     * エラーメッセージを取得します。
     * @return エラーメッセージ
     */
    public String[] getErrorMessages() {
        return errorMessages;
    }

    /**
     * View名を取得します。
     * @return View名
     */
    public String getViewName() {
        return viewName;
    }
}
