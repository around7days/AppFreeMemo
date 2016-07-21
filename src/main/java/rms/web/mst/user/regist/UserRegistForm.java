package rms.web.mst.user.regist;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * ユーザ登録画面フォーム
 * @author
 */
public class UserRegistForm extends rms.com.abstracts.AbstractForm {

    /* 入力チェック宣言 ----------------------------------------------------- */
    //@formatter:off
    /** 入力チェック：新規 */
    protected static interface Insert{};
    /** 入力チェック：更新 */
    protected static interface Update{};
    //@formatter:on

    /* 定数宣言 ------------------------------------------------------------- */
    /** 画面表示モード：新規 */
    public static final String VIEW_MODE_INSERT = "insert";
    /** 画面表示モード：更新 */
    public static final String VIEW_MODE_UPDATE = "update";

    /* 排他制御用 ----------------------------------------------------------- */
    /** バージョン */
    private int version;

    /* 変数宣言 ------------------------------------------------------------- */
    /** 画面表示モード */
    private String viewMode;

    /** ユーザID */
    @NotEmpty(message = "ユーザIDは{NotEmpty.message}", groups = { Insert.class, Update.class })
    @Size(max = 10, message = "ユーザIDは{Size.message}", groups = { Insert.class, Update.class })
    private String userId;
    /** パスワード */
    @NotEmpty(message = "パスワードは{NotEmpty.message}", groups = { Insert.class })
    @Size(max = 10, message = "パスワードは{Size.message}", groups = { Insert.class })
    private String password;
    /** ユーザ名 */
    @NotEmpty(message = "ユーザ名は{NotEmpty.message}", groups = { Insert.class, Update.class })
    @Size(max = 10, message = "ユーザ名は{Size.message}", groups = { Insert.class, Update.class })
    private String userNm;
    /** メールアドレス */
    @Email(message = "メールアドレスの{Email.message}", groups = { Insert.class, Update.class })
    private String email;
    /** 役割：申請者フラグ */
    private String roleApplicantFlg;
    /** 役割：承認者フラグ */
    private String roleApproverFlg;
    /** 役割：管理者フラグ */
    private String roleAdminFlg;

    /* getter/setter -------------------------------------------------------- */
    /**
     * バージョンを取得します。
     * @return バージョン
     */
    public int getVersion() {
        return version;
    }

    /**
     * バージョンを設定します。
     * @param version バージョン
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * 画面表示モードを取得します。
     * @return 画面表示モード
     */
    public String getViewMode() {
        return viewMode;
    }

    /**
     * 画面表示モードを設定します。
     * @param viewMode 画面表示モード
     */
    public void setViewMode(String viewMode) {
        this.viewMode = viewMode;
    }

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

    /**
     * ユーザ名を取得します。
     * @return ユーザ名
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * ユーザ名を設定します。
     * @param userNm ユーザ名
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

    /**
     * 役割：申請者フラグを取得します。
     * @return 役割：申請者フラグ
     */
    public String getRoleApplicantFlg() {
        return roleApplicantFlg;
    }

    /**
     * 役割：申請者フラグを設定します。
     * @param roleApplicantFlg 役割：申請者フラグ
     */
    public void setRoleApplicantFlg(String roleApplicantFlg) {
        this.roleApplicantFlg = roleApplicantFlg;
    }

    /**
     * 役割：承認者フラグを取得します。
     * @return 役割：承認者フラグ
     */
    public String getRoleApproverFlg() {
        return roleApproverFlg;
    }

    /**
     * 役割：承認者フラグを設定します。
     * @param roleApproverFlg 役割：承認者フラグ
     */
    public void setRoleApproverFlg(String roleApproverFlg) {
        this.roleApproverFlg = roleApproverFlg;
    }

    /**
     * 役割：管理者フラグを取得します。
     * @return 役割：管理者フラグ
     */
    public String getRoleAdminFlg() {
        return roleAdminFlg;
    }

    /**
     * 役割：管理者フラグを設定します。
     * @param roleAdminFlg 役割：管理者フラグ
     */
    public void setRoleAdminFlg(String roleAdminFlg) {
        this.roleAdminFlg = roleAdminFlg;
    }

}
