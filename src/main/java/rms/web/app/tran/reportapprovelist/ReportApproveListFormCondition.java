package rms.web.app.tran.reportapprovelist;

import javax.validation.constraints.NotNull;

/**
 * 月報承認状況一覧（検索条件）画面フォーム
 * @author
 */
public class ReportApproveListFormCondition extends rms.common.abstracts.AbstractForm {

    /* 変数宣言 ------------------------------------------------------------- */
    /** 対象年月 */
    @NotNull(message = "対象年月は{NotEmpty.message}")
    private Integer targetYm;

    /* getter/setter -------------------------------------------------------- */
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
