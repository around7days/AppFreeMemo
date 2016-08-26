package rms.web.tran.report.approve.regist;

import rms.com.validator.annotation.UploadFileNotEmpty;

import org.springframework.web.multipart.MultipartFile;

/**
 * 月報承認画面フォーム
 * @author
 */
public class ReportApproveRegistForm extends rms.web.com.abstracts.AbstractForm {

    /* 排他制御用 ----------------------------------------------------------- */
    /** 月報管理テーブル 排他制御用バージョン */
    private Integer lockVersion;

    /* 変数宣言 ------------------------------------------------------------- */
    /** 申請者ID */
    private String applyUserId;
    /** 申請者名 */
    private String applyUserNm;
    /** 年月 */
    private String targetYm;
    /** 月報ファイル */
    @UploadFileNotEmpty(message = "月報は{UploadFileNotEmpty.message}")
    private MultipartFile file;
    /** 公開有無名称 */
    private String publishFlgNm;
    /** 承認者名1 */
    private String approveUserNm1;
    /** 承認者名2 */
    private String approveUserNm2;
    /** 承認者名3 */
    private String approveUserNm3;

    /* getter/setter -------------------------------------------------------- */

    /**
     * 月報管理テーブル 排他制御用バージョンを取得します。
     * @return 月報管理テーブル 排他制御用バージョン
     */
    public Integer getLockVersion() {
        return lockVersion;
    }

    /**
     * 月報管理テーブル 排他制御用バージョンを設定します。
     * @param lockVersion 月報管理テーブル 排他制御用バージョン
     */
    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
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
     * 公開有無名称を取得します。
     * @return 公開有無名称
     */
    public String getPublishFlgNm() {
        return publishFlgNm;
    }

    /**
     * 公開有無名称を設定します。
     * @param publishFlgNm 公開有無名称
     */
    public void setPublishFlgNm(String publishFlgNm) {
        this.publishFlgNm = publishFlgNm;
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
