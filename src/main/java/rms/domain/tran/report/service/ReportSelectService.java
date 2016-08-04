package rms.domain.tran.report.service;

import java.util.List;

import rms.domain.com.entity.VTReport;
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

    /** 月報情報取得Dao */
    @Autowired
    ReportSelectDao reportSelectDao;

    /**
     * 月報情報の取得
     * @param applyUserId
     * @param targetYm
     * @return
     */
    public VTReport getReportInfo(String applyUserId,
                                  String targetYm) {
        // 月報情報の取得
        VTReport entity = reportSelectDao.reportById(applyUserId, Integer.valueOf(targetYm));
        logger.debug("取得情報 -> {}", entity);

        return entity;
    }

    /**
     * 月報情報一覧取得
     * @param condition
     * @param pageInfo
     * @return
     */
    public SearchResultEntity<ReportSearchResultEntity> getReportList(ReportSearchConditionEntity condition,
                                                                      PageInfo pageInfo) {
        // ページ情報の生成
        SelectOptions options = SelectOptionsUtils.get(pageInfo);

        // 検索処理
        List<ReportSearchResultEntity> resultList = reportSelectDao.reportListByCondition(condition, options);
        logger.debug("検索結果(全件) -> {}件", options.getCount());
        logger.debug("検索結果 -> {}件", resultList.size());
        resultList.forEach(result -> logger.debug("{}", result));

        // 検索結果格納
        SearchResultEntity<ReportSearchResultEntity> resultEntity = new SearchResultEntity<>();
        resultEntity.setResultList(resultList);
        resultEntity.setCount(options.getCount());

        return resultEntity;

    }

}
