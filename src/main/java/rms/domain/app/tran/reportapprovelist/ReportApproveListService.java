package rms.domain.app.tran.reportapprovelist;

import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rms.common.utils.PageInfo;
import rms.common.utils.SelectOptionsUtils;
import rms.domain.app.shared.entity.SearchResultEntity;

/**
 * 月報承認状況一覧画面サービス
 * @author
 */
@Service
public class ReportApproveListService extends rms.common.abstracts.AbstractService {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApproveListService.class);

    /** 月報情報取得Dao */
    @Autowired
    ReportApproveListDao dao;

    /**
     * 月報情報一覧取得（承認者用）
     * @param condition
     * @param pageInfo
     * @return
     */
    public SearchResultEntity<ReportApproveListEntityResult> search(ReportApproveListEntityCondition condition,
                                                                    PageInfo pageInfo) {
        // ページ情報の生成
        SelectOptions options = SelectOptionsUtils.get(pageInfo);

        // 検索処理
        List<ReportApproveListEntityResult> resultList = dao.reportApproveListByCondition(condition, options);
        logger.debug("検索結果(全件) -> {}件", options.getCount());
        logger.debug("検索結果 -> {}件", resultList.size());
        resultList.forEach(result -> logger.debug("{}", result));

        // 検索結果格納
        SearchResultEntity<ReportApproveListEntityResult> resultEntity = new SearchResultEntity<>();
        resultEntity.setResultList(resultList);
        resultEntity.setCount(options.getCount());

        return resultEntity;
    }

}
