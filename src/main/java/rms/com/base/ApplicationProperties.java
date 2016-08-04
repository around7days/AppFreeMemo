package rms.com.base;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * application.propertiesクラス
 * @author
 */
public enum ApplicationProperties {
    INSTANCE;

    /** Logger */
    private static final Logger logger = Logger.getLogger(ApplicationProperties.class);

    /** プロパティ名(xxxxxx.properties) */
    private static final String PROPERTY_NM = "application";

    /** プロパティ */
    private static final ResourceBundle rb = ResourceBundle.getBundle(PROPERTY_NM);

    /**
     * プロパティから値を取得
     * @param key
     * @return keyに対応する値
     */
    public int getInt(String key) {
        String val = getString(key);
        return Integer.valueOf(val);
    }

    /**
     * プロパティから値を取得
     * @param key
     * @return keyに対応する値
     */
    public String getString(String key) {
        if (!rb.containsKey(key)) {
            logger.warn("not contains key : " + key);
            return null;
        }
        return rb.getString(key);
    }

    /**
     * プロパティから値を取得
     * @param key
     * @return keyに対応する値
     */
    public boolean getBoolean(String key) {
        if (!rb.containsKey(key)) {
            logger.warn("not contains key : " + key);
            return false;
        }
        return Boolean.valueOf(rb.getString(key));
    }

    /**
     * プロパティから値を取得
     * @param key
     * @return keyに対応する値
     */
    public Integer getInteger(String key) {
        if (!rb.containsKey(key)) {
            logger.warn("not contains key : " + key);
            return null;
        }
        return Integer.valueOf(rb.getString(key));
    }

}