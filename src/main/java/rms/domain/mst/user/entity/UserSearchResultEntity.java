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