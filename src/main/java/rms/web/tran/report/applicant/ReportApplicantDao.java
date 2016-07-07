package rms.web.tran.report.applicant;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import rms.web.com.entity.SelectOptionEntity;

/**
 * 月報申請画面Dao
 */
@Dao
@ConfigAutowireable
public interface ReportApplicantDao {

    /**
     * 承認者一覧の取得<br>
     * selectbox用
     * @return
     */
    @Select
    List<SelectOptionEntity> selectboxApprover();
}