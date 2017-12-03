package rms.domain.app.tran.reportapprovelist;

import java.time.LocalDateTime;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import rms.common.consts.MCodeConst;

/**
 * 月報承認一覧（検索結果）クラス
 */
@Entity
public class ReportApproveListResultEntity extends rms.common.abstracts.AbstractEntity {

    /** 承認者ID */
    @Column(name = "approve_user_id")
    private String approveUserId;

    /** 申請者ID */
    @Column(name = "apply_user_id")
    private String applyUserId;

    /** 申請者名 */
    @Column(name = "apply_user_nm")
    private String applyUserNm;

    /** 対象年月 */
    @Column(name = "target_ym")
    private Integer targetYm;

    /** 申請日 */
    @Column(name = "apply_date")
    private LocalDateTime applyDate;

    /** 部署略称 */
    @Column(name = "department_rnm")
    private String departmentRnm;

    /** 承認状況 */
    @Column(name = "status")
    private String status;

    /** 承認状況名称 */
    @Column(name = "status_nm")
    private String statusNm;

    /** 承認者ID1 */
    @Column(name = "approve_user_id1")
    private String approveUserId1;

    /** 承認者名1 */
    @Column(name = "approve_user_nm1")
    private String approveUserNm1;

    /** 承認者ID2 */
    @Column(name = "approve_user_id2")
    private String approveUserId2;

    /** 承認者名2 */
    @Column(name = "approve_user_nm2")
    private String approveUserNm2;

    /** 承認者ID3 */
    @Column(name = "approve_user_id3")
    private String approveUserId3;

    /** 承認者名3 */
    @Column(name = "approve_user_nm3")
    private String approveUserNm3;

    /** 承認者ID4 */
    @Column(name = "approve_user_id4")
    private String approveUserId4;

    /** 承認者名4 */
    @Column(name = "approve_user_nm4")
    private String approveUserNm4;

    public String getApproveUserId() {
        return approveUserId;
    }

    public void setApproveUserId(String approveUserId) {
        this.approveUserId = approveUserId;
    }

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

    public String getDepartmentRnm() {
        return departmentRnm;
    }

    public void setDepartmentRnm(String departmentRnm) {
        this.departmentRnm = departmentRnm;
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

    public String getApproveUserId4() {
        return approveUserId4;
    }

    public void setApproveUserId4(String approveUserId4) {
        this.approveUserId4 = approveUserId4;
    }

    public String getApproveUserNm4() {
        return approveUserNm4;
    }

    public void setApproveUserNm4(String approveUserNm4) {
        this.approveUserNm4 = approveUserNm4;
    }

    // TODO ここに書くべきではない？検索条件にしたい場合はやっぱりSQLで下記判定を行う必要がある。
    /**
     * 月報操作フラグ
     * @return true:操作可能 false:操作不可
     */
    public boolean isOperateFlg() {
        boolean flg = false;

        if (this.approveUserId.equals(this.approveUserId1)) {
            // 操作者が承認者１の場合
            switch (this.getStatus()) {
            case MCodeConst.A001_Y01: // 承認待ち１
                flg = true;
            }
        } else if (this.approveUserId.equals(this.approveUserId2)) {
            // 操作者が承認者２の場合
            switch (this.getStatus()) {
            case MCodeConst.A001_Y02: // 承認待ち２
                flg = true;
            }
        } else if (this.approveUserId.equals(this.approveUserId3)) {
            // 操作者が承認者３の場合
            switch (this.getStatus()) {
            case MCodeConst.A001_Y03: // 承認待ち３
                flg = true;
            }
        } else if (this.approveUserId.equals(this.approveUserId4)) {
            // 操作者が承認者４の場合
            switch (this.getStatus()) {
            case MCodeConst.A001_Y04: // 承認待ち４
                flg = true;
            }
        }

        return flg;
    }

    /**
     * 月報DLフラグ
     * @return true:DL可能 false:DL不可
     */
    public boolean isReportDLFlg() {
        boolean flg = false;

        if (this.approveUserId.equals(this.approveUserId1)) {
            // 操作者が承認者１の場合
            switch (this.getStatus()) {
            case MCodeConst.A001_Y01: // 承認待ち１
            case MCodeConst.A001_Y02: // 承認待ち２
            case MCodeConst.A001_Y03: // 承認待ち３
            case MCodeConst.A001_Y04: // 承認待ち４
            case MCodeConst.A001_N01: // 否認１
            case MCodeConst.A001_N02: // 否認２
            case MCodeConst.A001_N03: // 否認３
            case MCodeConst.A001_N04: // 否認４
            case MCodeConst.A001_ZZZ: // 承認済み
                flg = true;
            }
        } else if (this.approveUserId.equals(this.approveUserId2)) {
            // 操作者が承認者２の場合
            switch (this.getStatus()) {
            case MCodeConst.A001_Y02: // 承認待ち２
            case MCodeConst.A001_Y03: // 承認待ち３
            case MCodeConst.A001_Y04: // 承認待ち４
            case MCodeConst.A001_N02: // 否認２
            case MCodeConst.A001_N03: // 否認３
            case MCodeConst.A001_N04: // 否認４
            case MCodeConst.A001_ZZZ: // 承認済み
                flg = true;
            }
        } else if (this.approveUserId.equals(this.approveUserId3)) {
            // 操作者が承認者３の場合
            switch (this.getStatus()) {
            case MCodeConst.A001_Y03: // 承認待ち３
            case MCodeConst.A001_Y04: // 承認待ち４
            case MCodeConst.A001_N03: // 否認３
            case MCodeConst.A001_N04: // 否認４
            case MCodeConst.A001_ZZZ: // 承認済み
                flg = true;
            }
        } else if (this.approveUserId.equals(this.approveUserId4)) {
            // 操作者が承認者４の場合
            switch (this.getStatus()) {
            case MCodeConst.A001_Y04: // 承認待ち４
            case MCodeConst.A001_N04: // 否認４
            case MCodeConst.A001_ZZZ: // 承認済み
                flg = true;
            }
        }

        return flg;
    }
}