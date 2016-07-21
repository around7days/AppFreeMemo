package rms.domain.com.repository;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import rms.domain.com.entity.TReport;
import org.seasar.doma.jdbc.NoResultException;
import org.seasar.doma.jdbc.OptimisticLockException;
import org.seasar.doma.jdbc.SelectOptions;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * TReportDaoクラス
 */
@Dao
@ConfigAutowireable
public interface TReportDao {

    /**
     * 1件取得
     * @param applicantId
     * @param targetYear
     * @param targetMonth
     * @return the TReport entity
     */
    @Select
    TReport selectById(String applicantId, Integer targetYear, Integer targetMonth);

    /**
     * 1件取得
     * @param applicantId
     * @param targetYear
     * @param targetMonth
     * @param options
     * @return the TReport entity
     */
    @Select
    TReport selectById(String applicantId, Integer targetYear, Integer targetMonth, SelectOptions options);

    /**
     * 1件取得
     * @param applicantId
     * @param targetYear
     * @param targetMonth
     * @param version
     * @throws NoResultException
     * @return the TReport entity
     */
    @Select(ensureResult = true)
    TReport selectByIdAndVersion(String applicantId, Integer targetYear, Integer targetMonth, Integer version) throws NoResultException;

    /**
     * 挿入
     * @param entity
     * @return affected rows
     */
    @Insert(excludeNull = true)
    int insert(TReport entity);


    /**
     * 更新（楽観的排他制御）<br>
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Update(excludeNull = true)
    int update(TReport entity) throws OptimisticLockException;

    /**
     * 更新
     * @param entity
     * @return affected rows
     */
    @Update(excludeNull = true, ignoreVersion = true)
    int updateNoOptimisticLockException(TReport entity);

    /**
     * 削除
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(TReport entity);
}