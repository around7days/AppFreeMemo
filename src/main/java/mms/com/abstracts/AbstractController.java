package mms.com.abstracts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;

import mms.com.security.UserInfo;

/**
 * @author
 */
public abstract class AbstractController {
    @Autowired
    protected MessageSource message;

    // TODO いる・・・？どっちかというとサービスクラス以降にほしい？
    /** 認証情報 */
    protected UserInfo getPrincipal() {
        return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
