package rms.web.tran.report.approval;

import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ReportApprovalEntityクラス
 */
@Entity
public class ReportApprovalEntity {

    /** applicantId */
    @Column(name = "applicant_id")
    private String applicantId;

    /** applicantNm */
    @Column(name = "applicant_nm")
    private String applicantNm;

    /** targetYm */
    @Column(name = "target_ym")
    private Integer targetYm;

    /** targetYear */
    @Column(name = "target_year")
    private String targetYear;

    /** targetMonth */
    @Column(name = "target_month")
    private String targetMonth;

    /** applicantDate */
    @Column(name = "applicant_date")
    private LocalDateTime applicantDate;

    /** status */
    @Column(name = "status")
    private String status;

    /** statusNm */
    @Column(name = "status_nm")
    private String statusNm;

    /** approver1Id */
    @Column(name = "approver1_id")
    private String approver1Id;

    /** approver1Nm */
    @Column(name = "approver1_nm")
    private String approver1Nm;

    /** approver2Id */
    @Column(name = "approver2_id")
    private String approver2Id;

    /** approver2Nm */
    @Column(name = "approver2_nm")
    private String approver2Nm;

    /** approver3Id */
    @Column(name = "approver3_id")
    private String approver3Id;

    /** approver3Nm */
    @Column(name = "approver3_nm")
    private String approver3Nm;

    /** filePath */
    @Column(name = "file_path")
    private String filePath;

    /**
     * applicantIdを取得します.
     * @return applicantId
     */
    public String getApplicantId() {
        return applicantId;
    }

    /**
     * applicantIdを設定します.
     * @param applicantId applicantId
     */
    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    /**
     * applicantNmを取得します.
     * @return applicantNm
     */
    public String getApplicantNm() {
        return applicantNm;
    }

    /**
     * applicantNmを設定します.
     * @param applicantNm applicantNm
     */
    public void setApplicantNm(String applicantNm) {
        this.applicantNm = applicantNm;
    }

    /**
     * targetYmを取得します.
     * @return targetYm
     */
    public Integer getTargetYm() {
        return targetYm;
    }

    /**
     * targetYmを設定します.
     * @param targetYm targetYm
     */
    public void setTargetYm(Integer targetYm) {
        this.targetYm = targetYm;
    }

    /**
     * targetYearを取得します.
     * @return targetYear
     */
    public String getTargetYear() {
        return targetYear;
    }

    /**
     * targetYearを設定します.
     * @param targetYear targetYear
     */
    public void setTargetYear(String targetYear) {
        this.targetYear = targetYear;
    }

    /**
     * targetMonthを取得します.
     * @return targetMonth
     */
    public String getTargetMonth() {
        return targetMonth;
    }

    /**
     * targetMonthを設定します.
     * @param targetMonth targetMonth
     */
    public void setTargetMonth(String targetMonth) {
        this.targetMonth = targetMonth;
    }

    /**
     * applicantDateを取得します.
     * @return applicantDate
     */
    public LocalDateTime getApplicantDate() {
        return applicantDate;
    }

    /**
     * applicantDateを設定します.
     * @param applicantDate applicantDate
     */
    public void setApplicantDate(LocalDateTime applicantDate) {
        this.applicantDate = applicantDate;
    }

    /**
     * statusを取得します.
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * statusを設定します.
     * @param status status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * statusNmを取得します.
     * @return statusNm
     */
    public String getStatusNm() {
        return statusNm;
    }

    /**
     * statusNmを設定します.
     * @param statusNm statusNm
     */
    public void setStatusNm(String statusNm) {
        this.statusNm = statusNm;
    }

    /**
     * approver1Idを取得します.
     * @return approver1Id
     */
    public String getApprover1Id() {
        return approver1Id;
    }

    /**
     * approver1Idを設定します.
     * @param approver1Id approver1Id
     */
    public void setApprover1Id(String approver1Id) {
        this.approver1Id = approver1Id;
    }

    /**
     * approver1Nmを取得します.
     * @return approver1Nm
     */
    public String getApprover1Nm() {
        return approver1Nm;
    }

    /**
     * approver1Nmを設定します.
     * @param approver1Nm approver1Nm
     */
    public void setApprover1Nm(String approver1Nm) {
        this.approver1Nm = approver1Nm;
    }

    /**
     * approver2Idを取得します.
     * @return approver2Id
     */
    public String getApprover2Id() {
        return approver2Id;
    }

    /**
     * approver2Idを設定します.
     * @param approver2Id approver2Id
     */
    public void setApprover2Id(String approver2Id) {
        this.approver2Id = approver2Id;
    }

    /**
     * approver2Nmを取得します.
     * @return approver2Nm
     */
    public String getApprover2Nm() {
        return approver2Nm;
    }

    /**
     * approver2Nmを設定します.
     * @param approver2Nm approver2Nm
     */
    public void setApprover2Nm(String approver2Nm) {
        this.approver2Nm = approver2Nm;
    }

    /**
     * approver3Idを取得します.
     * @return approver3Id
     */
    public String getApprover3Id() {
        return approver3Id;
    }

    /**
     * approver3Idを設定します.
     * @param approver3Id approver3Id
     */
    public void setApprover3Id(String approver3Id) {
        this.approver3Id = approver3Id;
    }

    /**
     * approver3Nmを取得します.
     * @return approver3Nm
     */
    public String getApprover3Nm() {
        return approver3Nm;
    }

    /**
     * approver3Nmを設定します.
     * @param approver3Nm approver3Nm
     */
    public void setApprover3Nm(String approver3Nm) {
        this.approver3Nm = approver3Nm;
    }

    /**
     * filePathを取得します.
     * @return filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * filePathを設定します.
     * @param filePath filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}