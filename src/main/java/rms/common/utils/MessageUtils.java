package rms.common.utils;

//package rms.web.com.utils;
//
//import java.util.Locale;
//
//import org.springframework.context.MessageSource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * メッセージUtils
// * @author
// */
//public class MessageUtils {
//    /** logger */
//    @SuppressWarnings("unused")
//    private static final Logger logger = LoggerFactory.getLogger(MessageUtils.class);
//
//    /**
//     * メッセージの取得
//     * @param message
//     * @param code
//     * @return
//     */
//    public static String getMessage(MessageSource message,
//                                    String code) {
//        return message.getMessage(code, null, Locale.getDefault());
//    }
//
//    /**
//     * メッセージの取得
//     * @param message
//     * @param code
//     * @param param
//     * @return
//     */
//    public static String getMessage(MessageSource message,
//                                    String code,
//                                    String param) {
//        String[] params = new String[1];
//        params[0] = param;
//        return message.getMessage(code, params, Locale.getDefault());
//    }
//
//    /**
//     * メッセージの取得
//     * @param message
//     * @param code
//     * @param params
//     * @return
//     */
//    public static String getMessage(MessageSource message,
//                                    String code,
//                                    String... params) {
//        return message.getMessage(code, params, Locale.getDefault());
//    }
//
//}
