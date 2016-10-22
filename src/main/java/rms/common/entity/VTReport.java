package rms.common.entity;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Table;
import org.seasar.doma.Version;

/**
 * VTReportクラス
 */
@Entity
@Table(name = "v_t_report")
public class VTReport {
    /** 申請者ID */
    @Column(name = "apply_user_id")
    private String applyUserId;
    /** ユーザ名 */
    @Column(name = "apply_user_nm")
    private String applyUserNm;
    /** 対象年月 */
    @Column(name = "target_ym")
    private Integer targetYm;
    /** 申請日 */
    @Column(name = "apply_date")
    private LocalDateTime applyDate;
    /** 公開有無 コードマスタ：B001 */
    @Column(name = "publish_flg")
    private String publishFlg;
    /** 公開有無名称 */
    @Column(name = "publish_flg_nm")
    private String publishFlgNm;
    /** 承認者ID１ */
    @Column(name = "approve_user_id1")
    private String approveUserId1;
    /** 承認者名１ */
    @Column(name = "approve_user_nm1")
    private String approveUserNm1;
    /** 承認日１ */
    @Column(name = "approve_date1")
    private LocalDateTime approveDate1;
    /** 承認者ID２ */
    @Column(name = "approve_user_id2")
    private String approveUserId2;
    /** 承認者名２ */
    @Column(name = "approve_user_nm2")
    private String approveUserNm2;
    /** 承認日２ */
    @Column(name = "approve_date2")
    private LocalDateTime approveDate2;
    /** 承認者ID３ */
    @Column(name = "approve_user_id3")
    private String approveUserId3;
    /** 承認者名３ */
    @Column(name = "approve_user_nm3")
    private String approveUserNm3;
    /** 承認日３ */
    @Column(name = "approve_date3")
    private LocalDateTime approveDate3;
    /** 月報ファイルパス */
    @Column(name = "file_path")
    private String filePath;
    /** コメント */
    @Column(name = "apply_user_comment")
    private String applyUserComment;
    /** 承認状況 コードマスタ：A001 */
    @Column(name = "status")
    private String status;
    /** 承認状況名称 */
    @Column(name = "status_nm")
    private String statusNm;
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

    public String getApplyUserNm() {
        return applyUserNm;
    }

    public void setApplyUserNm(String applyUserNm) {
        this.applyUserNm = applyUserNm;
    }

    public Integer getTargetYm() {
        return targetYm;
    }

    public void setTargetYm(Integer targetYm) {
        this.targetYm = targetYm;
    }

    public LocalDateTime getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDateTime applyDate) {
        this.applyDate = applyDate;
    }

    public String getPublishFlg() {
        return publishFlg;
    }

    public void setPublishFlg(String publishFlg) {
        this.publishFlg = publishFlg;
    }

    public String getPublishFlgNm() {
        return publishFlgNm;
    }

    public void setPublishFlgNm(String publishFlgNm) {
        this.publishFlgNm = publishFlgNm;
    }

    public String getApproveUserId1() {
        return approveUserId1;
    }

    public void setApproveUserId1(String approveUserId1) {
        this.approveUserId1 = approveUserId1;
    }

    public String getApproveUserNm1() {
        return approveUserNm1;
    }

    public void setApproveUserNm1(String approveUserNm1) {
        this.approveUserNm1 = approveUserNm1;
    }

    public LocalDateTime getApproveDate1() {
        return approveDate1;
    }

    public void setApproveDate1(LocalDateTime approveDate1) {
        this.approveDate1 = approveDate1;
    }

    public String getApproveUserId2() {
        return approveUserId2;
    }

    public void setApproveUserId2(String approveUserId2) {
        this.approveUserId2 = approveUserId2;
    }

    public String getApproveUserNm2() {
        return approveUserNm2;
    }

    public void setApproveUserNm2(String approveUserNm2) {
        this.approveUserNm2 = approveUserNm2;
    }

    public LocalDateTime getApproveDate2() {
        return approveDate2;
    }

    public void setApproveDate2(LocalDateTime approveDate2) {
        this.approveDate2 = approveDate2;
    }

    public String getApproveUserId3() {
        return approveUserId3;
    }

    public void setApproveUserId3(String approveUserId3) {
        this.approveUserId3 = approveUserId3;
    }

    public String getApproveUserNm3() {
        return approveUserNm3;
    }

    public void setApproveUserNm3(String approveUserNm3) {
        this.approveUserNm3 = approveUserNm3;
    }

    public LocalDateTime getApproveDate3() {
        return approveDate3;
    }

    public void setApproveDate3(LocalDateTime approveDate3) {
        this.approveDate3 = approveDate3;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getApplyUserComment() {
        return applyUserComment;
    }

    public void setApplyUserComment(String applyUserComment) {
        this.applyUserComment = applyUserComment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusNm() {
        return statusNm;
    }

    public void setStatusNm(String statusNm) {
        this.statusNm = statusNm;
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