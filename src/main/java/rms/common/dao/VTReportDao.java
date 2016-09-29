package rms.common.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import rms.common.entity.VTReport;

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
     * @return the VTReport entity
     */
    @Select
    VTReport selectById(String applyUserId,
                        Integer targetYm);

    /**
     * 1件取得
     * @param applyUserId
     * @param targetYm
     * @param options
     * @return the VTReport entity
     */
    @Select
    VTReport selectById(String applyUserId,
                        String targetYm,
                        SelectOptions options);

}