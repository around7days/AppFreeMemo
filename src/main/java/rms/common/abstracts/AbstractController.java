package rms.common.abstracts;

import rms.common.base.MessageSourceImpl;
import rms.common.utils.StringUtilsImpl;

/**
 * AbstractController
 * @author
 */
public abstract class AbstractController {

    /** MessageSource */
    // @Autowired
    protected MessageSourceImpl message;

    /**
     * リダイレクトURLの生成
     * @param url
     * @return
     */
    protected String redirect(String url) {
        return redirect(url, null);
    }

    /**
     * リダイレクトURLの生成
     * @param url
     * @param param
     * @return
     */
    protected String redirect(String url,
                              String param) {
        StringBuilder redirectUrl = new StringBuilder();
        redirectUrl.append("redirect:");
        redirectUrl.append(url);
        if (!StringUtilsImpl.isEmpty(param)) {
            redirectUrl.append("?").append(param);
        }
        return redirectUrl.toString();
    }

    /**
     * フォワードURLの生成
     * @param url
     * @return
     */
    protected String forward(String url) {
        return forward(url, null);
    }

    /**
     * フォワードURLの生成
     * @param url
     * @param param
     * @return
     */
    protected String forward(String url,
                             String param) {
        StringBuilder forwardUrl = new StringBuilder();
        forwardUrl.append("forward:");
        forwardUrl.append(url);
        if (!StringUtilsImpl.isEmpty(param)) {
            forwardUrl.append("?").append(param);
        }
        return forwardUrl.toString();
    }

    // /**
    // * ページIDの取得
    // * @return
    // */
    // protected abstract PageIdEnum getPageId();
}
