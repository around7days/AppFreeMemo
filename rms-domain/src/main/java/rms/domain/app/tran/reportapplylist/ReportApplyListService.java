package rms.domain.app.tran.reportapplylist;

import rms.common.utils.PageInfo;
import rms.common.utils.SearchResultDto;

/**
 * 月報申請状況一覧画面サービスインタフェース
 * @author
 */
public interface ReportApplyListService {

    /**
     * 月報情報一覧取得（申請者用）
     * @param condition
     * @param pageInfo
     * @return
     */
    public SearchResultDto<ReportApplyListResultEntity> search(ReportApplyListDto condition,
                                                               PageInfo pageInfo);
}
