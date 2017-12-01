package rms.domain.app.tran.reportapplylist;

import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rms.common.utils.PageInfo;
import rms.common.utils.SearchResultDto;
import rms.common.utils.SelectOptionsUtils;

/**
 * 月報申請状況一覧画面サービス
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportApplyListServiceImpl implements ReportApplyListService {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ReportApplyListServiceImpl.class);

    /** 月報情報取得Dao */
    @Autowired
    ReportApplyListDao dao;

    @Override
    public SearchResultDto<ReportApplyListEntityResult> search(ReportApplyListDtoCondition condition,
                                                               PageInfo pageInfo) {
        // ページ情報の生成
        SelectOptions options = SelectOptionsUtils.get(pageInfo);

        // 検索処理
        List<ReportApplyListEntityResult> resultList = dao.reportApplyListByCondition(condition, options);

        // 検索結果格納
        SearchResultDto<ReportApplyListEntityResult> resultDto = new SearchResultDto<>();
        resultDto.setResultList(resultList);
        resultDto.setCount(options.getCount());

        return resultDto;
    }
}
