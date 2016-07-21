package rms.domain.mst.user.entity;

import java.util.StringJoiner;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * UserSearchResultEntityクラス
 */
@Entity
public class UserSearchResultEntity {

    /** userId */
    @Column(name = "user_id")
    private String userId;

    /** userNm */
    @Column(name = "user_nm")
    private String userNm;

    /** email */
    @Column(name = "email")
    private String email;

    /** roleNm1 */
    @Column(name = "role_nm1")
    private String roleNm1;

    /** roleNm2 */
    @Column(name = "role_nm2")
    private String roleNm2;

    /** roleNm3 */
    @Column(name = "role_nm3")
    private String roleNm3;

    /**
     * userIdを取得します.
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * userIdを設定します.
     * @param userId userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * userNmを取得します.
     * @return userNm
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * userNmを設定します.
     * @param userNm userNm
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    /**
     * emailを取得します.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * emailを設定します.
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * roleNm1を取得します.
     * @return roleNm1
     */
    public String getRoleNm1() {
        return roleNm1;
    }

    /**
     * roleNm1を設定します.
     * @param roleNm1 roleNm1
     */
    public void setRoleNm1(String roleNm1) {
        this.roleNm1 = roleNm1;
    }

    /**
     * roleNm2を取得します.
     * @return roleNm2
     */
    public String getRoleNm2() {
        return roleNm2;
    }

    /**
     * roleNm2を設定します.
     * @param roleNm2 roleNm2
     */
    public void setRoleNm2(String roleNm2) {
        this.roleNm2 = roleNm2;
    }

    /**
     * roleNm3を取得します.
     * @return roleNm3
     */
    public String getRoleNm3() {
        return roleNm3;
    }

    /**
     * roleNm3を設定します.
     * @param roleNm3 roleNm3
     */
    public void setRoleNm3(String roleNm3) {
        this.roleNm3 = roleNm3;
    }

    // TODO 自動生成オブジェクトに追記するやり方はいいのかどうか・・・
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

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}