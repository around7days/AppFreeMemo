package rms.domain.tran.report.repository;

import java.util.List;

import rms.domain.tran.report.entity.ReportApplyListConditionEntity;
import rms.domain.tran.report.entity.ReportApproveListConditionEntity;
import rms.domain.tran.report.entity.ReportListConditionEntity;
import rms.domain.tran.report.entity.ReportResultEntity;

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
    List<ReportResultEntity> reportListByCondition(ReportListConditionEntity condition,
                                                   SelectOptions options);

    /**
     * 月報情報一覧の取得（申請者用）
     * @param condition
     * @param options
     * @return
     */
    @Select
    List<ReportResultEntity> reportApplyListByCondition(ReportApplyListConditionEntity condition,
                                                        SelectOptions options);

    /**
     * 月報情報一覧の取得（承認者用）
     * @param condition
     * @param options
     * @return
     */
    @Select
    List<ReportResultEntity> reportApproveListByCondition(ReportApproveListConditionEntity condition,
                                                          SelectOptions options);

}