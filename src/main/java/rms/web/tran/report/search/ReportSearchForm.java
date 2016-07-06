package rms.web.tran.report.search;

import java.util.List;

import rms.com.page.PageInfo;

/**
 * 月報状況一覧画面フォーム
 * @author
 */
public class ReportSearchForm extends rms.com.abstracts.AbstractForm {

    /** 検索条件 */
    private ReportSearchConditionForm condition = new ReportSearchConditionForm();
    /** ページ情報 */
    private PageInfo pageInfo = new PageInfo();
    /** 検索結果リスト */
    private List<SearchReportEntity> resultList;

    /**
     * 検索条件を取得します。
     * @return 検索条件
     */
    public ReportSearchConditionForm getCondition() {
        return condition;
    }

    /**
     * 検索条件を設定します。
     * @param condition 検索条件
     */
    public void setCondition(ReportSearchConditionForm condition) {
        this.condition = condition;
    }

    /**
     * ページ情報を取得します。
     * @return ページ情報
     */
    public PageInfo getPageInfo() {
        return pageInfo;
    }

    /**
     * ページ情報を設定します。
     * @param pageInfo ページ情報
     */
    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    /**
     * 検索結果リストを取得します。
     * @return 検索結果リスト
     */
    public List<SearchReportEntity> getResultList() {
        return resultList;
    }

    /**
     * 検索結果リストを設定します。
     * @param resultList 検索結果リスト
     */
    public void setResultList(List<SearchReportEntity> resultList) {
        this.resultList = resultList;
    }
}