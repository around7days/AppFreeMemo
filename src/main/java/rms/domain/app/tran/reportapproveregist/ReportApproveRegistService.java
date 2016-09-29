package rms.domain.app.tran.reportapproveregist;

import java.io.IOException;

import rms.common.base.BusinessException;
import rms.common.entity.VTReport;

/**
 * 月報承認画面サービス
 * @author
 */
public interface ReportApproveRegistService {

    /**
     * 月報情報の取得
     * @param applyUserId
     * @param targetYm
     * @return
     */
    public VTReport getReportInfo(String applyUserId,
                                  String targetYm);

    /**
     * 月報情報の承認処理<br>
     * 補足：承認状況はメソッド内で自動設定
     * @param reportApproveRegistEntity
     * @throws BusinessException
     * @throws IOException
     */
    public void approve(ReportApproveRegistEntity reportApproveRegistEntity) throws BusinessException, IOException;
}
