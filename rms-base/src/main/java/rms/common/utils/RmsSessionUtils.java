/**
 *
 */
package rms.common.utils;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * セッション管理Utils
 * @author
 */
public class RmsSessionUtils {
    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(RmsSessionUtils.class);

    // TODO どっかのタイミングできれいにする
    /** セッションキー除外対象リスト */
    private static final List<String> ignoreKeyList = Arrays.asList("SPRING_SECURITY_CONTEXT", // SPRING_SECURITY_CONTEXT
                                                                    "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN", // CRLFトークン
                                                                    RmsSessionInfo.SESSION_KEY // アプリケーション固有セッション情報
    );

    /**
     * セッション情報を破棄<br>
     * セッションキー除外対象リストを除く
     * @param session
     */
    public static void remove(HttpSession session) {
        Enumeration<String> e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            if (!ignoreKeyList.contains(key)) {
                // logger.debug("remove session -> {}", key);
                session.removeAttribute(key);
            }
        }
    }

    /**
     * セッション情報をMapに変換
     * @param session
     * @return
     */
    public static Map<String, ?> convertSessionToMap(HttpSession session) {
        Map<String, Object> attributes = new HashMap<>();

        Enumeration<String> names = session.getAttributeNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            Object obj = session.getAttribute(name);
            attributes.put(name, obj);
        }
        return attributes;
    }

    /**
     * アプリケーション固有セッション情報の取得<br>
     * 取得できない場合は新規作成する。
     * @param session
     * @return
     */
    public static RmsSessionInfo getRmsSessionInfo(HttpSession session) {
        RmsSessionInfo rmsSessionInfo;
        Object obj = session.getAttribute(RmsSessionInfo.SESSION_KEY);
        if (obj != null) {
            // 存在する場合はそのまま返却
            rmsSessionInfo = (RmsSessionInfo) obj;
        } else {
            // 存在しない場合は新規生成して返却
            rmsSessionInfo = new RmsSessionInfo();
            session.setAttribute(RmsSessionInfo.SESSION_KEY, rmsSessionInfo);
        }

        return rmsSessionInfo;
    }

    // /**
    // * セッションから指定されたクラスのフォーム情報の取得
    // * @param <T>
    // * @param session
    // * @param cls
    // * @return
    // */
    // @SuppressWarnings("unchecked")
    // public static <T> T getSessionForm(HttpSession session,
    // Class<T> cls) {
    //
    // try {
    // // クラスオブジェクトからSpringの自動生成keyを取得
    // String key = Conventions.getVariableName(cls.newInstance());
    // // セッションから取得
    // Object obj = session.getAttribute(key);
    // if (obj != null && obj.getClass() == cls) {
    // return (T) obj;
    // }
    // } catch (Exception e) {
    // throw new RuntimeException("class instance error", e);
    // }
    // return null;
    // }

}
