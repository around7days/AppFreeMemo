package rms.domain.com.repository;

import rms.domain.com.entity.VTReport;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

/**
 * VTReportDaoクラス
 */
@Dao
@ConfigAutowireable
public interface VTReportDao {

    /**
     * 1件取得
     * @param applyUserId
     * @param targetYm
     * @return the TReport entity
     */
    @Select
    VTReport selectById(String applyUserId,
                        Integer targetYm);

    /**
     * 1件取得
     * @param applyUserId
     * @param targetYm
     * @param options
     * @return the TReport entity
     */
    @Select
    VTReport selectById(String applyUserId,
                        Integer targetYm,
                        SelectOptions options);

}