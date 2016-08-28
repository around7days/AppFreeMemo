package rms.web.app.tran.reportapplylist;

import java.util.List;

import rms.common.utils.PageInfo;
import rms.domain.app.tran.reportapplylist.ReportApplyListEntityResult;

/**
 * 月報一覧画面フォーム
 * @author
 */
public class ReportApplyListForm extends rms.common.abstracts.AbstractForm {

    /* 変数宣言 ------------------------------------------------------------- */
    /** ページ情報 */
    private PageInfo pageInfo = new PageInfo();
    /** 検索結果リスト */
    private List<ReportApplyListEntityResult> resultList;

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
    public List<ReportApplyListEntityResult> getResultList() {
        return resultList;
    }

    /**
     * 検索結果リストを設定します。
     * @param resultList 検索結果リスト
     */
    public void setResultList(List<ReportApplyListEntityResult> resultList) {
        this.resultList = resultList;
    }
}