package rms.common.utils;

import org.springframework.stereotype.Component;

/**
 * URL生成補助クラス
 * @author
 */
@Component
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
     * @param methodNm
     * @return
     */
    public String redirect(String url,
                           String methodNm) {
        StringBuilder redirectUrl = new StringBuilder();
        redirectUrl.append("redirect:");
        redirectUrl.append(url);
        if (!RmsStringUtils.isEmpty(methodNm)) {
            redirectUrl.append("?").append(methodNm);
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
     * @param methodNm
     * @return
     */
    public String forward(String url,
                          String methodNm) {
        StringBuilder forwardUrl = new StringBuilder();
        forwardUrl.append("forward:");
        forwardUrl.append(url);
        if (!RmsStringUtils.isEmpty(methodNm)) {
            forwardUrl.append("?").append(methodNm);
        }
        return forwardUrl.toString();
    }

}
