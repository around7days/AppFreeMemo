package rms.web.com.abstracts;

import rms.web.base.UserInfo;

import org.springframework.security.core.context.SecurityContextHolder;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author
 */
public abstract class AbstractForm {

    /**
     * ユーザ認証情報の取得
     * @return
     */
    public UserInfo getPrincipal() {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
