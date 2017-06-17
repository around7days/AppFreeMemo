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
    @NotNull(message = "年月：{NotNull.message}", groups = { Apply.class })
    private Integer targetYm;
    /** 月報ファイル */
    @NotNullUploadFile(message = "月報：{NotNullUploadFile.message}", groups = { Apply.class, ReApply.class })
    private MultipartFile file;
    /** 公開有無 */
    private String publishFlg;
    /** 承認者ID1 */
    private String approveUserId1;
    /** 承認者ID2 */
    private String approveUserId2;
    /** 承認者ID3 */
    private String approveUserId3;
    /** 承認者ID4 */
    private String approveUserId4;
    /** 承認者名1 */
    private String approveUserNm1;
    /** 承認者名2 */
    private String approveUserNm2;
    /** 承認者名3 */
    private String approveUserNm3;
    /** 承認者名4 */
    private String approveUserNm4;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getViewMode() {
        return viewMode;
    }

    public void setViewMode(String viewMode) {
        this.viewMode = viewMode;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getApplyUserNm() {
        return applyUserNm;
    }

    public void setApplyUserNm(String applyUserNm) {
        this.applyUserNm = applyUserNm;
    }

    public Integer getTargetYm() {
        return targetYm;
    }

    public void setTargetYm(Integer targetYm) {
        this.targetYm = targetYm;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getPublishFlg() {
        return publishFlg;
    }

    public void setPublishFlg(String publishFlg) {
        this.publishFlg = publishFlg;
    }

    public String getApproveUserId1() {
        return approveUserId1;
    }

    public void setApproveUserId1(String approveUserId1) {
        this.approveUserId1 = approveUserId1;
    }

    public String getApproveUserId2() {
        return approveUserId2;
    }

    public void setApproveUserId2(String approveUserId2) {
        this.approveUserId2 = approveUserId2;
    }

    public String getApproveUserId3() {
        return approveUserId3;
    }

    public void setApproveUserId3(String approveUserId3) {
        this.approveUserId3 = approveUserId3;
    }

    public String getApproveUserId4() {
        return approveUserId4;
    }

    public void setApproveUserId4(String approveUserId4) {
        this.approveUserId4 = approveUserId4;
    }

    public String getApproveUserNm1() {
        return approveUserNm1;
    }

    public void setApproveUserNm1(String approveUserNm1) {
        this.approveUserNm1 = approveUserNm1;
    }

    public String getApproveUserNm2() {
        return approveUserNm2;
    }

    public void setApproveUserNm2(String approveUserNm2) {
        this.approveUserNm2 = approveUserNm2;
    }

    public String getApproveUserNm3() {
        return approveUserNm3;
    }

    public void setApproveUserNm3(String approveUserNm3) {
        this.approveUserNm3 = approveUserNm3;
    }

    public String getApproveUserNm4() {
        return approveUserNm4;
    }

    public void setApproveUserNm4(String approveUserNm4) {
        this.approveUserNm4 = approveUserNm4;
    }

}
