/**
 *
 */
package rms.common.utils;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import rms.common.consts.MessageEnum;

/**
 * メッセージUtils
 * @author
 */
public class MessageUtils {
    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(MessageUtils.class);

    /**
     * メッセージの取得
     * @param messageSource
     * @param messageCode
     * @return
     */
    public static String getMessage(MessageSource messageSource,
                                    MessageEnum messageCode) {
        return messageSource.getMessage(messageCode.name(), null, Locale.getDefault());
    }

    /**
     * メッセージの取得
     * @param messageSource
     * @param messageCode
     * @param params
     * @return
     */
    public static String getMessage(MessageSource messageSource,
                                    MessageEnum messageCode,
                                    Object... params) {
        return messageSource.getMessage(messageCode.name(), params, Locale.getDefault());
    }
}
