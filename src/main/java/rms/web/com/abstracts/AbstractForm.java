package rms.web.com.abstracts;

import rms.web.base.UserInfo;
import rms.web.com.utils.AuthenticationUtils;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author
 */
public abstract class AbstractForm {

    /**
     * 認証ユーザ情報の取得
     * @return
     */
    public UserInfo getPrincipal() {
        return AuthenticationUtils.getPrincipal();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
