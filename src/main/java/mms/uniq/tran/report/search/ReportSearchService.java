package mms.uniq.tran.report.search;

import org.seasar.doma.jdbc.SelectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mms.com.doma.dao.TReportDao;
import mms.com.page.PageInfo;
import mms.com.utils.SelectOptionsUtil;

/**
 * 月報状況一覧画面サービス
 * @author
 */
@Service
public class ReportSearchService extends mms.com.abstracts.AbstractService {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportSearchService.class);

    /** 月報管理テーブルDao */
    @Autowired
    TReportDao tReportDao;

    /** 月報状況一覧画面Dao */
    @Autowired
    ReportSearchDao reportSearchDao;

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

        //        // 検索処理
        //        List<MUser> result = reportSearchDao.searchUser(form, options);
        //        logger.debug("検索結果(全件)：{}件", options.getCount());
        //        logger.debug("検索結果：{}件", result.size());
        //        result.forEach(obj -> logger.debug(ToStringBuilder.reflectionToString(obj)));
        //
        //        // 検索結果格納
        //        pageInfo.setTotalSize(options.getCount());
        //        form.setResultList(result);

    }

    /**
     * ダウンロード処理
     * @param form
     */
    public void download(ReportSearchForm form) {

    }

    /**
     * 一括ダウンロード処理
     * @param form
     */
    public void downloadBulk(ReportSearchForm form) {

    }

}
