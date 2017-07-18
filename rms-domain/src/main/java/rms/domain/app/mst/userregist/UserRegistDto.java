package rms.domain.app.mst.userregist;

/**
 * ユーザ情報クラス
 * @author
 */
public class UserRegistDto extends rms.common.abstracts.AbstractDto {

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
    /** 部署ID */
    private String departmentId;

    /** 承認者ID1 */
    private String approveUserId1;
    /** 承認者ID2 */
    private String approveUserId2;
    /** 承認者ID3 */
    private String approveUserId3;
    /** 承認者ID4 */
    private String approveUserId4;

    /** 役割：申請者フラグ */
    private String roleApplyFlg;
    /** 役割：承認者フラグ */
    private String roleApproveFlg;
    /** 役割：閲覧者フラグ */
    private String roleReferenceFlg;
    /** 役割：管理者フラグ */
    private String roleAdminFlg;

    /* getter/setter -------------------------------------------------------- */

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

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

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getApproveUserId1() {
        return approveUserId1;
    }

    public void setApproveUserId1(String approveUserId1) {
        this.approveUserId1 = approveUserId1;
    }

    public String getApproveUserId2() {
        return approveUserId2;
    }

    public void setApproveUserId2(String approveUserId2) {
        this.approveUserId2 = approveUserId2;
    }

    public String getApproveUserId3() {
        return approveUserId3;
    }

    public void setApproveUserId3(String approveUserId3) {
        this.approveUserId3 = approveUserId3;
    }

    public String getApproveUserId4() {
        return approveUserId4;
    }

    public void setApproveUserId4(String approveUserId4) {
        this.approveUserId4 = approveUserId4;
    }

    public String getRoleApplyFlg() {
        return roleApplyFlg;
    }

    public void setRoleApplyFlg(String roleApplyFlg) {
        this.roleApplyFlg = roleApplyFlg;
    }

    public String getRoleApproveFlg() {
        return roleApproveFlg;
    }

    public void setRoleApproveFlg(String roleApproveFlg) {
        this.roleApproveFlg = roleApproveFlg;
    }

    public String getRoleReferenceFlg() {
        return roleReferenceFlg;
    }

    public void setRoleReferenceFlg(String roleReferenceFlg) {
        this.roleReferenceFlg = roleReferenceFlg;
    }

    public String getRoleAdminFlg() {
        return roleAdminFlg;
    }

    public void setRoleAdminFlg(String roleAdminFlg) {
        this.roleAdminFlg = roleAdminFlg;
    }

}
