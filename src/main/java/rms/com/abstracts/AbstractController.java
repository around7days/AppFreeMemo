package rms.com.abstracts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import rms.com.security.UserInfo;

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
        StringBuilder redirecUrl = new StringBuilder();
        redirecUrl.append("redirect:");
        redirecUrl.append(url);
        if (!StringUtils.isEmpty(param)) {
            redirecUrl.append("?").append(param);
        }
        return redirecUrl.toString();
    }

}
