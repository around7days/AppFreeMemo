package rms.common.base;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    // TODO インジェクトできない・・・
    /** MessageSource */
    @Autowired
    MessageSourceImpl message;

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
     * @param value
     */
    public BusinessException(String value) {
        super();
        this.errorCode = "";
        this.errorMessage = value;
        logger.debug("errorCode -> {}", this.errorCode);
        logger.debug("errorMessage -> {}", this.errorMessage);
    }

    /**
     * コンストラクタ
     * @param code
     */
    public BusinessException(MessageEnum code) {
        super();
        this.errorCode = code.name();
        this.errorMessage = message.getMessage(code);
        logger.debug("errorCode -> {}", this.errorCode);
        logger.debug("errorMessage -> {}", this.errorMessage);
    }

    /**
     * コンストラクタ
     * @param code
     * @param args
     */
    public BusinessException(MessageEnum code, Object... args) {
        super();
        this.errorCode = code.name();
        this.errorMessage = message.getMessage(code, args);
        logger.debug("errorCode -> {}", this.errorCode);
        logger.debug("errorMessage -> {}", this.errorMessage);
    }

    /**
     * コンストラクタ
     * @param code
     * @param args
     */
    public BusinessException(MessageEnum code, List<Object> args) {
        super();
        this.errorCode = code.name();
        this.errorMessage = message.getMessage(code, args);
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