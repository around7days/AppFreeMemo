package rms.domain.mst.user.entity;

import java.util.List;

import rms.domain.com.entity.MUserRole;
import rms.domain.com.entity.VMUser;

/**
 * ユーザ情報Entity
 * @author
 */
public class UserEntity extends rms.domain.com.abstracts.AbstractEntity {

    /** ユーザマスタビュー情報 */
    private VMUser user;
    /** ユーザ役割一覧情報 */
    private List<MUserRole> userRoleList;

    /** 役割：申請者フラグ */
    private String roleApplicantFlg;
    /** 役割：承認者フラグ */
    private String roleApproverFlg;
    /** 役割：管理者フラグ */
    private String roleAdminFlg;

    /**
     * ユーザマスタビュー情報を取得します。
     * @return ユーザマスタビュー情報
     */
    public VMUser getUser() {
        return user;
    }

    /**
     * ユーザマスタビュー情報を設定します。
     * @param user ユーザマスタビュー情報
     */
    public void setUser(VMUser user) {
        this.user = user;
    }

    /**
     * ユーザ役割一覧情報を取得します。
     * @return ユーザ役割一覧情報
     */
    public List<MUserRole> getUserRoleList() {
        return userRoleList;
    }

    /**
     * ユーザ役割一覧情報を設定します。
     * @param userRoleList ユーザ役割一覧情報
     */
    public void setUserRoleList(List<MUserRole> userRoleList) {
        this.userRoleList = userRoleList;
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
