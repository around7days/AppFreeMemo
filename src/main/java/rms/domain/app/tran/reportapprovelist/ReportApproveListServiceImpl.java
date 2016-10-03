package rms.domain.app.tran.reportapprovelist;

import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rms.common.dto.SearchResultDto;
import rms.common.utils.PageInfo;
import rms.common.utils.SelectOptionsUtils;

/**
 * 月報承認状況一覧画面サービス
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportApproveListServiceImpl implements ReportApproveListService {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ReportApproveListServiceImpl.class);

    /** 月報情報取得Dao */
    @Autowired
    ReportApproveListDao dao;

    /**
     * 月報情報一覧取得（承認者用）
     * @param condition
     * @param pageInfo
     * @return
     */
    @Override
    public SearchResultDto<ReportApproveListEntityResult> search(ReportApproveListDtoCondition condition,
                                                                 PageInfo pageInfo) {
        // ページ情報の生成
        SelectOptions options = SelectOptionsUtils.get(pageInfo);

        // 検索処理
        List<ReportApproveListEntityResult> resultList = dao.reportApproveListByCondition(condition, options);

        // 検索結果格納
        SearchResultDto<ReportApproveListEntityResult> resultDto = new SearchResultDto<>();
        resultDto.setResultList(resultList);
        resultDto.setCount(options.getCount());

        return resultDto;
    }

}
