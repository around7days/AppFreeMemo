package rms.domain.app.tran.reportapprovelist;

/**
 * 月報承認状況一覧（検索条件）クラス
 * @author
 */
public class ReportApproveListDto extends rms.common.abstracts.AbstractDto {

    /* 変数宣言 ------------------------------------------------------------- */
    /** 承認者ID */
    private String approveUserId;
    /** 対象年月 */
    private Integer targetYm;

    public String getApproveUserId() {
        return approveUserId;
    }

    public void setApproveUserId(String approveUserId) {
        this.approveUserId = approveUserId;
    }

    public Integer getTargetYm() {
        return targetYm;
    }

    public void setTargetYm(Integer targetYm) {
        this.targetYm = targetYm;
    }

}
