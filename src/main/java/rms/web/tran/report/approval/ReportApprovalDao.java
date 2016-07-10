package rms.web.tran.report.approval;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * 月報承認画面Dao
 */
@Dao
@ConfigAutowireable
public interface ReportApprovalDao {

    /**
     * 承認待ち情報の取得
     * @param form
     * @return
     */
    @Select
    ReportApprovalEntity select(ReportApprovalForm form);

    //    /**
    //     * 承認者一覧の取得<br>
    //     * selectbox用
    //     * @return
    //     */
    //    @Select
    //    List<SelectOptionEntity> selectboxApprover();
}