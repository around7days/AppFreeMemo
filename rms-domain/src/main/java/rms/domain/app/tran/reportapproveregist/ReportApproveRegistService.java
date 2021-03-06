package rms.domain.app.tran.reportapproveregist;

import java.io.IOException;

import rms.common.exception.BusinessException;

/**
 * 月報承認画面サービス
 * @author
 */
public interface ReportApproveRegistService {

    /**
     * 画面初期表示処理
     * @param applyUserId
     * @param targetYm
     * @return
     */
    public ReportApproveRegistDto initDisplay(String applyUserId,
                                              Integer targetYm);

    /**
     * 月報情報の承認処理<br>
     * @param dto
     * @throws BusinessException
     * @throws IOException
     */
    public void approve(ReportApproveRegistDto dto) throws BusinessException, IOException;

    /**
     * 月報情報の差戻処理<br>
     * @param dto
     * @throws BusinessException
     * @throws IOException
     */
    public void remand(ReportApproveRegistDto dto) throws BusinessException, IOException;

    /**
     * 月報情報の否認処理<br>
     * @param dto
     * @throws BusinessException
     * @throws IOException
     */
    public void deny(ReportApproveRegistDto dto) throws BusinessException, IOException;
}
