package rms.common.base;

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

import rms.common.consts.MessageEnum;

/**
 * MessageSource拡張クラス<br>
 * ・引数にMessageEnumの指定が可能<br>
 * ・引数の簡略化<br>
 * @author
 */
public class MessageSourceEnumAccessor {

    /** MessageSource */
    @Autowired
    private static MessageSource message;

    @Autowired
    private MessageSource wiredMessage;

    @PostConstruct
    public void init() {
        MessageSourceEnumAccessor.message = wiredMessage;
    }

    /**
     * メッセージの取得
     * @param code
     * @return
     */
    public String getMessage(MessageEnum code) {
        return message.getMessage(code.name(), null, Locale.getDefault());
    }

    /**
     * メッセージの取得
     * @param code
     * @param args
     * @return
     */
    public String getMessage(MessageEnum code,
                             Object... args) {
        return message.getMessage(code.name(), args, Locale.getDefault());
    }

    /**
     * メッセージの取得
     * @param code
     * @param args
     * @return
     */
    public String getMessage(MessageEnum code,
                             List<Object> args) {
        return message.getMessage(code.name(), args.toArray(), Locale.getDefault());
    }

    /**
     * メッセージの取得
     * @param code
     * @return
     */
    public String getMessage(String code) {
        return message.getMessage(code, null, Locale.getDefault());
    }

    /**
     * メッセージの取得
     * @param code
     * @param args
     * @return
     */
    public String getMessage(String code,
                             List<Object> args) {
        return message.getMessage(code, args.toArray(), Locale.getDefault());
    }

    /**
     * メッセージの取得
     * @param code
     * @param args
     * @return
     */
    public String getMessage(String code,
                             Object... args) {
        return message.getMessage(code, args, Locale.getDefault());
    }

    /*
     * (非 Javadoc)
     * @see org.springframework.context.MessageSource#getMessage(java.lang.String, java.lang.Object[], java.lang.String, java.util.Locale)
     */
    public String getMessage(String code,
                             Object[] args,
                             String defaultMessage,
                             Locale locale) {
        return message.getMessage(code, args, defaultMessage, locale);
    }

    /*
     * (非 Javadoc)
     * @see org.springframework.context.MessageSource#getMessage(java.lang.String, java.lang.Object[], java.util.Locale)
     */
    public String getMessage(String code,
                             Object[] args,
                             Locale locale) throws NoSuchMessageException {
        return message.getMessage(code, args, locale);
    }

    /*
     * (非 Javadoc)
     * @see org.springframework.context.MessageSource#getMessage(org.springframework.context.MessageSourceResolvable, java.util.Locale)
     */
    public String getMessage(MessageSourceResolvable resolvable,
                             Locale locale) throws NoSuchMessageException {
        return message.getMessage(resolvable, locale);
    }

}
