package rms.common.utils;

import org.seasar.doma.Entity;

/**
 * HTML：セレクトボックス用Entity
 * @author
 */
@Entity
public class SelectOptionEntity extends rms.common.abstracts.AbstractModel {

    /** 区切り文字 */
    private static final String DELIMITER = " - ";

    /** キー */
    private String key;

    /** 値 */
    private String value;

    /**
     * キーを取得します。
     * @return キー
     */
    public String getKey() {
        return key;
    }

    /**
     * キーを設定します。
     * @param Key キー
     */
    public void setKey(String Key) {
        this.key = Key;
    }

    /**
     * 値を取得します。
     * @return 値
     */
    public String getValue() {
        return value;
    }

    /**
     * 値を設定します。
     * @param value 値
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * キー＋値を取得します。
     * @return キー＋値
     */
    public String getKeyValue() {
        return key + DELIMITER + value;
    }

    /**
     * 値＋キーを取得します。
     * @return 値＋キー
     */
    public String getValueKey() {
        return value + DELIMITER + key;
    }

}
