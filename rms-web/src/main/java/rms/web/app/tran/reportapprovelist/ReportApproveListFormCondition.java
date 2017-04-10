package rms.web.app.tran.reportapprovelist;

import javax.validation.constraints.NotNull;

import rms.web.app.tran.reportapprovelist.ReportApproveListForm.Search;

/**
 * 月報承認状況一覧（検索条件）画面フォーム
 * @author
 */
public class ReportApproveListFormCondition extends rms.common.abstracts.AbstractForm {

    /* 変数宣言 ------------------------------------------------------------- */
    /** 対象年月 */
    @NotNull(message = "対象年月：{NotEmpty.message}", groups = { Search.class })
    private Integer targetYm;

    /* getter/setter -------------------------------------------------------- */
    public Integer getTargetYm() {
        return targetYm;
    }

    public void setTargetYm(Integer targetYm) {
        this.targetYm = targetYm;
    }
}
