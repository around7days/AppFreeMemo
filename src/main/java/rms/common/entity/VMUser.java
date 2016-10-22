package rms.common.entity;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Table;
import org.seasar.doma.Version;

/**
 * VMUserクラス
 */
@Entity
@Table(name = "v_m_user")
public class VMUser {
    /** ユーザID */
    @Column(name = "user_id")
    private String userId;
    /** ユーザ名 */
    @Column(name = "user_nm")
    private String userNm;
    /** パスワード */
    @Column(name = "password")
    private String password;
    /** メールアドレス */
    @Column(name = "email")
    private String email;
    /** 部署ID コードマスタ：D001 */
    @Column(name = "department_id")
    private String departmentId;
    /** コード名称 */
    @Column(name = "department_nm")
    private String departmentNm;
    /** 属性１ */
    @Column(name = "department_rnm")
    private String departmentRnm;
    /** 承認者ID */
    @Column(name = "approve_user_id1")
    private String approveUserId1;
    /**  */
    @Column(name = "approve_user_nm1")
    private String approveUserNm1;
    /** 承認者ID */
    @Column(name = "approve_user_id2")
    private String approveUserId2;
    /**  */
    @Column(name = "approve_user_nm2")
    private String approveUserNm2;
    /** 承認者ID */
    @Column(name = "approve_user_id3")
    private String approveUserId3;
    /**  */
    @Column(name = "approve_user_nm3")
    private String approveUserNm3;
    /** バージョン */
    @Version
    @Column(name = "version")
    private Integer version;
    /** 削除フラグ */
    @Column(name = "del_flg")
    private Integer delFlg;
    /** 登録日時 */
    @Column(name = "ins_date")
    private LocalDateTime insDate;
    /** 登録ID */
    @Column(name = "ins_id")
    private String insId;
    /** 更新日時 */
    @Column(name = "upd_date")
    private LocalDateTime updDate;
    /** 更新ID */
    @Column(name = "upd_id")
    private String updId;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getDepartmentNm() {
        return departmentNm;
    }

    public void setDepartmentNm(String departmentNm) {
        this.departmentNm = departmentNm;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    public LocalDateTime getInsDate() {
        return insDate;
    }

    public void setInsDate(LocalDateTime insDate) {
        this.insDate = insDate;
    }

    public String getInsId() {
        return insId;
    }

    public void setInsId(String insId) {
        this.insId = insId;
    }

    public LocalDateTime getUpdDate() {
        return updDate;
    }

    public void setUpdDate(LocalDateTime updDate) {
        this.updDate = updDate;
    }

    public String getUpdId() {
        return updId;
    }

    public void setUpdId(String updId) {
        this.updId = updId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}