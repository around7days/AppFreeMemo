package rms.domain.tran.report.repository;

import java.util.List;

import rms.domain.tran.report.entity.ReportSearchConditionEntity;
import rms.domain.tran.report.entity.ReportSearchResultEntity;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

/**
 * 月報情報取得Dao
 */
@Dao
@ConfigAutowireable
public interface ReportSelectDao {

    /**
     * 月報情報一覧の取得
     * @param condition
     * @param options
     * @return
     */
    @Select
    List<ReportSearchResultEntity> reportListByCondition(ReportSearchConditionEntity condition,
                                                         SelectOptions options);
}