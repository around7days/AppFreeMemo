package rms.domain.tran.report.repository;

import java.util.List;

import rms.domain.tran.report.entity.ReportApprovalEntity;
import rms.domain.tran.report.entity.ReportSearchConditionEntity;
import rms.web.tran.report.approval.ReportApprovalForm;
import rms.web.tran.report.search.ReportSearchConditionForm;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

/**
 * 月報情報Dao
 */
@Dao
@ConfigAutowireable
public interface ReportDao {

    /**
     * 月報情報の取得
     * @param form
     * @return
     */
    @Select
    ReportApprovalEntity selectReport(ReportApprovalForm form);

    /**
     * 月報検索処理
     * @param form
     * @param options
     * @return
     */
    @Select
    List<ReportSearchConditionEntity> searchReport(ReportSearchConditionForm form,
                                                   SelectOptions options);
}