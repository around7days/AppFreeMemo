package rms.web.tran.report.approve.list;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 月報承認状況一覧（検索条件）画面フォーム
 * @author
 */
public class ReportApproveListConditionForm extends rms.web.com.abstracts.AbstractForm {

    /* 変数宣言 ------------------------------------------------------------- */
    /** 対象年月 */
    @NotEmpty(message = "対象年月は{NotEmpty.message}")
    private String targetYm;

    /* getter/setter -------------------------------------------------------- */
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
