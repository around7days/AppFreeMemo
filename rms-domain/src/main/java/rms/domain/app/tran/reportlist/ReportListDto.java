package rms.domain.app.tran.reportlist;

/**
 * 月報一覧（検索条件）クラス
 * @author
 */
public class ReportListDto extends rms.common.abstracts.AbstractDto {

    /* 変数宣言 ------------------------------------------------------------- */
    /** 対象年月 */
    private Integer targetYm;
    /** 申請者ID */
    private String applyUserId;
    /** 申請者名 */
    private String applyUserNm;

    public Integer getTargetYm() {
        return targetYm;
    }

    public void setTargetYm(Integer targetYm) {
        this.targetYm = targetYm;
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
}
