package rms.domain.app.tran.reportapplyregist;

import java.io.IOException;

import rms.common.base.BusinessException;
import rms.common.model.VMUser;
import rms.common.model.VTReport;

/**
 * 月報申請画面サービス
 * @author
 */
public interface ReportApplyRegistService {

    /**
     * 申請者のユーザ情報を取得
     * @param userId
     * @return
     */
    public VMUser getApplyUserInfo(String userId);

    /**
     * 月報情報の取得
     * @param applyUserId
     * @param targetYm
     * @return
     */
    public VTReport getReportInfo(String applyUserId,
                                  String targetYm);

    /**
     * 月報情報の申請処理<br>
     * 補足：承認状況はメソッド内で自動設定
     * @param reportApplyRegistEntity
     * @throws IOException
     * @throws BusinessException
     * @throws NumberFormatException
     */
    public void apply(ReportApplyRegistEntity reportApplyRegistEntity) throws IOException, NumberFormatException,
                                                                       BusinessException;

}
