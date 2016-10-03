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
     * @param applyUserId
     * @return
     */
    public ReportApplyRegistDto getInitInsertReportInfo(String applyUserId);

    /**
     * 初期表示用月報情報の生成（更新時）
     * @param applyUserId
     * @param targetYm
     * @return
     */
    public ReportApplyRegistDto getInitUpdateReportInfo(String applyUserId,
                                                        Integer targetYm);

    /**
     * 月報情報の申請処理<br>
     * @param reportApplyRegistEntity
     * @throws IOException
     * @throws BusinessException
     */
    public void apply(ReportApplyRegistDto reportApplyRegistEntity) throws IOException, BusinessException;

    /**
     * 月報情報の再申請処理<br>
     * @param reportApplyRegistEntity
     * @throws IOException
     * @throws BusinessException
     */
    public void reApply(ReportApplyRegistDto reportApplyRegistEntity) throws IOException, BusinessException;

}
