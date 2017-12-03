package rms.web.app.tran.reportlist;

import java.util.List;

import javax.validation.Valid;

import rms.common.utils.PageInfo;
import rms.common.validator.NotNullArray;
import rms.domain.app.tran.reportlist.ReportListResultEntity;

/**
 * 月報一覧画面フォーム
 * @author
 */
public class ReportListForm extends rms.common.abstracts.AbstractForm {
    /* 入力チェック宣言 ----------------------------------------------------- */
    //@formatter:off
    /** 入力チェック：検索 */
    protected static interface Search{};
    /** 入力チェック：一括DL */
    protected static interface BulkDownload{};
    //@formatter:on

    /* 変数宣言 ------------------------------------------------------------- */
    /** 検索条件 */
    @Valid
    private ReportListConditionForm condition = new ReportListConditionForm();
    /** ページ情報 */
    private PageInfo pageInfo = new PageInfo(PageInfo.LIMIT_100);
    /** 検索結果リスト */
    private List<ReportListResultEntity> resultList;
    /** 検索結果チェックボックス選択リスト */
    @NotNullArray(message = "ダウンロードする月報を選択して下さい", groups = { BulkDownload.class })
    private Integer[] reportDLCheck;

    public ReportListConditionForm getCondition() {
        return condition;
    }

    public void setCondition(ReportListConditionForm condition) {
        this.condition = condition;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<ReportListResultEntity> getResultList() {
        return resultList;
    }

    public void setResultList(List<ReportListResultEntity> resultList) {
        this.resultList = resultList;
    }

    public Integer[] getReportDLCheck() {
        return reportDLCheck;
    }

    public void setReportDLCheck(Integer[] reportDLCheck) {
        this.reportDLCheck = reportDLCheck;
    }

}