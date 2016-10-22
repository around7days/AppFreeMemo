package rms.web.app.tran.reportlist;

import java.util.List;

import javax.validation.Valid;

import rms.common.utils.PageInfo;
import rms.domain.app.tran.reportlist.ReportListEntityResult;

/**
 * 月報一覧画面フォーム
 * @author
 */
public class ReportListForm extends rms.common.abstracts.AbstractForm {

    /* 変数宣言 ------------------------------------------------------------- */
    /** 検索条件 */
    @Valid
    private ReportListFormCondition condition = new ReportListFormCondition();
    /** ページ情報 */
    private PageInfo pageInfo = new PageInfo();
    /** 検索結果リスト */
    private List<ReportListEntityResult> resultList;

    public ReportListFormCondition getCondition() {
        return condition;
    }

    public void setCondition(ReportListFormCondition condition) {
        this.condition = condition;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<ReportListEntityResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<ReportListEntityResult> resultList) {
        this.resultList = resultList;
    }

}