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

    /** お知らせ情報 */
    private String info;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
