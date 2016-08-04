package rms.web.tran.report.apply.regist;

import rms.com.validator.annotation.UploadFileNotEmpty;

import org.springframework.web.multipart.MultipartFile;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 月報申請画面フォーム
 * @author
 */
public class ReportApplyRegistForm extends rms.web.com.abstracts.AbstractForm {

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

    /** 年月 */
    @NotEmpty(message = "年月は{NotEmpty.message}", groups = { Insert.class, Update.class })
    private String targetYm;
    /** 月報ファイル */
    @UploadFileNotEmpty(message = "月報は{UploadFileNotEmpty.message}", groups = { Insert.class, Update.class })
    private MultipartFile file;
    /** 公開有無 */
    private String publishFlg;
    /** 承認者１ID */
    private String approveUserId1;
    /** 承認者２ID */
    private String approveUserId2;
    /** 承認者３ID */
    private String approveUserId3;
    /** 承認者１名 */
    private String approveUserNm1;
    /** 承認者２名 */
    private String approveUserNm2;
    /** 承認者３名 */
    private String approveUserNm3;

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
     * 公開有無を取得します。
     * @return 公開有無
     */
    public String getPublishFlg() {
        return publishFlg;
    }

    /**
     * 公開有無を設定します。
     * @param publishFlg 公開有無
     */
    public void setPublishFlg(String publishFlg) {
        this.publishFlg = publishFlg;
    }

    /**
     * 承認者１IDを取得します。
     * @return 承認者１ID
     */
    public String getApproveUserId1() {
        return approveUserId1;
    }

    /**
     * 承認者１IDを設定します。
     * @param approveUserId1 承認者１ID
     */
    public void setApproveUserId1(String approveUserId1) {
        this.approveUserId1 = approveUserId1;
    }

    /**
     * 承認者２IDを取得します。
     * @return 承認者２ID
     */
    public String getApproveUserId2() {
        return approveUserId2;
    }

    /**
     * 承認者２IDを設定します。
     * @param approveUserId2 承認者２ID
     */
    public void setApproveUserId2(String approveUserId2) {
        this.approveUserId2 = approveUserId2;
    }

    /**
     * 承認者３IDを取得します。
     * @return 承認者３ID
     */
    public String getApproveUserId3() {
        return approveUserId3;
    }

    /**
     * 承認者３IDを設定します。
     * @param approveUserId3 承認者３ID
     */
    public void setApproveUserId3(String approveUserId3) {
        this.approveUserId3 = approveUserId3;
    }

    /**
     * 承認者１名を取得します。
     * @return 承認者１名
     */
    public String getApproveUserNm1() {
        return approveUserNm1;
    }

    /**
     * 承認者１名を設定します。
     * @param approveUserNm1 承認者１名
     */
    public void setApproveUserNm1(String approveUserNm1) {
        this.approveUserNm1 = approveUserNm1;
    }

    /**
     * 承認者２名を取得します。
     * @return 承認者２名
     */
    public String getApproveUserNm2() {
        return approveUserNm2;
    }

    /**
     * 承認者２名を設定します。
     * @param approveUserNm2 承認者２名
     */
    public void setApproveUserNm2(String approveUserNm2) {
        this.approveUserNm2 = approveUserNm2;
    }

    /**
     * 承認者３名を取得します。
     * @return 承認者３名
     */
    public String getApproveUserNm3() {
        return approveUserNm3;
    }

    /**
     * 承認者３名を設定します。
     * @param approveUserNm3 承認者３名
     */
    public void setApproveUserNm3(String approveUserNm3) {
        this.approveUserNm3 = approveUserNm3;
    }
}
