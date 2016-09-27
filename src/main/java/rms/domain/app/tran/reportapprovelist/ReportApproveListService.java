package rms.domain.app.tran.reportapprovelist;

import rms.common.utils.PageInfo;
import rms.domain.app.shared.entity.SearchResultEntity;

/**
 * 月報承認状況一覧画面サービスインタフェース
 * @author
 */
public interface ReportApproveListService {

    /**
     * 月報情報一覧取得（承認者用）
     * @param condition
     * @param pageInfo
     * @return
     */
    public SearchResultEntity<ReportApproveListEntityResult> search(ReportApproveListEntityCondition condition,
                                                                    PageInfo pageInfo);

}
