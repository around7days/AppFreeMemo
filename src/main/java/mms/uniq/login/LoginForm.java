package mms.uniq.login;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * ログイン画面フォーム
 * @author
 */
public class LoginForm {

    /** ユーザーID */
    @NotEmpty
    @Size(max = 10)
    private String userId;

    /** パスワード */
    @NotEmpty
    @Size(max = 10)
    private String password;

    /**
     * ユーザーIDを取得します。
     * @return ユーザーID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定します。
     * @param userId ユーザーID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * パスワードを取得します。
     * @return パスワード
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定します。
     * @param password パスワード
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
