package rms.domain.app.tran.reportapplylist;

import java.time.LocalDateTime;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

/**
 * 月報申請状況一覧（検索結果）クラス
 */
@Entity
public class ReportApplyListEntityResult extends rms.common.abstracts.AbstractEntity {

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

    /** 公開有無 */
    @Column(name = "publish_flg")
    private String publishFlg;

    /** コード名称 */
    @Column(name = "publish_flg_nm")
    private String publishFlgNm;

    /** 承認状況 */
    @Column(name = "status")
    private String status;

    /** コード名称 */
    @Column(name = "status_nm")
    private String statusNm;

    /** 承認者ID1 */
    @Column(name = "approve_user_id1")
    private String approveUserId1;

    /** ユーザ名 */
    @Column(name = "approve_user_nm1")
    private String approveUserNm1;

    /** 承認者ID2 */
    @Column(name = "approve_user_id2")
    private String approveUserId2;

    /** ユーザ名 */
    @Column(name = "approve_user_nm2")
    private String approveUserNm2;

    /** 承認者ID3 */
    @Column(name = "approve_user_id3")
    private String approveUserId3;

    /** ユーザ名 */
    @Column(name = "approve_user_nm3")
    private String approveUserNm3;

    /** 月報ファイルパス */
    @Column(name = "file_path")
    private String filePath;

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
     * 公開有無を取得します。
     * @return 公開有無
     */
    public String getPublishFlg() {
        return publishFlg;
    }

    /**
     * 公開有無を設定します。
     * @param publishFlg 公開有無
     */
    public void setPublishFlg(String publishFlg) {
        this.publishFlg = publishFlg;
    }

    /**
     * コード名称を取得します。
     * @return コード名称
     */
    public String getPublishFlgNm() {
        return publishFlgNm;
    }

    /**
     * コード名称を設定します。
     * @param publishFlgNm コード名称
     */
    public void setPublishFlgNm(String publishFlgNm) {
        this.publishFlgNm = publishFlgNm;
    }

    /**
     * 承認状況を取得します。
     * @return 承認状況
     */
    public String getStatus() {
        return status;
    }

    /**
     * 承認状況を設定します。
     * @param status 承認状況
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * コード名称を取得します。
     * @return コード名称
     */
    public String getStatusNm() {
        return statusNm;
    }

    /**
     * コード名称を設定します。
     * @param statusNm コード名称
     */
    public void setStatusNm(String statusNm) {
        this.statusNm = statusNm;
    }

    /**
     * 承認者ID1を取得します。
     * @return 承認者ID1
     */
    public String getApproveUserId1() {
        return approveUserId1;
    }

    /**
     * 承認者ID1を設定します。
     * @param approveUserId1 承認者ID1
     */
    public void setApproveUserId1(String approveUserId1) {
        this.approveUserId1 = approveUserId1;
    }

    /**
     * ユーザ名を取得します。
     * @return ユーザ名
     */
    public String getApproveUserNm1() {
        return approveUserNm1;
    }

    /**
     * ユーザ名を設定します。
     * @param approveUserNm1 ユーザ名
     */
    public void setApproveUserNm1(String approveUserNm1) {
        this.approveUserNm1 = approveUserNm1;
    }

    /**
     * 承認者ID2を取得します。
     * @return 承認者ID2
     */
    public String getApproveUserId2() {
        return approveUserId2;
    }

    /**
     * 承認者ID2を設定します。
     * @param approveUserId2 承認者ID2
     */
    public void setApproveUserId2(String approveUserId2) {
        this.approveUserId2 = approveUserId2;
    }

    /**
     * ユーザ名を取得します。
     * @return ユーザ名
     */
    public String getApproveUserNm2() {
        return approveUserNm2;
    }

    /**
     * ユーザ名を設定します。
     * @param approveUserNm2 ユーザ名
     */
    public void setApproveUserNm2(String approveUserNm2) {
        this.approveUserNm2 = approveUserNm2;
    }

    /**
     * 承認者ID3を取得します。
     * @return 承認者ID3
     */
    public String getApproveUserId3() {
        return approveUserId3;
    }

    /**
     * 承認者ID3を設定します。
     * @param approveUserId3 承認者ID3
     */
    public void setApproveUserId3(String approveUserId3) {
        this.approveUserId3 = approveUserId3;
    }

    /**
     * ユーザ名を取得します。
     * @return ユーザ名
     */
    public String getApproveUserNm3() {
        return approveUserNm3;
    }

    /**
     * ユーザ名を設定します。
     * @param approveUserNm3 ユーザ名
     */
    public void setApproveUserNm3(String approveUserNm3) {
        this.approveUserNm3 = approveUserNm3;
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

}