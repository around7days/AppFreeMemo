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

    /* getter/setter -------------------------------------------------------- */

    /**
     * 月報ファイル名を取得します。
     * @return 月報ファイル名
     */
    public String getFileNm() {
        return fileNm;
    }

    /**
     * 月報ファイル名を設定します。
     * @param fileNm 月報ファイル名
     */
    public void setFileNm(String fileNm) {
        this.fileNm = fileNm;
    }

    /**
     * 対象年月を取得します。
     * @return 対象年月
     */
    public Integer getTargetYm() {
        return targetYm;
    }

    /**
     * 対象年月を設定します。
     * @param targetYm 対象年月
     */
    public void setTargetYm(Integer targetYm) {
        this.targetYm = targetYm;
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
     * 結果を取得します。
     * @return 結果
     */
    public String getStatus() {
        return status;
    }

    /**
     * 結果を設定します。
     * @param status 結果
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * コメントを取得します。
     * @return コメント
     */
    public String getComment() {
        return comment;
    }

    /**
     * コメントを設定します。
     * @param comment コメント
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

}
