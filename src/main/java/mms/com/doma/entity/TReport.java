package mms.com.doma.entity;

import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 */
@Entity(listener = TReportListener.class)
@Table(name = "t_report")
public class TReport {

    /** 申請者ID */
    @Id
    @Column(name = "applicant_id")
    String applicantId;

    /** 対象年月(yyyymm) */
    @Id
    @Column(name = "target_ym")
    Integer targetYm;

    /** 申請日 */
    @Column(name = "applicant_date")
    LocalDateTime applicantDate;

    /** 承認状況 */
    @Column(name = "status")
    Integer status;

    /** 承認者１ID */
    @Column(name = "approval1_id")
    String approval1Id;

    /** 承認者２ID */
    @Column(name = "approval2_id")
    String approval2Id;

    /** 承認者３ID */
    @Column(name = "approval3_id")
    String approval3Id;

    /** 月報ファイルパス */
    @Column(name = "file_path")
    String filePath;

    /** 削除フラグ */
    @Column(name = "del_flg")
    Integer delFlg;

    /** 登録日時 */
    @Column(name = "ins_date")
    LocalDateTime insDate;

    /** 登録ID */
    @Column(name = "ins_id")
    String insId;

    /** 更新日時 */
    @Column(name = "upd_date")
    LocalDateTime updDate;

    /** 更新ID */
    @Column(name = "upd_id")
    String updId;

    /**
     * Returns the applicantId.
     *
     * @return the applicantId
     */
    public String getApplicantId() {
        return applicantId;
    }

    /**
     * Sets the applicantId.
     *
     * @param applicantId the applicantId
     */
    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    /**
     * Returns the targetYm.
     *
     * @return the targetYm
     */
    public Integer getTargetYm() {
        return targetYm;
    }

    /**
     * Sets the targetYm.
     *
     * @param targetYm the targetYm
     */
    public void setTargetYm(Integer targetYm) {
        this.targetYm = targetYm;
    }

    /**
     * Returns the applicantDate.
     *
     * @return the applicantDate
     */
    public LocalDateTime getApplicantDate() {
        return applicantDate;
    }

    /**
     * Sets the applicantDate.
     *
     * @param applicantDate the applicantDate
     */
    public void setApplicantDate(LocalDateTime applicantDate) {
        this.applicantDate = applicantDate;
    }

    /**
     * Returns the status.
     *
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Returns the approval1Id.
     *
     * @return the approval1Id
     */
    public String getApproval1Id() {
        return approval1Id;
    }

    /**
     * Sets the approval1Id.
     *
     * @param approval1Id the approval1Id
     */
    public void setApproval1Id(String approval1Id) {
        this.approval1Id = approval1Id;
    }

    /**
     * Returns the approval2Id.
     *
     * @return the approval2Id
     */
    public String getApproval2Id() {
        return approval2Id;
    }

    /**
     * Sets the approval2Id.
     *
     * @param approval2Id the approval2Id
     */
    public void setApproval2Id(String approval2Id) {
        this.approval2Id = approval2Id;
    }

    /**
     * Returns the approval3Id.
     *
     * @return the approval3Id
     */
    public String getApproval3Id() {
        return approval3Id;
    }

    /**
     * Sets the approval3Id.
     *
     * @param approval3Id the approval3Id
     */
    public void setApproval3Id(String approval3Id) {
        this.approval3Id = approval3Id;
    }

    /**
     * Returns the filePath.
     *
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets the filePath.
     *
     * @param filePath the filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the delFlg.
     *
     * @return the delFlg
     */
    public Integer getDelFlg() {
        return delFlg;
    }

    /**
     * Sets the delFlg.
     *
     * @param delFlg the delFlg
     */
    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * Returns the insDate.
     *
     * @return the insDate
     */
    public LocalDateTime getInsDate() {
        return insDate;
    }

    /**
     * Sets the insDate.
     *
     * @param insDate the insDate
     */
    public void setInsDate(LocalDateTime insDate) {
        this.insDate = insDate;
    }

    /**
     * Returns the insId.
     *
     * @return the insId
     */
    public String getInsId() {
        return insId;
    }

    /**
     * Sets the insId.
     *
     * @param insId the insId
     */
    public void setInsId(String insId) {
        this.insId = insId;
    }

    /**
     * Returns the updDate.
     *
     * @return the updDate
     */
    public LocalDateTime getUpdDate() {
        return updDate;
    }

    /**
     * Sets the updDate.
     *
     * @param updDate the updDate
     */
    public void setUpdDate(LocalDateTime updDate) {
        this.updDate = updDate;
    }

    /**
     * Returns the updId.
     *
     * @return the updId
     */
    public String getUpdId() {
        return updId;
    }

    /**
     * Sets the updId.
     *
     * @param updId the updId
     */
    public void setUpdId(String updId) {
        this.updId = updId;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}