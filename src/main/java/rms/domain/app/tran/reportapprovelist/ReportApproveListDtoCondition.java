package rms.domain.app.tran.reportapprovelist;

/**
 * 月報承認状況一覧（検索条件）クラス
 * @author
 */
public class ReportApproveListDtoCondition extends rms.common.abstracts.AbstractDto {

    /* 変数宣言 ------------------------------------------------------------- */
    /** 承認者ID */
    private String approveUserId;
    /** 対象年月 */
    private Integer targetYm;

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

}
