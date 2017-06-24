package rms.domain.app.shared.dto;

/**
 * 提出用月報ファイル情報格納クラス<br>
 * @author
 */
public class SharedSubmitReportFileDto {

    /** 申請者ID */
    private String applyUserId;
    /** 年月 */
    private Integer targetYm;

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public Integer getTargetYm() {
        return targetYm;
    }

    public void setTargetYm(Integer targetYm) {
        this.targetYm = targetYm;
    }
}
