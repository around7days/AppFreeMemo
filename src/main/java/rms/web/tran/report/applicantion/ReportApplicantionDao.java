package rms.web.tran.report.applicantion;

import java.util.List;

import rms.web.com.utils.SelectOptionEntity;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * 月報申請画面Dao
 */
@Dao
@ConfigAutowireable
public interface ReportApplicantionDao {

    /**
     * 承認者一覧の取得<br>
     * selectbox用
     * @return
     */
    @Select
    List<SelectOptionEntity> selectboxApprover();
}