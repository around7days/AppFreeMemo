package rms.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * アプリケーション固有セッション情報保持クラス
 * @author
 */
public class RmsSessionInfo {
    /** logger */
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(RmsSessionInfo.class);

    /** セッションキー */
    public static final String SESSION_KEY = RmsSessionInfo.class.getName();

    /** CSSテーマ */
    private String theme;
    /** 画面ID */
    private String screenId;
    /** 前画面ID */
    private String preScreenId;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getPreScreenId() {
        return preScreenId;
    }

    public void setPreScreenId(String preScreenId) {
        this.preScreenId = preScreenId;
    }
}
