package rms.common.base;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * application.propertiesクラス
 */
public enum ApplicationProperties {
    INSTANCE;

    /** Logger */
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(ApplicationProperties.class);

    /** プロパティ名(xxxxxx.properties) */
    private static final String PROPERTY_NM = "application";

    /** プロパティ */
    private static final ResourceBundle rb = ResourceBundle.getBundle(PROPERTY_NM);

    /**
     * プロパティから値の取得
     * @param key
     * @return keyに対応する値
     */
    public Object getObject(String key) {
        return rb.getObject(key);
    }

    /**
     * プロパティから値を取得
     * @param key
     * @return keyに対応する値
     */
    public String getString(String key) {
        return rb.getString(key);
    }

    /**
     * プロパティから値を取得
     * @param key
     * @return keyに対応する値
     */
    public boolean getBoolean(String key) {
        return Boolean.valueOf(rb.getString(key));
    }

    /**
     * プロパティから値を取得
     * @param key
     * @return keyに対応する値
     */
    public Integer getInteger(String key) {
        return Integer.valueOf(rb.getString(key));
    }
}