package rms.domain.app.tran.reportlist;

import rms.common.utils.PageInfo;
import rms.common.utils.SearchResultDto;

/**
 * 月報一覧画面サービスインタフェース
 * @author
 */
public interface ReportListService {

    /**
     * 月報情報一覧取得
     * @param condition
     * @param pageInfo
     * @return
     */
    public SearchResultDto<ReportListEntityResult> search(ReportListDtoCondition condition,
                                                          PageInfo pageInfo);

}
