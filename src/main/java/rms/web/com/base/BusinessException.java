package rms.web.com.base;

import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 業務ロジックチェックException<br>
 * 業務上想定内のエラー発生時に使用する。
 * @author
 */
@Component
public class BusinessException extends RuntimeException {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BusinessException.class);

    // TODO メッセージIDからも取得できるようにしたい。
    /** エラーメッセージ */
    private String errorMessage;

    /**
     *
     */
    public BusinessException() {
        super();
    }

    /**
     * @param errorMessage
     */
    public BusinessException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
        logger.debug("errorMessage -> {}", errorMessage);
    }

    /**
     * エラーメッセージを取得します。
     * @return エラーメッセージ
     */
    public String getErrorMessage() {
        return errorMessage;
    }

}
