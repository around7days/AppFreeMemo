package rms.domain.com.repository;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import rms.domain.com.entity.TReportHis;
import org.seasar.doma.jdbc.NoResultException;
import org.seasar.doma.jdbc.OptimisticLockException;
import org.seasar.doma.jdbc.SelectOptions;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * TReportHisDaoクラス
 */
@Dao
@ConfigAutowireable
public interface TReportHisDao {

    /**
     * 1件取得
     * @param applicantId
     * @param targetYear
     * @param targetMonth
     * @param seq
     * @return the TReportHis entity
     */
    @Select
    TReportHis selectById(String applicantId, Integer targetYear, Integer targetMonth, Integer seq);

    /**
     * 1件取得
     * @param applicantId
     * @param targetYear
     * @param targetMonth
     * @param seq
     * @param options
     * @return the TReportHis entity
     */
    @Select
    TReportHis selectById(String applicantId, Integer targetYear, Integer targetMonth, Integer seq, SelectOptions options);

    /**
     * 1件取得
     * @param applicantId
     * @param targetYear
     * @param targetMonth
     * @param seq
     * @param version
     * @throws NoResultException
     * @return the TReportHis entity
     */
    @Select(ensureResult = true)
    TReportHis selectByIdAndVersion(String applicantId, Integer targetYear, Integer targetMonth, Integer seq, Integer version) throws NoResultException;

    /**
     * 挿入
     * @param entity
     * @return affected rows
     */
    @Insert(excludeNull = true)
    int insert(TReportHis entity);


    /**
     * 更新（楽観的排他制御）
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Update(excludeNull = true)
    int update(TReportHis entity) throws OptimisticLockException;

    /**
     * 更新
     * @param entity
     * @return affected rows
     */
    @Update(excludeNull = true, ignoreVersion = true)
    int updateNoOptimisticLockException(TReportHis entity);

    /**
     * 削除
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(TReportHis entity);
}