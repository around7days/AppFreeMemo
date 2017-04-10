package rms.common.abstracts;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import rms.common.auth.UserInfo;
import rms.common.auth.UserInfoAccessor;

/**
 * AbstractForm
 * @author
 */
public abstract class AbstractForm {

    /**
     * 認証ユーザ情報の取得
     * @return
     */
    public UserInfo getPrincipal() {
        return UserInfoAccessor.getPrincipal();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
