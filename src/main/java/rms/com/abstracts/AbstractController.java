package rms.com.abstracts;

import java.util.Enumeration;

import rms.web.com.base.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * @author
 */
public abstract class AbstractController {
    @Autowired
    protected MessageSource message;

    // TODO いる・・・？どっちかというとサービスクラス以降にほしい？
    /**
     * 認証情報
     */
    protected UserInfo getPrincipal() {
        return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * リダイレクトURLの生成
     * @param url
     */
    protected String redirect(String url) {
        return redirect(url, null);
    }

    /**
     * リダイレクトURLの生成
     * @param url
     * @param param
     */
    protected String redirect(String url,
                              String param) {
        StringBuilder redirectUrl = new StringBuilder();
        redirectUrl.append("redirect:");
        redirectUrl.append(url);
        if (!StringUtils.isEmpty(param)) {
            redirectUrl.append("?").append(param);
        }
        return redirectUrl.toString();
    }

    /**
     * フォワードURLの生成
     * @param url
     */
    protected String forward(String url) {
        return forward(url, null);
    }

    /**
     * フォワードURLの生成
     * @param url
     * @param param
     */
    protected String forward(String url,
                             String param) {
        StringBuilder forwardUrl = new StringBuilder();
        forwardUrl.append("forward:");
        forwardUrl.append(url);
        if (!StringUtils.isEmpty(param)) {
            forwardUrl.append("?").append(param);
        }
        return forwardUrl.toString();
    }

    /**
     * セッションからフォーム情報の取得
     * @param session
     * @param cls
     * @param model
     */
    @SuppressWarnings("unchecked")
    protected <T> T getSessionForm(HttpSession session,
                                   Class<T> cls) {
        // TODO クラス名のキャメル式で取得するか、クラス呼び出し時にセッションキーを保持しておきたい
        Enumeration<String> enumeration = session.getAttributeNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            Object obj = session.getAttribute(key);
            if (obj.getClass() == cls) {
                return (T) obj;
            }
        }
        return null;
    }

}
