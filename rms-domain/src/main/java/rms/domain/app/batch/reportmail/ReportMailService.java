package rms.domain.app.batch.reportmail;

/**
 * 月報メール配信バッチサービス
 * @author
 */
public interface ReportMailService {

    /**
     * 承認完了メール配信
     * @throws Exception
     */
    public void sendmailApproved() throws Exception;
}
