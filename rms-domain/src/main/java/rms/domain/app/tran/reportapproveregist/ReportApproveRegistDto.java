package rms.domain.app.tran.reportapproveregist;

import org.springframework.web.multipart.MultipartFile;

/**
 * 月報承認クラス
 */
public class ReportApproveRegistDto extends rms.common.abstracts.AbstractDto {

    /* 排他制御用 ----------------------------------------------------------- */
    /** 月報テーブル 排他制御用バージョン */
    private Integer version;

    /* 変数宣言 ------------------------------------------------------------- */
    /** 申請者ID */
    private String applyUserId;
    /** 申請者名 */
    private String applyUserNm;
    /** 年月 */
    private Integer targetYm;
    /** 承認者月報ファイル */
    private MultipartFile file;
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
    /** 承認状況 */
    private String status;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
