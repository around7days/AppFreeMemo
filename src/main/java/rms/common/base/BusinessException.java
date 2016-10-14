package rms.common.base;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import rms.common.consts.MessageEnum;

/**
 * 業務ロジックチェックException<br>
 * 説明：業務上想定内のエラー発生時に使用する。
 * @author
 */
@Component
public class BusinessException extends Exception {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BusinessException.class);

    /** MessageSource */
    @Autowired
    MessageSource message;

    /** エラーコード */
    private String errorCode;

    /** エラーメッセージ */
    private String errorMessage;

    /**
     * コンストラクタ
     */
    public BusinessException() {
        super();
    }

    /**
     * コンストラクタ
     * @param messageVal
     */
    public BusinessException(String messageVal) {
        super();
        this.errorCode = "";
        this.errorMessage = messageVal;
        logger.debug("errorCode -> {}", this.errorCode);
        logger.debug("errorMessage -> {}", this.errorMessage);
    }

    /**
     * コンストラクタ
     * @param messageEnum
     */
    public BusinessException(MessageEnum messageEnum) {
        super();
        this.errorCode = messageEnum.name();
        this.errorMessage = message.getMessage(messageEnum.name(), null, Locale.getDefault());
        logger.debug("errorCode -> {}", this.errorCode);
        logger.debug("errorMessage -> {}", this.errorMessage);
    }

    /**
     * コンストラクタ
     * @param messageEnum
     * @param args
     */
    public BusinessException(MessageEnum messageEnum, Object... args) {
        super();
        this.errorCode = messageEnum.name();
        this.errorMessage = message.getMessage(messageEnum.name(), args, Locale.getDefault());
        logger.debug("errorCode -> {}", this.errorCode);
        logger.debug("errorMessage -> {}", this.errorMessage);
    }

    /**
     * コンストラクタ
     * @param messageEnum
     * @param args
     */
    public BusinessException(MessageEnum messageEnum, List<Object> args) {
        super();
        this.errorCode = messageEnum.name();
        this.errorMessage = message.getMessage(messageEnum.name(), args.toArray(), Locale.getDefault());
        logger.debug("errorCode -> {}", this.errorCode);
        logger.debug("errorMessage -> {}", this.errorMessage);
    }

    /**
     * エラーコードを取得します。
     * @return エラーコード
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * エラーメッセージを取得します。
     * @return エラーメッセージ
     */
    public String getErrorMessage() {
        return errorMessage;
    }

}
