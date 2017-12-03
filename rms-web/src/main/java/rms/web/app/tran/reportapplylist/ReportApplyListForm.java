package rms.web.app.tran.reportapplylist;

import java.util.List;

import rms.common.utils.PageInfo;
import rms.domain.app.tran.reportapplylist.ReportApplyListResultEntity;

/**
 * 月報一覧画面フォーム
 * @author
 */
public class ReportApplyListForm extends rms.common.abstracts.AbstractForm {

    /* 変数宣言 ------------------------------------------------------------- */
    /** ページ情報 */
    private PageInfo pageInfo = new PageInfo();
    /** 検索結果リスト */
    private List<ReportApplyListResultEntity> resultList;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<ReportApplyListResultEntity> getResultList() {
        return resultList;
    }

    public void setResultList(List<ReportApplyListResultEntity> resultList) {
        this.resultList = resultList;
    }
}