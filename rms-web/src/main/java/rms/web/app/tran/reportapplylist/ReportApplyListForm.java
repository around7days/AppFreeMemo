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
    private PageInfo pageInfo;
    /** 検索結果リスト */
    private List<ReportApplyListEntityResult> resultList;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<ReportApplyListEntityResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<ReportApplyListEntityResult> resultList) {
        this.resultList = resultList;
    }
}