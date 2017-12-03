package rms.web.app.tran.reportlist;

/**
 * 月報一覧（検索条件）画面フォーム
 * @author
 */
public class ReportListFormCondition extends rms.common.abstracts.AbstractForm {

    /* 変数宣言 ------------------------------------------------------------- */
    /** 対象年月 */
    private Integer targetYm;

    public Integer getTargetYm() {
        return targetYm;
    }

    public void setTargetYm(Integer targetYm) {
        this.targetYm = targetYm;
    }
}
