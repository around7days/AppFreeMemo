package rms.domain.app.shared.service;

import rms.common.consts.Const.StatusExecKbn;

/**
 * 月報関連共通サービスインタフェース
 * @author
 */
public interface SharedReportService {

    /**
     * 処理後の承認状況を計算して返却
     * @param applyUserId 申請者ID
     * @param targetYm 対象年月
     * @param execKbn 承認状況処理区分[申請or承認or否認]
     * @return 承認状況
     */
    public String getNewStatus(String applyUserId,
                               Integer targetYm,
                               StatusExecKbn execKbn);

}