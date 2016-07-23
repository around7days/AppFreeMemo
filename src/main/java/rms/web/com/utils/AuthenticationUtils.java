package rms.web.com.utils;

import rms.web.base.UserInfo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 */
public class AuthenticationUtils {
    /**
     * 認証ユーザ情報の取得
     * @return
     */
    public static UserInfo getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }

        return (UserInfo) authentication.getPrincipal();
    }
}
