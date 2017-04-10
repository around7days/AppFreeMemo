package rms.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * NumberUtilsクラス
 * @author
 */
public class NumberUtilsImpl extends org.springframework.util.NumberUtils {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(NumberUtilsImpl.class);

    /**
     * 数値チェック
     * @param val
     * @return true:数値 false:数値以外
     */
    public static boolean isInteger(String val) {
        try {
            Integer.valueOf(val);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
