package mms.uniq.tran.report.search;

import java.util.List;

import mms.com.page.PageInfo;

/**
 * 月報状況一覧画面フォーム
 * @author
 */
public class ReportSearchForm extends mms.com.abstracts.AbstractForm {

    /** 検索条件 */
    private ReportSearchConditionForm condition = new ReportSearchConditionForm();
    /** ページ情報 */
    private PageInfo pageInfo = new PageInfo();
    /** 検索結果リスト */
    private List<ReportSearchResultForm> resultList;

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
    public List<ReportSearchResultForm> getResultList() {
        return resultList;
    }

    /**
     * 検索結果リストを設定します。
     * @param resultList 検索結果リスト
     */
    public void setResultList(List<ReportSearchResultForm> resultList) {
        this.resultList = resultList;
    }
}

/**
 * 月報状況一覧（検索条件）画面フォーム
 * @author
 */
class ReportSearchConditionForm extends mms.com.abstracts.AbstractForm {

    /** 申請者ID */
    private String applicantId;
    /** 申請者名 */
    private String applicantNm;
    /** 承認状況：未承認 */
    private String statusUnApprove;
    /** 承認状況：承認済み */
    private String statusApprove;

    /**
     * 申請者IDを取得します。
     * @return 申請者ID
     */
    public String getApplicantId() {
        return applicantId;
    }

    /**
     * 申請者IDを設定します。
     * @param applicantId 申請者ID
     */
    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    /**
     * 申請者名を取得します。
     * @return 申請者名
     */
    public String getApplicantNm() {
        return applicantNm;
    }

    /**
     * 申請者名を設定します。
     * @param applicantNm 申請者名
     */
    public void setApplicantNm(String applicantNm) {
        this.applicantNm = applicantNm;
    }

    /**
     * 承認状況：未承認を取得します。
     * @return 承認状況：未承認
     */
    public String getStatusUnApprove() {
        return statusUnApprove;
    }

    /**
     * 承認状況：未承認を設定します。
     * @param statusUnApprove 承認状況：未承認
     */
    public void setStatusUnApprove(String statusUnApprove) {
        this.statusUnApprove = statusUnApprove;
    }

    /**
     * 承認状況：承認済みを取得します。
     * @return 承認状況：承認済み
     */
    public String getStatusApprove() {
        return statusApprove;
    }

    /**
     * 承認状況：承認済みを設定します。
     * @param statusApprove 承認状況：承認済み
     */
    public void setStatusApprove(String statusApprove) {
        this.statusApprove = statusApprove;
    }
}

/**
 * 月報状況一覧（検索結果）画面フォーム
 * @author
 */
class ReportSearchResultForm extends mms.com.abstracts.AbstractForm {

    /** 年月 */
    private String targetYm;
    /** 申請者ID */
    private String applicantId;
    /** 申請者名 */
    private String applicantNm;
    /** 部署 */
    private String department;
    /** 承認状況 */
    private String status;
    /** 承認状況名称 */
    private String statusNm;
    /** 月報ファイルパス */
    private String filePath;

    /**
     * 年月を取得します。
     * @return 年月
     */
    public String getTargetYm() {
        return targetYm;
    }

    /**
     * 年月を設定します。
     * @param targetYm 年月
     */
    public void setTargetYm(String targetYm) {
        this.targetYm = targetYm;
    }

    /**
     * 申請者IDを取得します。
     * @return 申請者ID
     */
    public String getApplicantId() {
        return applicantId;
    }

    /**
     * 申請者IDを設定します。
     * @param applicantId 申請者ID
     */
    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    /**
     * 申請者名を取得します。
     * @return 申請者名
     */
    public String getApplicantNm() {
        return applicantNm;
    }

    /**
     * 申請者名を設定します。
     * @param applicantNm 申請者名
     */
    public void setApplicantNm(String applicantNm) {
        this.applicantNm = applicantNm;
    }

    /**
     * 部署を取得します。
     * @return 部署
     */
    public String getDepartment() {
        return department;
    }

    /**
     * 部署を設定します。
     * @param department 部署
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * 承認状況を取得します。
     * @return 承認状況
     */
    public String getStatus() {
        return status;
    }

    /**
     * 承認状況を設定します。
     * @param status 承認状況
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 承認状況名称を取得します。
     * @return 承認状況名称
     */
    public String getStatusNm() {
        return statusNm;
    }

    /**
     * 承認状況名称を設定します。
     * @param statusNm 承認状況名称
     */
    public void setStatusNm(String statusNm) {
        this.statusNm = statusNm;
    }

    /**
     * 月報ファイルパスを取得します。
     * @return 月報ファイルパス
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 月報ファイルパスを設定します。
     * @param filePath 月報ファイルパス
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}