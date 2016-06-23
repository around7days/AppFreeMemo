package mms.com.security;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import mms.com.doma.entity.MUser;

/**
 * 認証ユーザーの情報を格納するクラス
 */
public class LoginUserInfo extends User {
    private static final long serialVersionUID = 1L;

    /**
     * ログインユーザー情報
     */
    private final MUser userInfo;

    /**
     * コンストラクタ
     * @param user
     */
    public LoginUserInfo(MUser user) {
        // スーパークラスのユーザーID、パスワードに値をセットする
        // 実際の認証はスーパークラスのユーザーID、パスワードで行われる
        super(user.getUserId(), user.getPassword(), true, true, true, true, new ArrayList<GrantedAuthority>());
        this.userInfo = user;
    }

    /**
     * @return
     */
    public MUser getUserInfo() {
        return userInfo;
    }
}