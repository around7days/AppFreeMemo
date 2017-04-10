package rms.common.base;

import rms.common.utils.StringUtilsImpl;

/**
 * URL生成補助クラス
 * @author
 */
public class UrlCreateHelper {

    /**
     * リダイレクトURLの生成
     * @param url
     * @return
     */
    public String redirect(String url) {
        return redirect(url, null);
    }

    /**
     * リダイレクトURLの生成
     * @param url
     * @param param
     * @return
     */
    public String redirect(String url,
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
    public String forward(String url) {
        return forward(url, null);
    }

    /**
     * フォワードURLの生成
     * @param url
     * @param param
     * @return
     */
    public String forward(String url,
                          String param) {
        StringBuilder forwardUrl = new StringBuilder();
        forwardUrl.append("forward:");
        forwardUrl.append(url);
        if (!StringUtilsImpl.isEmpty(param)) {
            forwardUrl.append("?").append(param);
        }
        return forwardUrl.toString();
    }

}
