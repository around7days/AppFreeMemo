package rms.common.model;

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

    /**
     * 申請者IDを取得します。
     * @return 申請者ID
     */
    public String getApplyUserId() {
        return applyUserId;
    }

    /**
     * 申請者IDを設定します。
     * @param applyUserId 申請者ID
     */
    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    /**
     * ユーザ名を取得します。
     * @return ユーザ名
     */
    public String getApplyUserNm() {
        return applyUserNm;
    }

    /**
     * ユーザ名を設定します。
     * @param applyUserNm ユーザ名
     */
    public void setApplyUserNm(String applyUserNm) {
        this.applyUserNm = applyUserNm;
    }

    /**
     * 対象年月を取得します。
     * @return 対象年月
     */
    public Integer getTargetYm() {
        return targetYm;
    }

    /**
     * 対象年月を設定します。
     * @param targetYm 対象年月
     */
    public void setTargetYm(Integer targetYm) {
        this.targetYm = targetYm;
    }

    /**
     * 申請日を取得します。
     * @return 申請日
     */
    public LocalDateTime getApplyDate() {
        return applyDate;
    }

    /**
     * 申請日を設定します。
     * @param applyDate 申請日
     */
    public void setApplyDate(LocalDateTime applyDate) {
        this.applyDate = applyDate;
    }

    /**
     * 公開有無 コードマスタ：B001を取得します。
     * @return 公開有無 コードマスタ：B001
     */
    public String getPublishFlg() {
        return publishFlg;
    }

    /**
     * 公開有無 コードマスタ：B001を設定します。
     * @param publishFlg 公開有無 コードマスタ：B001
     */
    public void setPublishFlg(String publishFlg) {
        this.publishFlg = publishFlg;
    }

    /**
     * 公開有無名称を取得します。
     * @return 公開有無名称
     */
    public String getPublishFlgNm() {
        return publishFlgNm;
    }

    /**
     * 公開有無名称を設定します。
     * @param publishFlgNm 公開有無名称
     */
    public void setPublishFlgNm(String publishFlgNm) {
        this.publishFlgNm = publishFlgNm;
    }

    /**
     * 承認者ID１を取得します。
     * @return 承認者ID１
     */
    public String getApproveUserId1() {
        return approveUserId1;
    }

    /**
     * 承認者ID１を設定します。
     * @param approveUserId1 承認者ID１
     */
    public void setApproveUserId1(String approveUserId1) {
        this.approveUserId1 = approveUserId1;
    }

    /**
     * 承認者名１を取得します。
     * @return 承認者名１
     */
    public String getApproveUserNm1() {
        return approveUserNm1;
    }

    /**
     * 承認者名１を設定します。
     * @param approveUserNm1 承認者名１
     */
    public void setApproveUserNm1(String approveUserNm1) {
        this.approveUserNm1 = approveUserNm1;
    }

    /**
     * 承認日１を取得します。
     * @return 承認日１
     */
    public LocalDateTime getApproveDate1() {
        return approveDate1;
    }

    /**
     * 承認日１を設定します。
     * @param approveDate1 承認日１
     */
    public void setApproveDate1(LocalDateTime approveDate1) {
        this.approveDate1 = approveDate1;
    }

    /**
     * 承認者ID２を取得します。
     * @return 承認者ID２
     */
    public String getApproveUserId2() {
        return approveUserId2;
    }

    /**
     * 承認者ID２を設定します。
     * @param approveUserId2 承認者ID２
     */
    public void setApproveUserId2(String approveUserId2) {
        this.approveUserId2 = approveUserId2;
    }

    /**
     * 承認者名２を取得します。
     * @return 承認者名２
     */
    public String getApproveUserNm2() {
        return approveUserNm2;
    }

    /**
     * 承認者名２を設定します。
     * @param approveUserNm2 承認者名２
     */
    public void setApproveUserNm2(String approveUserNm2) {
        this.approveUserNm2 = approveUserNm2;
    }

    /**
     * 承認日２を取得します。
     * @return 承認日２
     */
    public LocalDateTime getApproveDate2() {
        return approveDate2;
    }

    /**
     * 承認日２を設定します。
     * @param approveDate2 承認日２
     */
    public void setApproveDate2(LocalDateTime approveDate2) {
        this.approveDate2 = approveDate2;
    }

    /**
     * 承認者ID３を取得します。
     * @return 承認者ID３
     */
    public String getApproveUserId3() {
        return approveUserId3;
    }

    /**
     * 承認者ID３を設定します。
     * @param approveUserId3 承認者ID３
     */
    public void setApproveUserId3(String approveUserId3) {
        this.approveUserId3 = approveUserId3;
    }

    /**
     * 承認者名３を取得します。
     * @return 承認者名３
     */
    public String getApproveUserNm3() {
        return approveUserNm3;
    }

    /**
     * 承認者名３を設定します。
     * @param approveUserNm3 承認者名３
     */
    public void setApproveUserNm3(String approveUserNm3) {
        this.approveUserNm3 = approveUserNm3;
    }

    /**
     * 承認日３を取得します。
     * @return 承認日３
     */
    public LocalDateTime getApproveDate3() {
        return approveDate3;
    }

    /**
     * 承認日３を設定します。
     * @param approveDate3 承認日３
     */
    public void setApproveDate3(LocalDateTime approveDate3) {
        this.approveDate3 = approveDate3;
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
     * コメントを取得します。
     * @return コメント
     */
    public String getApplyUserComment() {
        return applyUserComment;
    }

    /**
     * コメントを設定します。
     * @param applyUserComment コメント
     */
    public void setApplyUserComment(String applyUserComment) {
        this.applyUserComment = applyUserComment;
    }

    /**
     * 承認状況 コードマスタ：A001を取得します。
     * @return 承認状況 コードマスタ：A001
     */
    public String getStatus() {
        return status;
    }

    /**
     * 承認状況 コードマスタ：A001を設定します。
     * @param status 承認状況 コードマスタ：A001
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 承認状況名称を取得します。
     * @return 承認状況名称
     */
    public String getStatusNm() {
        return statusNm;
    }

    /**
     * 承認状況名称を設定します。
     * @param statusNm 承認状況名称
     */
    public void setStatusNm(String statusNm) {
        this.statusNm = statusNm;
    }

    /**
     * バージョンを取得します。
     * @return バージョン
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * バージョンを設定します。
     * @param version バージョン
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 削除フラグを取得します。
     * @return 削除フラグ
     */
    public Integer getDelFlg() {
        return delFlg;
    }

    /**
     * 削除フラグを設定します。
     * @param delFlg 削除フラグ
     */
    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * 登録日時を取得します。
     * @return 登録日時
     */
    public LocalDateTime getInsDate() {
        return insDate;
    }

    /**
     * 登録日時を設定します。
     * @param insDate 登録日時
     */
    public void setInsDate(LocalDateTime insDate) {
        this.insDate = insDate;
    }

    /**
     * 登録IDを取得します。
     * @return 登録ID
     */
    public String getInsId() {
        return insId;
    }

    /**
     * 登録IDを設定します。
     * @param insId 登録ID
     */
    public void setInsId(String insId) {
        this.insId = insId;
    }

    /**
     * 更新日時を取得します。
     * @return 更新日時
     */
    public LocalDateTime getUpdDate() {
        return updDate;
    }

    /**
     * 更新日時を設定します。
     * @param updDate 更新日時
     */
    public void setUpdDate(LocalDateTime updDate) {
        this.updDate = updDate;
    }

    /**
     * 更新IDを取得します。
     * @return 更新ID
     */
    public String getUpdId() {
        return updId;
    }

    /**
     * 更新IDを設定します。
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