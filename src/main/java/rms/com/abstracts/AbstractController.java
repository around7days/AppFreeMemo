package rms.com.abstracts;

import rms.web.com.base.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Conventions;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * @author
 */
public abstract class AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(AbstractController.class);

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
     */
    @SuppressWarnings("unchecked")
    protected <T> T getSessionForm(HttpSession session,
                                   Class<T> cls) {

        try {
            // クラスオブジェクトからSpringの自動生成keyを取得
            String key = Conventions.getVariableName(cls.newInstance());
            // セッションから取得
            Object obj = session.getAttribute(key);
            if (obj != null && obj.getClass() == cls) {
                return (T) obj;
            }
        } catch (Exception e) {
            logger.warn("class instance error", e.getMessage());
        }
        return null;
    }

}
