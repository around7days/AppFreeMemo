package mms.com.security;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import mms.com.doma.entity.MUser;

/**
 * 認証ユーザーの情報を格納するクラス
 */
public class UserInfo extends User {
    private static final long serialVersionUID = 1L;

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UserInfo.class);

    /**
     * ユーザー情報
     */
    /** ユーザーID */
    String userId;

    /** ユーザー名 */
    String userNm;

    /** メールアドレス */
    String email;

    /**
     * コンストラクタ
     * @param user
     */
    public UserInfo(MUser user) {
        // スーパークラスのユーザーID、パスワードに値をセットする
        // 実際の認証はスーパークラスのユーザーID、パスワードで行われる
        super(user.getUserId(), user.getPassword(), true, true, true, true, new ArrayList<GrantedAuthority>());
        this.userId = user.getUserId();
        this.userNm = user.getUserNm();
        this.email = user.getEmail();
    }

    /**
     * ユーザーIDを取得します。
     * @return ユーザーID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザー名を取得します。
     * @return ユーザー名
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
}