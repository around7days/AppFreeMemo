package rms.domain.tran.report.entity;

/**
 * 月報申請状況一覧（検索条件）Entity
 * @author
 */
public class ReportApplyListConditionEntity extends rms.domain.com.abstracts.AbstractEntity {

    /* 変数宣言 ------------------------------------------------------------- */
    /** 申請者ID */
    private String applyUserId;

    /* getter/setter -------------------------------------------------------- */
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
}
