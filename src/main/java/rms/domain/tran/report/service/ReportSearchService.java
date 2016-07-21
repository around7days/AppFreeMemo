package rms.domain.tran.report.service;

import java.util.List;

import rms.com.base.PageInfo;
import rms.domain.com.repository.TReportDao;
import rms.domain.tran.report.entity.ReportSearchConditionEntity;
import rms.domain.tran.report.repository.ReportDao;
import rms.web.com.utils.SelectOptionsUtil;
import rms.web.tran.report.search.ReportSearchConditionForm;
import rms.web.tran.report.search.ReportSearchForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.seasar.doma.jdbc.SelectOptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 月報状況一覧画面サービス
 * @author
 */
@Service
public class ReportSearchService extends rms.com.abstracts.AbstractService {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportSearchService.class);

    /** 月報管理テーブルDao */
    @Autowired
    TReportDao tReportDao;

    /** 月報情報Dao */
    @Autowired
    ReportDao reportDao;

    /**
     * 初期処理
     * @param form
     */
    public void init(ReportSearchForm form) {
        // 検索条件の初期値設定
        ReportSearchConditionForm searchForm = form.getCondition();
        searchForm.setStatusUnApprove("on");
    }

    /**
     * 検索処理
     * @param form
     */
    public void search(ReportSearchForm form) {
        // ページング設定
        PageInfo pageInfo = form.getPageInfo();
        SelectOptions options = SelectOptionsUtil.get(pageInfo);

        // 検索処理
        List<ReportSearchConditionEntity> resultList = reportDao.searchReport(form.getCondition(), options);
        logger.debug("検索結果(全件) -> {}件", options.getCount());
        logger.debug("検索結果 -> {}件", resultList.size());
        resultList.forEach(result -> logger.debug(result.toString()));

        // 検索結果格納
        pageInfo.setTotalSize(options.getCount());
        form.setResultList(resultList);
    }

}
