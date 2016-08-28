package rms.domain.app.mst.userregist;

/**
 * ユーザ情報Entity
 * @author
 */
public class UserRegistEntity extends rms.common.abstracts.AbstractEntity {

    /* 排他制御用 ----------------------------------------------------------- */
    /** ユーザマスタ 排他制御用バージョン */
    private Integer version;

    /* 変数宣言 ------------------------------------------------------------- */
    /** ユーザID */
    private String userId;
    /** パスワード */
    private String password;
    /** ユーザ名 */
    private String userNm;
    /** メールアドレス */
    private String email;

    /** 承認者ID1 */
    private String approveUserId1;
    /** 承認者ID2 */
    private String approveUserId2;
    /** 承認者ID3 */
    private String approveUserId3;

    /** 役割：申請者フラグ */
    private String roleApplyFlg;
    /** 役割：承認者フラグ */
    private String roleApproveFlg;
    /** 役割：管理者フラグ */
    private String roleAdminFlg;

    /* getter/setter -------------------------------------------------------- */

    /**
     * ユーザマスタ 排他制御用バージョンを取得します。
     * @return ユーザマスタ 排他制御用バージョン
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * ユーザマスタ 排他制御用バージョンを設定します。
     * @param version ユーザマスタ 排他制御用バージョン
     */
    public void setVersion(Integer version) {
        this.version = version;
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
     * 承認者ID1を取得します。
     * @return 承認者ID1
     */
    public String getApproveUserId1() {
        return approveUserId1;
    }

    /**
     * 承認者ID1を設定します。
     * @param approveUserId1 承認者ID1
     */
    public void setApproveUserId1(String approveUserId1) {
        this.approveUserId1 = approveUserId1;
    }

    /**
     * 承認者ID2を取得します。
     * @return 承認者ID2
     */
    public String getApproveUserId2() {
        return approveUserId2;
    }

    /**
     * 承認者ID2を設定します。
     * @param approveUserId2 承認者ID2
     */
    public void setApproveUserId2(String approveUserId2) {
        this.approveUserId2 = approveUserId2;
    }

    /**
     * 承認者ID3を取得します。
     * @return 承認者ID3
     */
    public String getApproveUserId3() {
        return approveUserId3;
    }

    /**
     * 承認者ID3を設定します。
     * @param approveUserId3 承認者ID3
     */
    public void setApproveUserId3(String approveUserId3) {
        this.approveUserId3 = approveUserId3;
    }

    /**
     * 役割：申請者フラグを取得します。
     * @return 役割：申請者フラグ
     */
    public String getRoleApplyFlg() {
        return roleApplyFlg;
    }

    /**
     * 役割：申請者フラグを設定します。
     * @param roleApplyFlg 役割：申請者フラグ
     */
    public void setRoleApplyFlg(String roleApplyFlg) {
        this.roleApplyFlg = roleApplyFlg;
    }

    /**
     * 役割：承認者フラグを取得します。
     * @return 役割：承認者フラグ
     */
    public String getRoleApproveFlg() {
        return roleApproveFlg;
    }

    /**
     * 役割：承認者フラグを設定します。
     * @param roleApproveFlg 役割：承認者フラグ
     */
    public void setRoleApproveFlg(String roleApproveFlg) {
        this.roleApproveFlg = roleApproveFlg;
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
