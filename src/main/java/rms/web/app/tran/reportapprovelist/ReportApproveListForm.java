package rms.web.app.tran.reportapprovelist;

import java.util.List;

import javax.validation.Valid;

import rms.common.utils.PageInfo;
import rms.domain.app.tran.reportapprovelist.ReportApproveListEntityResult;

/**
 * 月報承認状況一覧画面フォーム
 * @author
 */
public class ReportApproveListForm extends rms.common.abstracts.AbstractForm {

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
    private ReportApproveListFormCondition condition = new ReportApproveListFormCondition();
    /** ページ情報 */
    private PageInfo pageInfo = new PageInfo();
    /** 検索結果リスト */
    private List<ReportApproveListEntityResult> resultList;
    /** 検索結果チェックボックス選択リスト */
    // TODO 未実装
    // @NotNull(message = "ダウンロードする月報を選択して下さい", groups = { BulkDownload.class })
    private Integer[] reportDLCheck;

    /* getter/setter -------------------------------------------------------- */
    /**
     * 検索条件を取得します。
     * @return 検索条件
     */
    public ReportApproveListFormCondition getCondition() {
        return condition;
    }

    /**
     * 検索条件を設定します。
     * @param condition 検索条件
     */
    public void setCondition(ReportApproveListFormCondition condition) {
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
    public List<ReportApproveListEntityResult> getResultList() {
        return resultList;
    }

    /**
     * 検索結果リストを設定します。
     * @param resultList 検索結果リスト
     */
    public void setResultList(List<ReportApproveListEntityResult> resultList) {
        this.resultList = resultList;
    }

    /**
     * 検索結果チェックボックス選択リストを取得します。
     * @return 検索結果チェックボックス選択リスト
     */
    public Integer[] getReportDLCheck() {
        return reportDLCheck;
    }

    /**
     * 検索結果チェックボックス選択リストを設定します。
     * @param reportDLCheck 検索結果チェックボックス選択リスト
     */
    public void setReportDLCheck(Integer[] reportDLCheck) {
        this.reportDLCheck = reportDLCheck;
    }
}