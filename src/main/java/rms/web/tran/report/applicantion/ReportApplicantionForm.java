package rms.web.tran.report.applicantion;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import rms.com.validator.annotation.UploadFileNotEmpty;
import rms.web.com.utils.SelectOptionEntity;

/**
 * 月報申請画面フォーム
 * @author
 */
public class ReportApplicantionForm extends rms.web.com.abstracts.AbstractForm {

    /* 入力チェック宣言 ----------------------------------------------------- */
    //@formatter:off
    /** 入力チェック：新規 */
    protected static interface Insert{};
    /** 入力チェック：更新 */
    protected static interface Update{};
    //@formatter:on

    /* 定数宣言 ------------------------------------------------------------- */
    /** 画面表示モード：新規 */
    public static final String VIEW_MODE_INSERT = "insert";
    /** 画面表示モード：更新 */
    public static final String VIEW_MODE_UPDATE = "update";

    /* 変数宣言 ------------------------------------------------------------- */
    /** 画面表示モード */
    private String viewMode;

    /** 年月：年 */
    @NotEmpty(message = "年月：年は{NotEmpty.message}", groups = { Insert.class, Update.class })
    private String targetYear;
    /** 年月：月 */
    @NotEmpty(message = "年月：月は{NotEmpty.message}", groups = { Insert.class, Update.class })
    private String targetMonth;
    /** 月報ファイル */
    @UploadFileNotEmpty(message = "月報は{UploadFileNotEmpty.message}", groups = { Insert.class, Update.class })
    private MultipartFile file;
    /** 承認者１ID */
    private String approver1Id;
    /** 承認者２ID */
    private String approver2Id;
    /** 承認者３ID */
    @NotEmpty(message = "承認者３は{NotEmpty.message}", groups = { Insert.class, Update.class })
    private String approver3Id;

    /** 承認者リスト(selectbox用) */
    private List<SelectOptionEntity> approverList;

    /* getter/setter -------------------------------------------------------- */

    /**
     * 画面表示モードを取得します。
     * @return 画面表示モード
     */
    public String getViewMode() {
        return viewMode;
    }

    /**
     * 画面表示モードを設定します。
     * @param viewMode 画面表示モード
     */
    public void setViewMode(String viewMode) {
        this.viewMode = viewMode;
    }

    /**
     * 年月：年を取得します。
     * @return 年月：年
     */
    public String getTargetYear() {
        return targetYear;
    }

    /**
     * 年月：年を設定します。
     * @param targetYear 年月：年
     */
    public void setTargetYear(String targetYear) {
        this.targetYear = targetYear;
    }

    /**
     * 年月：月を取得します。
     * @return 年月：月
     */
    public String getTargetMonth() {
        return targetMonth;
    }

    /**
     * 年月：月を設定します。
     * @param targetMonth 年月：月
     */
    public void setTargetMonth(String targetMonth) {
        this.targetMonth = targetMonth;
    }

    /**
     * 月報ファイルを取得します。
     * @return 月報ファイル
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * 月報ファイルを設定します。
     * @param file 月報ファイル
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    /**
     * 承認者１IDを取得します。
     * @return 承認者１ID
     */
    public String getApprover1Id() {
        return approver1Id;
    }

    /**
     * 承認者１IDを設定します。
     * @param approver1Id 承認者１ID
     */
    public void setApprover1Id(String approver1Id) {
        this.approver1Id = approver1Id;
    }

    /**
     * 承認者２IDを取得します。
     * @return 承認者２ID
     */
    public String getApprover2Id() {
        return approver2Id;
    }

    /**
     * 承認者２IDを設定します。
     * @param approver2Id 承認者２ID
     */
    public void setApprover2Id(String approver2Id) {
        this.approver2Id = approver2Id;
    }

    /**
     * 承認者３IDを取得します。
     * @return 承認者３ID
     */
    public String getApprover3Id() {
        return approver3Id;
    }

    /**
     * 承認者３IDを設定します。
     * @param approver3Id 承認者３ID
     */
    public void setApprover3Id(String approver3Id) {
        this.approver3Id = approver3Id;
    }

    /**
     * 承認者リスト(selectbox用)を取得します。
     * @return 承認者リスト(selectbox用)
     */
    public List<SelectOptionEntity> getApproverList() {
        return approverList;
    }

    /**
     * 承認者リスト(selectbox用)を設定します。
     * @param approverList 承認者リスト(selectbox用)
     */
    public void setApproverList(List<SelectOptionEntity> approverList) {
        this.approverList = approverList;
    }

}
