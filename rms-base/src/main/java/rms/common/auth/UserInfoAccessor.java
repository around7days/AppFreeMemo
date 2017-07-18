package rms.common.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 */
public class UserInfoAccessor {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UserInfoAccessor.class);

    /**
     * 認証ユーザ情報の取得
     * @return
     */
    public static UserInfo getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserInfo) {
            return (UserInfo) authentication.getPrincipal();
        }
        return null;
    }
}
