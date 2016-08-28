package rms.domain.app.tran.reportapprovelist;

/**
 * 月報承認状況一覧（検索条件）Entity
 * @author
 */
public class ReportApproveListEntityCondition extends rms.common.abstracts.AbstractEntity {

    /* 変数宣言 ------------------------------------------------------------- */
    /** 承認者ID */
    private String approveUserId;
    /** 対象年月 */
    private String targetYm;

    /* getter/setter -------------------------------------------------------- */
    /**
     * 承認者IDを取得します。
     * @return 承認者ID
     */
    public String getApproveUserId() {
        return approveUserId;
    }

    /**
     * 承認者IDを設定します。
     * @param approveUserId 承認者ID
     */
    public void setApproveUserId(String approveUserId) {
        this.approveUserId = approveUserId;
    }

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

}
