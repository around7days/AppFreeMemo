package rms.web.tran.report.approval;

/**
 * 月報承認画面フォーム
 * @author
 */
public class ReportApprovalForm extends rms.web.com.abstracts.AbstractForm {

    /* 排他制御用 ----------------------------------------------------------- */
    /** バージョン */
    private int version;

    /* 変数宣言 ------------------------------------------------------------- */
    /** 月報ファイルパス */
    private String filePath;
    /** 年月 */
    private String targetYm;
    /** 申請者ID */
    private String applicantId;
    /** 申請者名称 */
    private String applicantNm;
    /** 承認者１名称 */
    private String approver1Nm;
    /** 承認者２名称 */
    private String approver2Nm;
    /** 承認者３名称 */
    private String approver3Nm;

    /* getter/setter -------------------------------------------------------- */
    /**
     * バージョンを取得します。
     * @return バージョン
     */
    public int getVersion() {
        return version;
    }

    /**
     * バージョンを設定します。
     * @param version バージョン
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * 月報ファイルパスを取得します。
     * @return 月報ファイルパス
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 月報ファイルパスを設定します。
     * @param filePath 月報ファイルパス
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 年月を取得します。
     * @return 年月
     */
    public String getTargetYm() {
        return targetYm;
    }

    /**
     * 年月を設定します。
     * @param targetYm 年月
     */
    public void setTargetYm(String targetYm) {
        this.targetYm = targetYm;
    }

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
     * 申請者名称を取得します。
     * @return 申請者名称
     */
    public String getApplicantNm() {
        return applicantNm;
    }

    /**
     * 申請者名称を設定します。
     * @param applicantNm 申請者名称
     */
    public void setApplicantNm(String applicantNm) {
        this.applicantNm = applicantNm;
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

}
