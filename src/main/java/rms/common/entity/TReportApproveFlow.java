package rms.common.entity;

import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.Version;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

    /**
     * 申請者IDを取得します.
     * @return 申請者ID
     */
    public String getApplyUserId() {
        return applyUserId;
    }

    /**
     * 申請者IDを設定します.
     * @param applyUserId 申請者ID
     */
    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    /**
     * 対象年月を取得します.
     * @return 対象年月
     */
    public Integer getTargetYm() {
        return targetYm;
    }

    /**
     * 対象年月を設定します.
     * @param targetYm 対象年月
     */
    public void setTargetYm(Integer targetYm) {
        this.targetYm = targetYm;
    }

    /**
     * 承認SEQを取得します.
     * @return 承認SEQ
     */
    public Integer getApproveSeq() {
        return approveSeq;
    }

    /**
     * 承認SEQを設定します.
     * @param approveSeq 承認SEQ
     */
    public void setApproveSeq(Integer approveSeq) {
        this.approveSeq = approveSeq;
    }

    /**
     * 承認者IDを取得します.
     * @return 承認者ID
     */
    public String getApproveUserId() {
        return approveUserId;
    }

    /**
     * 承認者IDを設定します.
     * @param approveUserId 承認者ID
     */
    public void setApproveUserId(String approveUserId) {
        this.approveUserId = approveUserId;
    }

    /**
     * 承認日を取得します.
     * @return 承認日
     */
    public LocalDateTime getApproveDate() {
        return approveDate;
    }

    /**
     * 承認日を設定します.
     * @param approveDate 承認日
     */
    public void setApproveDate(LocalDateTime approveDate) {
        this.approveDate = approveDate;
    }

    /**
     * コメントを取得します.
     * @return コメント
     */
    public String getComment() {
        return comment;
    }

    /**
     * コメントを設定します.
     * @param comment コメント
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * バージョンを取得します.
     * @return バージョン
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * バージョンを設定します.
     * @param version バージョン
     */
    public void setVersion(Integer version) {
        this.version = version;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}