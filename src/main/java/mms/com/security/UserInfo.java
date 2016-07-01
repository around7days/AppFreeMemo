package mms.com.security;

import java.util.ArrayList;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import mms.com.doma.entity.MUser;

/**
 * 認証ユーザの情報を格納するクラス
 */
public class UserInfo extends User {
    private static final long serialVersionUID = 1L;

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UserInfo.class);

    /*
     * ユーザ情報
     */
    /** ユーザID */
    String userId;

    /** ユーザ名 */
    String userNm;

    /** メールアドレス */
    String email;

    /**
     * コンストラクタ
     * @param user
     */
    public UserInfo(MUser user) {
        // スーパークラスのユーザID、パスワードに値をセットする
        // 実際の認証はスーパークラスのユーザID、パスワードで行われる
        super(user.getUserId(), user.getPassword(), true, true, true, true, new ArrayList<GrantedAuthority>());
        this.userId = user.getUserId();
        this.userNm = user.getUserNm();
        this.email = user.getEmail();
    }

    /**
     * ユーザIDを取得します。
     * @return ユーザID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザ名を取得します。
     * @return ユーザ名
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * メールアドレスを取得します。
     * @return メールアドレス
     */
    public String getEmail() {
        return email;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}