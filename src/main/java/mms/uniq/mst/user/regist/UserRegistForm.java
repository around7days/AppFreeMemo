package mms.uniq.mst.user.regist;

import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * ユーザ登録画面フォーム
 * @author
 */
public class UserRegistForm {

    /** 画面表示モード定義 */
    enum ViewMode {
        /** 新規 */
        NEW,
        /** 更新 */
        UPDATE;
    }

    /** 画面表示モード */
    private ViewMode viewMode;

    /** ユーザーID */
    @NotEmpty(message = "ユーザーIDは{NotEmpty.message}")
    @Size(max = 10, message = "ユーザーIDは{Size.message}")
    private String userId;

    /** ユーザー名 */
    @NotEmpty(message = "ユーザー名は{NotEmpty.message}")
    @Size(max = 10, message = "ユーザー名は{Size.message}")
    private String userNm;

    /** メールアドレス */
    @Email(message = "メールアドレスの{Email.message}")
    private String email;

    /**
     * 画面表示モードを取得します。
     * @return 画面表示モード
     */
    public ViewMode getViewMode() {
        return viewMode;
    }

    /**
     * 画面表示モードを設定します。
     * @param viewMode 画面表示モード
     */
    public void setViewMode(ViewMode viewMode) {
        this.viewMode = viewMode;
    }

    // TODO
    /**
     * 画面表示モード：新規？
     * @return 結果
     */
    public boolean isViewModeInsert() {
        return viewMode == ViewMode.NEW;
    }

    /**
     * 画面表示モード：更新？
     * @return 結果
     */
    public boolean isViewModeUpdate() {
        return viewMode == ViewMode.UPDATE;
    }

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
     * ユーザー名を取得します。
     * @return ユーザー名
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * ユーザー名を設定します。
     * @param userNm ユーザー名
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    /**
     * メールアドレスを取得します。
     * @return メールアドレス
     */
    public String getEmail() {
        return email;
    }

    /**
     * メールアドレスを設定します。
     * @param email メールアドレス
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
