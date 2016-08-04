package rms.web.tran.report.apply.list;

import java.util.List;

import rms.domain.tran.report.entity.ReportSearchResultEntity;
import rms.web.com.utils.PageInfo;

/**
 * 月報一覧画面フォーム
 * @author
 */
public class ReportApplyListForm extends rms.web.com.abstracts.AbstractForm {

    /* 変数宣言 ------------------------------------------------------------- */
    /** ページ情報 */
    private PageInfo pageInfo = new PageInfo();
    /** 検索結果リスト */
    private List<ReportSearchResultEntity> resultList;

    /* getter/setter -------------------------------------------------------- */
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