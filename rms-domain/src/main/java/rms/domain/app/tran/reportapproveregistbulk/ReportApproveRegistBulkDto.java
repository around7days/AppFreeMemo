package rms.domain.app.tran.reportapproveregistbulk;

/**
 * 月報一括承認実行結果クラス
 */
public class ReportApproveRegistBulkDto extends rms.common.abstracts.AbstractDto {

    /* 変数宣言 ------------------------------------------------------------- */
    /** 月報ファイル名 */
    private String fileNm;
    /** 対象年月 */
    private Integer targetYm;
    /** 申請者ID */
    private String applyUserId;
    /** 申請者名 */
    private String applyUserNm;
    /** 結果 */
    private String status;
    /** コメント */
    private String comment;

    public String getFileNm() {
        return fileNm;
    }

    public void setFileNm(String fileNm) {
        this.fileNm = fileNm;
    }

    public Integer getTargetYm() {
        return targetYm;
    }

    public void setTargetYm(Integer targetYm) {
        this.targetYm = targetYm;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
