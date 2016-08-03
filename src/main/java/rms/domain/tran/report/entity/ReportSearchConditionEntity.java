package rms.domain.tran.report.entity;

/**
 * 月報一覧（検索条件）Entity
 * @author
 */
public class ReportSearchConditionEntity extends rms.domain.com.abstracts.AbstractEntity {

    /* 変数宣言 ------------------------------------------------------------- */
    /** 対象年月 */
    private String targetYm;
    /** 申請者ID */
    private String applicantId;
    /** 申請者名 */
    private String applicantNm;

    /* getter/setter -------------------------------------------------------- */
    /**
     * 対象年月を取得します。
     * @return 対象年月
     */
    public String getTargetYm() {
        return targetYm;
    }

    /**
     * 対象年月を設定します。
     * @param targetYm 対象年月
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
}
