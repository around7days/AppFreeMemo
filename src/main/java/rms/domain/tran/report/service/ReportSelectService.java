package rms.domain.tran.report.service;

import java.util.List;

import rms.domain.com.entity.VTReport;
import rms.domain.com.repository.VTReportDao;
import rms.domain.tran.report.entity.ReportApplyListConditionEntity;
import rms.domain.tran.report.entity.ReportApplyListResultEntity;
import rms.domain.tran.report.entity.ReportApproveListConditionEntity;
import rms.domain.tran.report.entity.ReportApproveListResultEntity;
import rms.domain.tran.report.entity.ReportListConditionEntity;
import rms.domain.tran.report.entity.ReportListResultEntity;
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

    /** VTReportDao */
    @Autowired
    VTReportDao vTReportDao;

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
        VTReport entity = vTReportDao.selectById(applyUserId, Integer.valueOf(targetYm));
        logger.debug("取得情報 -> {}", entity);

        return entity;
    }

    /**
     * 月報情報一覧取得
     * @param condition
     * @param pageInfo
     * @return
     */
    public SearchResultEntity<ReportListResultEntity> getReportList(ReportListConditionEntity condition,
                                                                    PageInfo pageInfo) {
        // ページ情報の生成
        SelectOptions options = SelectOptionsUtils.get(pageInfo);

        // 検索処理
        List<ReportListResultEntity> resultList = reportSelectDao.reportListByCondition(condition, options);
        logger.debug("検索結果(全件) -> {}件", options.getCount());
        logger.debug("検索結果 -> {}件", resultList.size());
        resultList.forEach(result -> logger.debug("{}", result));

        // 検索結果格納
        SearchResultEntity<ReportListResultEntity> resultEntity = new SearchResultEntity<>();
        resultEntity.setResultList(resultList);
        resultEntity.setCount(options.getCount());

        return resultEntity;
    }

    /**
     * 月報情報一覧取得（申請者用）
     * @param condition
     * @param pageInfo
     * @return
     */
    public SearchResultEntity<ReportApplyListResultEntity> getReportApplyList(ReportApplyListConditionEntity condition,
                                                                              PageInfo pageInfo) {
        // ページ情報の生成
        SelectOptions options = SelectOptionsUtils.get(pageInfo);

        // 検索処理
        List<ReportApplyListResultEntity> resultList = reportSelectDao.reportApplyListByCondition(condition, options);
        logger.debug("検索結果(全件) -> {}件", options.getCount());
        logger.debug("検索結果 -> {}件", resultList.size());
        resultList.forEach(result -> logger.debug("{}", result));

        // 検索結果格納
        SearchResultEntity<ReportApplyListResultEntity> resultEntity = new SearchResultEntity<>();
        resultEntity.setResultList(resultList);
        resultEntity.setCount(options.getCount());

        return resultEntity;
    }

    /**
     * 月報情報一覧取得（承認者用）
     * @param condition
     * @param pageInfo
     * @return
     */
    public SearchResultEntity<ReportApproveListResultEntity> getReportApproveList(ReportApproveListConditionEntity condition,
                                                                                  PageInfo pageInfo) {
        // ページ情報の生成
        SelectOptions options = SelectOptionsUtils.get(pageInfo);

        // 検索処理
        List<ReportApproveListResultEntity> resultList = reportSelectDao.reportApproveListByCondition(condition,
                                                                                                      options);
        logger.debug("検索結果(全件) -> {}件", options.getCount());
        logger.debug("検索結果 -> {}件", resultList.size());
        resultList.forEach(result -> logger.debug("{}", result));

        // 検索結果格納
        SearchResultEntity<ReportApproveListResultEntity> resultEntity = new SearchResultEntity<>();
        resultEntity.setResultList(resultList);
        resultEntity.setCount(options.getCount());

        return resultEntity;
    }

}
