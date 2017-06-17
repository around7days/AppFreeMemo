package rms.domain.app.mst.userlist;

import java.util.StringJoiner;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import rms.common.utils.RmsStringUtils;

/**
 * ユーザ一覧（検索結果）クラス
 */
@Entity
public class UserListEntityResult extends rms.common.abstracts.AbstractEntity {

    /** ユーザID */
    @Column(name = "user_id")
    private String userId;

    /** ユーザ名 */
    @Column(name = "user_nm")
    private String userNm;

    /** メールアドレス */
    @Column(name = "email")
    private String email;

    /** 部署略称 */
    @Column(name = "department_rnm")
    private String departmentRnm;

    /** 承認者ID1 */
    @Column(name = "approve_user_id1")
    private String approveUserId1;

    /** 承認者名称1 */
    @Column(name = "approve_user_nm1")
    private String approveUserNm1;

    /** 承認者ID2 */
    @Column(name = "approve_user_id2")
    private String approveUserId2;

    /** 承認者名称2 */
    @Column(name = "approve_user_nm2")
    private String approveUserNm2;

    /** 承認者ID3 */
    @Column(name = "approve_user_id3")
    private String approveUserId3;

    /** 承認者名称3 */
    @Column(name = "approve_user_nm3")
    private String approveUserNm3;

    /** 承認者ID4 */
    @Column(name = "approve_user_id4")
    private String approveUserId4;

    /** 承認者名称4 */
    @Column(name = "approve_user_nm4")
    private String approveUserNm4;

    /** 役割名1 */
    @Column(name = "role_nm1")
    private String roleNm1;

    /** 役割名2 */
    @Column(name = "role_nm2")
    private String roleNm2;

    /** 役割名3 */
    @Column(name = "role_nm3")
    private String roleNm3;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getDepartmentRnm() {
        return departmentRnm;
    }

    public void setDepartmentRnm(String departmentRnm) {
        this.departmentRnm = departmentRnm;
    }

    public String getApproveUserId1() {
        return approveUserId1;
    }

    public void setApproveUserId1(String approveUserId1) {
        this.approveUserId1 = approveUserId1;
    }

    public String getApproveUserNm1() {
        return approveUserNm1;
    }

    public void setApproveUserNm1(String approveUserNm1) {
        this.approveUserNm1 = approveUserNm1;
    }

    public String getApproveUserId2() {
        return approveUserId2;
    }

    public void setApproveUserId2(String approveUserId2) {
        this.approveUserId2 = approveUserId2;
    }

    public String getApproveUserNm2() {
        return approveUserNm2;
    }

    public void setApproveUserNm2(String approveUserNm2) {
        this.approveUserNm2 = approveUserNm2;
    }

    public String getApproveUserId3() {
        return approveUserId3;
    }

    public void setApproveUserId3(String approveUserId3) {
        this.approveUserId3 = approveUserId3;
    }

    public String getApproveUserNm3() {
        return approveUserNm3;
    }

    public void setApproveUserNm3(String approveUserNm3) {
        this.approveUserNm3 = approveUserNm3;
    }

    public String getApproveUserId4() {
        return approveUserId4;
    }

    public void setApproveUserId4(String approveUserId4) {
        this.approveUserId4 = approveUserId4;
    }

    public String getApproveUserNm4() {
        return approveUserNm4;
    }

    public void setApproveUserNm4(String approveUserNm4) {
        this.approveUserNm4 = approveUserNm4;
    }

    public String getRoleNm1() {
        return roleNm1;
    }

    public void setRoleNm1(String roleNm1) {
        this.roleNm1 = roleNm1;
    }

    public String getRoleNm2() {
        return roleNm2;
    }

    public void setRoleNm2(String roleNm2) {
        this.roleNm2 = roleNm2;
    }

    public String getRoleNm3() {
        return roleNm3;
    }

    public void setRoleNm3(String roleNm3) {
        this.roleNm3 = roleNm3;
    }

    /**
     * 役割名を返却します<br>
     * 備考：役割が複数存在する場合は「/」で結合して返却
     * @return
     */
    public String getRoleNm() {
        StringJoiner join = new StringJoiner("/");
        //@formatter:off
        if(!RmsStringUtils.isEmpty(roleNm1)){ join.add(roleNm1); }
        if(!RmsStringUtils.isEmpty(roleNm2)){ join.add(roleNm2); }
        if(!RmsStringUtils.isEmpty(roleNm3)){ join.add(roleNm3); }
        //@formatter:on
        return join.toString();
    }
}