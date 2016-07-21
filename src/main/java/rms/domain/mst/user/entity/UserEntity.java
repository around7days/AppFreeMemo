package rms.domain.mst.user.entity;

import rms.domain.com.entity.MUser;

/**
 * ユーザ情報Entity
 * @author
 */
public class UserEntity extends rms.com.abstracts.AbstractEntity {

    /** ユーザマスタ情報 */
    private MUser MUser;
    /** 役割：申請者フラグ */
    private String roleApplicantFlg;
    /** 役割：承認者フラグ */
    private String roleApproverFlg;
    /** 役割：管理者フラグ */
    private String roleAdminFlg;

    /**
     * ユーザマスタ情報を取得します。
     * @return ユーザマスタ情報
     */
    public MUser getMUser() {
        return MUser;
    }

    /**
     * ユーザマスタ情報を設定します。
     * @param MUser ユーザマスタ情報
     */
    public void setMUser(MUser MUser) {
        this.MUser = MUser;
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
