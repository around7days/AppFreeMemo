package rms.domain.app.tran.reportapplyregist;

import java.io.IOException;

import rms.common.base.BusinessException;

/**
 * 月報申請画面サービス
 * @author
 */
public interface ReportApplyRegistService {

    /**
     * 初期表示用月報情報の生成（新規時）
     * @param userId
     * @return
     */
    public ReportApplyRegistDto getInitInsertReportInfo(String userId);

    /**
     * 月報情報の申請処理<br>
     * 補足：承認状況はメソッド内で自動設定
     * @param reportApplyRegistEntity
     * @throws IOException
     * @throws BusinessException
     * @throws NumberFormatException
     */
    public void apply(ReportApplyRegistDto reportApplyRegistEntity) throws IOException, NumberFormatException,
                                                                    BusinessException;

}
