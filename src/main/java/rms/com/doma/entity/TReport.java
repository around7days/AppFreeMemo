package rms.com.doma.entity;

import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * TReportクラス
 * 
 */
@Entity(listener = TReportListener.class)
@Table(name = "t_report")
public class TReport {

    /** 申請者ID */
    @Id
    @Column(name = "applicant_id")
    private String applicantId;

    /** 対象年月(yyyymm) */
    @Id
    @Column(name = "target_ym")
    private Integer targetYm;

    /** 申請日 */
    @Column(name = "applicant_date")
    private LocalDateTime applicantDate;

    /** 承認状況 */
    @Column(name = "status")
    private String status;

    /** 承認者１ID */
    @Column(name = "approver1_id")
    private String approver1Id;

    /** 承認者２ID */
    @Column(name = "approver2_id")
    private String approver2Id;

    /** 承認者３ID */
    @Column(name = "approver3_id")
    private String approver3Id;

    /** 月報ファイルパス */
    @Column(name = "file_path")
    private String filePath;

    /** 削除フラグ */
    @Column(name = "del_flg")
    private Integer delFlg;

    /** 登録日時 */
    @Column(name = "ins_date")
    private LocalDateTime insDate;

    /** 登録ID */
    @Column(name = "ins_id")
    private String insId;

    /** 更新日時 */
    @Column(name = "upd_date")
    private LocalDateTime updDate;

    /** 更新ID */
    @Column(name = "upd_id")
    private String updId;

    /**
     * 申請者IDを取得します.
     * @return 申請者ID
     */
    public String getApplicantId() {
        return applicantId;
    }

    /**
     * 申請者IDを設定します.
     * @param applicantId 申請者ID
     */
    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    /**
     * 対象年月(yyyymm)を取得します.
     * @return 対象年月(yyyymm)
     */
    public Integer getTargetYm() {
        return targetYm;
    }

    /**
     * 対象年月(yyyymm)を設定します.
     * @param targetYm 対象年月(yyyymm)
     */
    public void setTargetYm(Integer targetYm) {
        this.targetYm = targetYm;
    }

    /**
     * 申請日を取得します.
     * @return 申請日
     */
    public LocalDateTime getApplicantDate() {
        return applicantDate;
    }

    /**
     * 申請日を設定します.
     * @param applicantDate 申請日
     */
    public void setApplicantDate(LocalDateTime applicantDate) {
        this.applicantDate = applicantDate;
    }

    /**
     * 承認状況を取得します.
     * @return 承認状況
     */
    public String getStatus() {
        return status;
    }

    /**
     * 承認状況を設定します.
     * @param status 承認状況
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 承認者１IDを取得します.
     * @return 承認者１ID
     */
    public String getApprover1Id() {
        return approver1Id;
    }

    /**
     * 承認者１IDを設定します.
     * @param approver1Id 承認者１ID
     */
    public void setApprover1Id(String approver1Id) {
        this.approver1Id = approver1Id;
    }

    /**
     * 承認者２IDを取得します.
     * @return 承認者２ID
     */
    public String getApprover2Id() {
        return approver2Id;
    }

    /**
     * 承認者２IDを設定します.
     * @param approver2Id 承認者２ID
     */
    public void setApprover2Id(String approver2Id) {
        this.approver2Id = approver2Id;
    }

    /**
     * 承認者３IDを取得します.
     * @return 承認者３ID
     */
    public String getApprover3Id() {
        return approver3Id;
    }

    /**
     * 承認者３IDを設定します.
     * @param approver3Id 承認者３ID
     */
    public void setApprover3Id(String approver3Id) {
        this.approver3Id = approver3Id;
    }

    /**
     * 月報ファイルパスを取得します.
     * @return 月報ファイルパス
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 月報ファイルパスを設定します.
     * @param filePath 月報ファイルパス
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 削除フラグを取得します.
     * @return 削除フラグ
     */
    public Integer getDelFlg() {
        return delFlg;
    }

    /**
     * 削除フラグを設定します.
     * @param delFlg 削除フラグ
     */
    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * 登録日時を取得します.
     * @return 登録日時
     */
    public LocalDateTime getInsDate() {
        return insDate;
    }

    /**
     * 登録日時を設定します.
     * @param insDate 登録日時
     */
    public void setInsDate(LocalDateTime insDate) {
        this.insDate = insDate;
    }

    /**
     * 登録IDを取得します.
     * @return 登録ID
     */
    public String getInsId() {
        return insId;
    }

    /**
     * 登録IDを設定します.
     * @param insId 登録ID
     */
    public void setInsId(String insId) {
        this.insId = insId;
    }

    /**
     * 更新日時を取得します.
     * @return 更新日時
     */
    public LocalDateTime getUpdDate() {
        return updDate;
    }

    /**
     * 更新日時を設定します.
     * @param updDate 更新日時
     */
    public void setUpdDate(LocalDateTime updDate) {
        this.updDate = updDate;
    }

    /**
     * 更新IDを取得します.
     * @return 更新ID
     */
    public String getUpdId() {
        return updId;
    }

    /**
     * 更新IDを設定します.
     * @param updId 更新ID
     */
    public void setUpdId(String updId) {
        this.updId = updId;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}