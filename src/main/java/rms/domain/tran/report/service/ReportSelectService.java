package rms.domain.tran.report.service;

import java.util.List;

import rms.domain.com.repository.TReportDao;
import rms.domain.tran.report.entity.ReportSearchConditionEntity;
import rms.domain.tran.report.entity.ReportSearchResultEntity;
import rms.domain.tran.report.repository.ReportSelectDao;
import rms.web.base.SearchResultEntity;
import rms.web.com.utils.PageInfo;
import rms.web.com.utils.SelectOptionsUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.seasar.doma.jdbc.SelectOptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 月報情報取得サービス
 * @author
 */
@Service
public class ReportSelectService extends rms.domain.com.abstracts.AbstractService {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportSelectService.class);

    /** 月報管理テーブルDao */
    @Autowired
    TReportDao tReportDao;

    /** 月報情報取得Dao */
    @Autowired
    ReportSelectDao reportSelectDao;

    /**
     * 月報情報一覧取得
     * @param condition
     * @param pageInfo
     * @return
     */
    public SearchResultEntity<ReportSearchResultEntity> getReportList(ReportSearchConditionEntity condition,
                                                                      PageInfo pageInfo) {
        // ページング設定
        SelectOptions options = SelectOptionsUtils.get(pageInfo);

        // 検索処理
        List<ReportSearchResultEntity> resultList = reportSelectDao.reportListByCondition(condition, options);
        logger.debug("検索結果(全件) -> {}件", options.getCount());
        logger.debug("検索結果 -> {}件", resultList.size());
        resultList.forEach(result -> logger.debug("{}", result));

        // 検索結果格納
        SearchResultEntity<ReportSearchResultEntity> searchResultEntity = new SearchResultEntity<>();
        searchResultEntity.setResultList(resultList);
        searchResultEntity.setCount(options.getCount());

        return searchResultEntity;

    }

}
