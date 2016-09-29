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

    /** 部署名称 */
    @Column(name = "department_nm")
    private String departmentNm;

    /** 属性１ */
    @Column(name = "department_rnm")
    private String departmentRnm;

    /** 承認者ID１ */
    @Column(name = "approve_user_id1")
    private String approveUserId1;

    /** 承認者名１ */
    @Column(name = "approve_user_nm1")
    private String approveUserNm1;

    /** 承認者ID２ */
    @Column(name = "approve_user_id2")
    private String approveUserId2;

    /** 承認者名２ */
    @Column(name = "approve_user_nm2")
    private String approveUserNm2;

    /** 承認者ID３ */
    @Column(name = "approve_user_id3")
    private String approveUserId3;

    /** 承認者名３ */
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
     * 部署ID コードマスタ：D001を取得します。
     * @return 部署ID コードマスタ：D001
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * 部署ID コードマスタ：D001を設定します。
     * @param departmentId 部署ID コードマスタ：D001
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 部署名称を取得します。
     * @return 部署名称
     */
    public String getDepartmentNm() {
        return departmentNm;
    }

    /**
     * 部署名称を設定します。
     * @param departmentNm 部署名称
     */
    public void setDepartmentNm(String departmentNm) {
        this.departmentNm = departmentNm;
    }

    /**
     * 属性１を取得します。
     * @return 属性１
     */
    public String getDepartmentRnm() {
        return departmentRnm;
    }

    /**
     * 属性１を設定します。
     * @param departmentRnm 属性１
     */
    public void setDepartmentRnm(String departmentRnm) {
        this.departmentRnm = departmentRnm;
    }

    /**
     * 承認者ID１を取得します。
     * @return 承認者ID１
     */
    public String getApproveUserId1() {
        return approveUserId1;
    }

    /**
     * 承認者ID１を設定します。
     * @param approveUserId1 承認者ID１
     */
    public void setApproveUserId1(String approveUserId1) {
        this.approveUserId1 = approveUserId1;
    }

    /**
     * 承認者名１を取得します。
     * @return 承認者名１
     */
    public String getApproveUserNm1() {
        return approveUserNm1;
    }

    /**
     * 承認者名１を設定します。
     * @param approveUserNm1 承認者名１
     */
    public void setApproveUserNm1(String approveUserNm1) {
        this.approveUserNm1 = approveUserNm1;
    }

    /**
     * 承認者ID２を取得します。
     * @return 承認者ID２
     */
    public String getApproveUserId2() {
        return approveUserId2;
    }

    /**
     * 承認者ID２を設定します。
     * @param approveUserId2 承認者ID２
     */
    public void setApproveUserId2(String approveUserId2) {
        this.approveUserId2 = approveUserId2;
    }

    /**
     * 承認者名２を取得します。
     * @return 承認者名２
     */
    public String getApproveUserNm2() {
        return approveUserNm2;
    }

    /**
     * 承認者名２を設定します。
     * @param approveUserNm2 承認者名２
     */
    public void setApproveUserNm2(String approveUserNm2) {
        this.approveUserNm2 = approveUserNm2;
    }

    /**
     * 承認者ID３を取得します。
     * @return 承認者ID３
     */
    public String getApproveUserId3() {
        return approveUserId3;
    }

    /**
     * 承認者ID３を設定します。
     * @param approveUserId3 承認者ID３
     */
    public void setApproveUserId3(String approveUserId3) {
        this.approveUserId3 = approveUserId3;
    }

    /**
     * 承認者名３を取得します。
     * @return 承認者名３
     */
    public String getApproveUserNm3() {
        return approveUserNm3;
    }

    /**
     * 承認者名３を設定します。
     * @param approveUserNm3 承認者名３
     */
    public void setApproveUserNm3(String approveUserNm3) {
        this.approveUserNm3 = approveUserNm3;
    }

    /**
     * バージョンを取得します。
     * @return バージョン
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * バージョンを設定します。
     * @param version バージョン
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 削除フラグを取得します。
     * @return 削除フラグ
     */
    public Integer getDelFlg() {
        return delFlg;
    }

    /**
     * 削除フラグを設定します。
     * @param delFlg 削除フラグ
     */
    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * 登録日時を取得します。
     * @return 登録日時
     */
    public LocalDateTime getInsDate() {
        return insDate;
    }

    /**
     * 登録日時を設定します。
     * @param insDate 登録日時
     */
    public void setInsDate(LocalDateTime insDate) {
        this.insDate = insDate;
    }

    /**
     * 登録IDを取得します。
     * @return 登録ID
     */
    public String getInsId() {
        return insId;
    }

    /**
     * 登録IDを設定します。
     * @param insId 登録ID
     */
    public void setInsId(String insId) {
        this.insId = insId;
    }

    /**
     * 更新日時を取得します。
     * @return 更新日時
     */
    public LocalDateTime getUpdDate() {
        return updDate;
    }

    /**
     * 更新日時を設定します。
     * @param updDate 更新日時
     */
    public void setUpdDate(LocalDateTime updDate) {
        this.updDate = updDate;
    }

    /**
     * 更新IDを取得します。
     * @return 更新ID
     */
    public String getUpdId() {
        return updId;
    }

    /**
     * 更新IDを設定します。
     * @param updId 更新ID
     */
    public void setUpdId(String updId) {
        this.updId = updId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}