package mms.com.doma.dao;

import mms.com.doma.entity.TReport;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * TReportDaoクラス
 */
@Dao
@ConfigAutowireable
public interface TReportDao {

    /**
     * selectById
     * @param applicantId
     * @param targetYm
     * @return the TReport entity
     */
    @Select(ensureResult = true)
    TReport selectById(String applicantId, Integer targetYm);

    /**
     * insert
     * @param entity
     * @return affected rows
     */
    @Insert(excludeNull = true)
    int insert(TReport entity);

    /**
     * udpate
     * @param entity
     * @return affected rows
     */
    @Update(excludeNull = true)
    int update(TReport entity);

    /**
     * delete
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(TReport entity);
}