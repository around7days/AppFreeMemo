package rms.common.entity;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.Version;

/**
 * TReportApproveFlowクラス
 */
@Entity(listener = TReportApproveFlowListener.class)
@Table(name = "t_report_approve_flow")
public class TReportApproveFlow {
    /** 申請者ID */
    @Id
    @Column(name = "apply_user_id")
    private String applyUserId;
    /** 対象年月 */
    @Id
    @Column(name = "target_ym")
    private Integer targetYm;
    /** 承認SEQ */
    @Id
    @Column(name = "approve_seq")
    private Integer approveSeq;
    /** 承認者ID */
    @Column(name = "approve_user_id")
    private String approveUserId;
    /** 承認日 */
    @Column(name = "approve_date")
    private LocalDateTime approveDate;
    /** コメント */
    @Column(name = "comment")
    private String comment;
    /** バージョン */
    @Version
    @Column(name = "version")
    private Integer version;
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

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public Integer getTargetYm() {
        return targetYm;
    }

    public void setTargetYm(Integer targetYm) {
        this.targetYm = targetYm;
    }

    public Integer getApproveSeq() {
        return approveSeq;
    }

    public void setApproveSeq(Integer approveSeq) {
        this.approveSeq = approveSeq;
    }

    public String getApproveUserId() {
        return approveUserId;
    }

    public void setApproveUserId(String approveUserId) {
        this.approveUserId = approveUserId;
    }

    public LocalDateTime getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(LocalDateTime approveDate) {
        this.approveDate = approveDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    public LocalDateTime getInsDate() {
        return insDate;
    }

    public void setInsDate(LocalDateTime insDate) {
        this.insDate = insDate;
    }

    public String getInsId() {
        return insId;
    }

    public void setInsId(String insId) {
        this.insId = insId;
    }

    public LocalDateTime getUpdDate() {
        return updDate;
    }

    public void setUpdDate(LocalDateTime updDate) {
        this.updDate = updDate;
    }

    public String getUpdId() {
        return updId;
    }

    public void setUpdId(String updId) {
        this.updId = updId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}