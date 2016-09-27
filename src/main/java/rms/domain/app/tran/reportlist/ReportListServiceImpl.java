package rms.domain.app.tran.reportlist;

import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rms.common.utils.PageInfo;
import rms.common.utils.SelectOptionsUtils;
import rms.domain.app.shared.entity.SearchResultEntity;

/**
 * 月報一覧画面サービス
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportListServiceImpl implements ReportListService {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportListServiceImpl.class);

    /** 月報情報取得Dao */
    @Autowired
    ReportListDao reportListDao;

    /**
     * 月報情報一覧取得
     * @param condition
     * @param pageInfo
     * @return
     */
    @Override
    public SearchResultEntity<ReportListEntityResult> search(ReportListEntityCondition condition,
                                                             PageInfo pageInfo) {
        // ページ情報の生成
        SelectOptions options = SelectOptionsUtils.get(pageInfo);

        // 検索処理
        List<ReportListEntityResult> resultList = reportListDao.reportListByCondition(condition, options);
        logger.debug("検索結果(全件) -> {}件", options.getCount());
        logger.debug("検索結果 -> {}件", resultList.size());
        resultList.forEach(result -> logger.debug("{}", result));

        // 検索結果格納
        SearchResultEntity<ReportListEntityResult> resultEntity = new SearchResultEntity<>();
        resultEntity.setResultList(resultList);
        resultEntity.setCount(options.getCount());

        return resultEntity;
    }

}
