package rms.web.app.system.login;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * ログイン画面フォーム
 * @author
 */
public class LoginForm extends rms.common.abstracts.AbstractForm {

    /** ユーザID */
    @NotEmpty(message = "ユーザID：{NotEmpty.message}")
    @Size(max = 10, message = "ユーザID：{Size.message}")
    private String userId;

    /** パスワード */
    @NotEmpty(message = "パスワード：{NotEmpty.message}")
    @Size(max = 10, message = "パスワード：{Size.message}")
    private String password;

    /**
     * ユーザIDを取得します。
     * @return ユーザID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザIDを設定します。
     * @param userId ユーザID
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
