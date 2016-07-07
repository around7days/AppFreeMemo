package rms.web.mst.user.search;

import javax.validation.constraints.Size;

/**
 * ユーザ一覧（検索条件）画面フォーム
 * @author
 */
public class UserSearchConditionForm extends rms.com.abstracts.AbstractForm {

    /** ユーザID */
    @Size(max = 10, message = "ユーザIDは{Size.message}")
    private String userId;

    /** ユーザ名 */
    @Size(max = 10, message = "ユーザ名は{Size.message}")
    private String userNm;

    /** 申請者区分 */
    private String applicantKbn;

    /** 承認者区分 */
    private String approvalKbn;

    /** 管理者区分 */
    private String adminKbn;

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
     * 申請者区分を取得します。
     * @return 申請者区分
     */
    public String getApplicantKbn() {
        return applicantKbn;
    }

    /**
     * 申請者区分を設定します。
     * @param applicantKbn 申請者区分
     */
    public void setApplicantKbn(String applicantKbn) {
        this.applicantKbn = applicantKbn;
    }

    /**
     * 承認者区分を取得します。
     * @return 承認者区分
     */
    public String getApprovalKbn() {
        return approvalKbn;
    }

    /**
     * 承認者区分を設定します。
     * @param approvalKbn 承認者区分
     */
    public void setApprovalKbn(String approvalKbn) {
        this.approvalKbn = approvalKbn;
    }

    /**
     * 管理者区分を取得します。
     * @return 管理者区分
     */
    public String getAdminKbn() {
        return adminKbn;
    }

    /**
     * 管理者区分を設定します。
     * @param adminKbn 管理者区分
     */
    public void setAdminKbn(String adminKbn) {
        this.adminKbn = adminKbn;
    }

}
