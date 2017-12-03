package rms.domain.app.tran.reportapplylist;

/**
 * 月報申請状況一覧（検索条件）クラス
 * @author
 */
public class ReportApplyListDtoCondition extends rms.common.abstracts.AbstractDto {

    /* 変数宣言 ------------------------------------------------------------- */
    /** 申請者ID */
    private String applyUserId;

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }
}
