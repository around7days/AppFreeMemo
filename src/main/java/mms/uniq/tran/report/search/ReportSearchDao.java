package mms.uniq.tran.report.search;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

/**
 * 月報状況一覧画面Dao
 */
@Dao
@ConfigAutowireable
public interface ReportSearchDao {

    /**
     * 月報検索処理
     * @param form
     * @param options
     * @return
     */
    @Select
    List<SearchReportEntity> searchReport(ReportSearchConditionForm form,
                                          SelectOptions options);
}