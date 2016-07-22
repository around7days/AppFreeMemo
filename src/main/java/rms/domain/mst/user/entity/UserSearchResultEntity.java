package rms.domain.mst.user.entity;

import java.util.StringJoiner;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import org.apache.commons.lang3.StringUtils;

/**
 * UserSearchResultEntityクラス
 */
@Entity
public class UserSearchResultEntity extends rms.com.abstracts.AbstractEntity {

    /** ユーザID */
    @Column(name = "user_id")
    private String userId;

    /** ユーザ名 */
    @Column(name = "user_nm")
    private String userNm;

    /** メールアドレス */
    @Column(name = "email")
    private String email;

    /** 承認者１ID */
    @Column(name = "approver1_id")
    private String approver1Id;

    /** 承認者１名称 */
    @Column(name = "approver1_nm")
    private String approver1Nm;

    /** 承認者２ID */
    @Column(name = "approver2_id")
    private String approver2Id;

    /** 承認者２名称 */
    @Column(name = "approver2_nm")
    private String approver2Nm;

    /** 承認者３ID */
    @Column(name = "approver3_id")
    private String approver3Id;

    /** 承認者３名称 */
    @Column(name = "approver3_nm")
    private String approver3Nm;

    /** 役割名１ */
    @Column(name = "role_nm1")
    private String roleNm1;

    /** 役割名２ */
    @Column(name = "role_nm2")
    private String roleNm2;

    /** 役割名３ */
    @Column(name = "role_nm3")
    private String roleNm3;

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
     * 承認者１IDを取得します。
     * @return 承認者１ID
     */
    public String getApprover1Id() {
        return approver1Id;
    }

    /**
     * 承認者１IDを設定します。
     * @param approver1Id 承認者１ID
     */
    public void setApprover1Id(String approver1Id) {
        this.approver1Id = approver1Id;
    }

    /**
     * 承認者１名称を取得します。
     * @return 承認者１名称
     */
    public String getApprover1Nm() {
        return approver1Nm;
    }

    /**
     * 承認者１名称を設定します。
     * @param approver1Nm 承認者１名称
     */
    public void setApprover1Nm(String approver1Nm) {
        this.approver1Nm = approver1Nm;
    }

    /**
     * 承認者２IDを取得します。
     * @return 承認者２ID
     */
    public String getApprover2Id() {
        return approver2Id;
    }

    /**
     * 承認者２IDを設定します。
     * @param approver2Id 承認者２ID
     */
    public void setApprover2Id(String approver2Id) {
        this.approver2Id = approver2Id;
    }

    /**
     * 承認者２名称を取得します。
     * @return 承認者２名称
     */
    public String getApprover2Nm() {
        return approver2Nm;
    }

    /**
     * 承認者２名称を設定します。
     * @param approver2Nm 承認者２名称
     */
    public void setApprover2Nm(String approver2Nm) {
        this.approver2Nm = approver2Nm;
    }

    /**
     * 承認者３IDを取得します。
     * @return 承認者３ID
     */
    public String getApprover3Id() {
        return approver3Id;
    }

    /**
     * 承認者３IDを設定します。
     * @param approver3Id 承認者３ID
     */
    public void setApprover3Id(String approver3Id) {
        this.approver3Id = approver3Id;
    }

    /**
     * 承認者３名称を取得します。
     * @return 承認者３名称
     */
    public String getApprover3Nm() {
        return approver3Nm;
    }

    /**
     * 承認者３名称を設定します。
     * @param approver3Nm 承認者３名称
     */
    public void setApprover3Nm(String approver3Nm) {
        this.approver3Nm = approver3Nm;
    }

    /**
     * 役割名１を取得します。
     * @return 役割名１
     */
    public String getRoleNm1() {
        return roleNm1;
    }

    /**
     * 役割名１を設定します。
     * @param roleNm1 役割名１
     */
    public void setRoleNm1(String roleNm1) {
        this.roleNm1 = roleNm1;
    }

    /**
     * 役割名２を取得します。
     * @return 役割名２
     */
    public String getRoleNm2() {
        return roleNm2;
    }

    /**
     * 役割名２を設定します。
     * @param roleNm2 役割名２
     */
    public void setRoleNm2(String roleNm2) {
        this.roleNm2 = roleNm2;
    }

    /**
     * 役割名３を取得します。
     * @return 役割名３
     */
    public String getRoleNm3() {
        return roleNm3;
    }

    /**
     * 役割名３を設定します。
     * @param roleNm3 役割名３
     */
    public void setRoleNm3(String roleNm3) {
        this.roleNm3 = roleNm3;
    }

    /**
     * 役割名を返却します
     * <p>
     * 役割が複数存在する場合は「/」で結合して返却
     * </p>
     * @return
     */
    public String getRoleNm() {
        StringJoiner join = new StringJoiner("/");
        //@formatter:off
        if(!StringUtils.isEmpty(roleNm1)){ join.add(roleNm1); }
        if(!StringUtils.isEmpty(roleNm2)){ join.add(roleNm2); }
        if(!StringUtils.isEmpty(roleNm3)){ join.add(roleNm3); }
        //@formatter:on
        return join.toString();
    }
}