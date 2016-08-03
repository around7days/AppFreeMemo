package rms.web.tran.report.search;

import java.util.List;

import rms.domain.tran.report.entity.ReportSearchResultEntity;
import rms.web.com.utils.PageInfo;

import javax.validation.Valid;

/**
 * 月報一覧画面フォーム
 * @author
 */
public class ReportSearchForm extends rms.web.com.abstracts.AbstractForm {

    /* 変数宣言 ------------------------------------------------------------- */
    /** 検索条件 */
    @Valid
    private ReportSearchConditionForm condition = new ReportSearchConditionForm();
    /** ページ情報 */
    private PageInfo pageInfo = new PageInfo();
    /** 検索結果リスト */
    private List<ReportSearchResultEntity> resultList;

    /* getter/setter -------------------------------------------------------- */
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
    public List<ReportSearchResultEntity> getResultList() {
        return resultList;
    }

    /**
     * 検索結果リストを設定します。
     * @param resultList 検索結果リスト
     */
    public void setResultList(List<ReportSearchResultEntity> resultList) {
        this.resultList = resultList;
    }
}