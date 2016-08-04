package rms.domain.mst.user.entity;

import java.util.StringJoiner;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import org.apache.commons.lang3.StringUtils;

/**
 * ユーザ一覧（検索結果）Entity
 */
@Entity
public class UserSearchResultEntity extends rms.domain.com.abstracts.AbstractEntity {

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
    @Column(name = "approve_user_id1")
    private String approveUserId1;

    /** 承認者１名称 */
    @Column(name = "approve_user_nm1")
    private String approveUserNm1;

    /** 承認者２ID */
    @Column(name = "approve_user_id2")
    private String approveUserId2;

    /** 承認者２名称 */
    @Column(name = "approve_user_nm2")
    private String approveUserNm2;

    /** 承認者３ID */
    @Column(name = "approve_user_id3")
    private String approveUserId3;

    /** 承認者３名称 */
    @Column(name = "approve_user_nm3")
    private String approveUserNm3;

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
    public String getApproveUserId1() {
        return approveUserId1;
    }

    /**
     * 承認者１IDを設定します。
     * @param approveUserId1 承認者１ID
     */
    public void setApproveUserId1(String approveUserId1) {
        this.approveUserId1 = approveUserId1;
    }

    /**
     * 承認者１名称を取得します。
     * @return 承認者１名称
     */
    public String getApproveUserNm1() {
        return approveUserNm1;
    }

    /**
     * 承認者１名称を設定します。
     * @param approveUserNm1 承認者１名称
     */
    public void setApproveUserNm1(String approveUserNm1) {
        this.approveUserNm1 = approveUserNm1;
    }

    /**
     * 承認者２IDを取得します。
     * @return 承認者２ID
     */
    public String getApproveUserId2() {
        return approveUserId2;
    }

    /**
     * 承認者２IDを設定します。
     * @param approveUserId2 承認者２ID
     */
    public void setApproveUserId2(String approveUserId2) {
        this.approveUserId2 = approveUserId2;
    }

    /**
     * 承認者２名称を取得します。
     * @return 承認者２名称
     */
    public String getApproveUserNm2() {
        return approveUserNm2;
    }

    /**
     * 承認者２名称を設定します。
     * @param approveUserNm2 承認者２名称
     */
    public void setApproveUserNm2(String approveUserNm2) {
        this.approveUserNm2 = approveUserNm2;
    }

    /**
     * 承認者３IDを取得します。
     * @return 承認者３ID
     */
    public String getApproveUserId3() {
        return approveUserId3;
    }

    /**
     * 承認者３IDを設定します。
     * @param approveUserId3 承認者３ID
     */
    public void setApproveUserId3(String approveUserId3) {
        this.approveUserId3 = approveUserId3;
    }

    /**
     * 承認者３名称を取得します。
     * @return 承認者３名称
     */
    public String getApproveUserNm3() {
        return approveUserNm3;
    }

    /**
     * 承認者３名称を設定します。
     * @param approveUserNm3 承認者３名称
     */
    public void setApproveUserNm3(String approveUserNm3) {
        this.approveUserNm3 = approveUserNm3;
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
     * 役割名を返却します<br>
     * 備考：役割が複数存在する場合は「/」で結合して返却
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