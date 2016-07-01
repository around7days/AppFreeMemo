package mms.com.doma.dao;

import mms.com.doma.entity.TReport;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 */
@Dao
@ConfigAutowireable
public interface TReportDao {

    /**
     * @param applicantId
     * @param targetYm
     * @return the TReport entity
     */
    @Select(ensureResult = true)
    TReport selectById(String applicantId, Integer targetYm);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert(excludeNull = true)
    int insert(TReport entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update(excludeNull = true)
    int update(TReport entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(TReport entity);
}