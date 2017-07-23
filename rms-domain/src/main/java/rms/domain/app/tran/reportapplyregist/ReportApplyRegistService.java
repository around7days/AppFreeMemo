package rms.domain.app.tran.reportapplyregist;

import java.io.IOException;

import rms.common.exception.BusinessException;

/**
 * 月報申請画面サービス
 * @author
 */
public interface ReportApplyRegistService {

    /**
     * 画面初期表示処理（申請時）
     * @param applyUserId
     * @return
     */
    public ReportApplyRegistDto initDisplayApply(String applyUserId);

    /**
     * 画面初期表示処理（再申請時）
     * @param applyUserId
     * @param targetYm
     * @return
     */
    public ReportApplyRegistDto initDisplayReApply(String applyUserId,
                                                   Integer targetYm);

    /**
     * 月報情報の申請処理<br>
     * @param dto
     * @throws IOException
     * @throws BusinessException
     */
    public void apply(ReportApplyRegistDto dto) throws IOException, BusinessException;

    /**
     * 月報情報の再申請処理<br>
     * @param dto
     * @throws IOException
     * @throws BusinessException
     */
    public void reApply(ReportApplyRegistDto dto) throws IOException, BusinessException;

}
