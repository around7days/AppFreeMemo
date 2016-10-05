package rms.web.app.tran.reportapplyregist;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import rms.common.validator.NotNullUploadFile;

/**
 * 月報申請画面フォーム
 * @author
 */
public class ReportApplyRegistForm extends rms.common.abstracts.AbstractForm {

    /* 入力チェック宣言 ----------------------------------------------------- */
    //@formatter:off
    /** 入力チェック：申請 */
    protected static interface Apply{};
    /** 入力チェック：再申請 */
    protected static interface ReApply{};
    //@formatter:on

    /* 定数宣言 ------------------------------------------------------------- */
    /** 画面表示モード：申請 */
    public static final String VIEW_MODE_APPLY = "apply";
    /** 画面表示モード：再申請 */
    public static final String VIEW_MODE_REAPPLY = "reApply";

    /* 排他制御用 ----------------------------------------------------------- */
    /** 月報テーブル 排他制御用バージョン */
    private Integer version;

    /* 変数宣言 ------------------------------------------------------------- */
    /** 画面表示モード */
    private String viewMode;

    /** 申請者ID */
    private String applyUserId;
    /** 申請者名 */
    private String applyUserNm;
    /** 年月 */
    @NotNull(message = "年月は{NotNull.message}", groups = { Apply.class })
    private Integer targetYm;
    /** 月報ファイル */
    @NotNullUploadFile(message = "月報は{NotNullUploadFile.message}", groups = { Apply.class, ReApply.class })
    private MultipartFile file;
    /** 公開有無 */
    private String publishFlg;
    /** 承認者ID1 */
    private String approveUserId1;
    /** 承認者ID2 */
    private String approveUserId2;
    /** 承認者ID3 */
    private String approveUserId3;
    /** 承認者名1 */
    private String approveUserNm1;
    /** 承認者名2 */
    private String approveUserNm2;
    /** 承認者名3 */
    private String approveUserNm3;

    /* getter/setter -------------------------------------------------------- */

    /**
     * 月報テーブル 排他制御用バージョンを取得します。
     * @return 月報テーブル 排他制御用バージョン
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 月報テーブル 排他制御用バージョンを設定します。
     * @param version 月報テーブル 排他制御用バージョン
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

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
     * 申請者IDを取得します。
     * @return 申請者ID
     */
    public String getApplyUserId() {
        return applyUserId;
    }

    /**
     * 申請者IDを設定します。
     * @param applyUserId 申請者ID
     */
    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    /**
     * 申請者名を取得します。
     * @return 申請者名
     */
    public String getApplyUserNm() {
        return applyUserNm;
    }

    /**
     * 申請者名を設定します。
     * @param applyUserNm 申請者名
     */
    public void setApplyUserNm(String applyUserNm) {
        this.applyUserNm = applyUserNm;
    }

    /**
     * 年月を取得します。
     * @return 年月
     */
    public Integer getTargetYm() {
        return targetYm;
    }

    /**
     * 年月を設定します。
     * @param targetYm 年月
     */
    public void setTargetYm(Integer targetYm) {
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
     * 承認者ID1を取得します。
     * @return 承認者ID1
     */
    public String getApproveUserId1() {
        return approveUserId1;
    }

    /**
     * 承認者ID1を設定します。
     * @param approveUserId1 承認者ID1
     */
    public void setApproveUserId1(String approveUserId1) {
        this.approveUserId1 = approveUserId1;
    }

    /**
     * 承認者ID2を取得します。
     * @return 承認者ID2
     */
    public String getApproveUserId2() {
        return approveUserId2;
    }

    /**
     * 承認者ID2を設定します。
     * @param approveUserId2 承認者ID2
     */
    public void setApproveUserId2(String approveUserId2) {
        this.approveUserId2 = approveUserId2;
    }

    /**
     * 承認者ID3を取得します。
     * @return 承認者ID3
     */
    public String getApproveUserId3() {
        return approveUserId3;
    }

    /**
     * 承認者ID3を設定します。
     * @param approveUserId3 承認者ID3
     */
    public void setApproveUserId3(String approveUserId3) {
        this.approveUserId3 = approveUserId3;
    }

    /**
     * 承認者名1を取得します。
     * @return 承認者名1
     */
    public String getApproveUserNm1() {
        return approveUserNm1;
    }

    /**
     * 承認者名1を設定します。
     * @param approveUserNm1 承認者名1
     */
    public void setApproveUserNm1(String approveUserNm1) {
        this.approveUserNm1 = approveUserNm1;
    }

    /**
     * 承認者名2を取得します。
     * @return 承認者名2
     */
    public String getApproveUserNm2() {
        return approveUserNm2;
    }

    /**
     * 承認者名2を設定します。
     * @param approveUserNm2 承認者名2
     */
    public void setApproveUserNm2(String approveUserNm2) {
        this.approveUserNm2 = approveUserNm2;
    }

    /**
     * 承認者名3を取得します。
     * @return 承認者名3
     */
    public String getApproveUserNm3() {
        return approveUserNm3;
    }

    /**
     * 承認者名3を設定します。
     * @param approveUserNm3 承認者名3
     */
    public void setApproveUserNm3(String approveUserNm3) {
        this.approveUserNm3 = approveUserNm3;
    }
}
