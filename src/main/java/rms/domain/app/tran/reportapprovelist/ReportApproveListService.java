package rms.domain.app.tran.reportapprovelist;

import rms.common.dto.SearchResultDto;
import rms.common.utils.PageInfo;

/**
 * 月報承認状況一覧画面サービスインタフェース
 * @author
 */
public interface ReportApproveListService {

    /**
     * 画面初期表示処理
     * @return
     */
    public ReportApproveListDtoCondition initDisplay();

    /**
     * 月報情報一覧取得（承認者用）
     * @param condition
     * @param pageInfo
     * @return
     */
    public SearchResultDto<ReportApproveListEntityResult> search(ReportApproveListDtoCondition condition,
                                                                 PageInfo pageInfo);

}
