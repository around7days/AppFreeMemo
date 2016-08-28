package rms.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import rms.common.auth.UserInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class AuthenticationUtils {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationUtils.class);

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
