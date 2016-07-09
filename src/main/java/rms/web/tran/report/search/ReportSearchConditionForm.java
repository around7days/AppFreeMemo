package rms.web.tran.report.search;

/**
 * 月報状況一覧（検索条件）画面フォーム
 * @author
 */
public class ReportSearchConditionForm extends rms.com.abstracts.AbstractForm {

    /* 変数宣言 ------------------------------------------------------------- */
    /** 申請者ID */
    private String applicantId;
    /** 申請者名 */
    private String applicantNm;
    /** 承認状況：未承認 */
    private String statusUnApprove;
    /** 承認状況：承認済み */
    private String statusApprove;

    /* getter/setter -------------------------------------------------------- */
    /**
     * 申請者IDを取得します。
     * @return 申請者ID
     */
    public String getApplicantId() {
        return applicantId;
    }

    /**
     * 申請者IDを設定します。
     * @param applicantId 申請者ID
     */
    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    /**
     * 申請者名を取得します。
     * @return 申請者名
     */
    public String getApplicantNm() {
        return applicantNm;
    }

    /**
     * 申請者名を設定します。
     * @param applicantNm 申請者名
     */
    public void setApplicantNm(String applicantNm) {
        this.applicantNm = applicantNm;
    }

    /**
     * 承認状況：未承認を取得します。
     * @return 承認状況：未承認
     */
    public String getStatusUnApprove() {
        return statusUnApprove;
    }

    /**
     * 承認状況：未承認を設定します。
     * @param statusUnApprove 承認状況：未承認
     */
    public void setStatusUnApprove(String statusUnApprove) {
        this.statusUnApprove = statusUnApprove;
    }

    /**
     * 承認状況：承認済みを取得します。
     * @return 承認状況：承認済み
     */
    public String getStatusApprove() {
        return statusApprove;
    }

    /**
     * 承認状況：承認済みを設定します。
     * @param statusApprove 承認状況：承認済み
     */
    public void setStatusApprove(String statusApprove) {
        this.statusApprove = statusApprove;
    }
}
