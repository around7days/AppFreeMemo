package rms.common.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.NoResultException;
import org.seasar.doma.jdbc.OptimisticLockException;
import org.seasar.doma.jdbc.SelectOptions;

import rms.common.entity.TReport;

/**
 * TReportDaoクラス
 */
@Dao
@ConfigAutowireable
public interface TReportDao {

    /* 自動生成メソッド ------------------------------------------------------------- */

    /**
     * 1件取得
     * @param applyUserId
     * @param targetYm
     * @return the TReport entity
     */
    @Select
    TReport selectById(String applyUserId,
                       Integer targetYm);

    /**
     * 1件取得
     * @param applyUserId
     * @param targetYm
     * @param options
     * @return the TReport entity
     */
    @Select
    TReport selectById(String applyUserId,
                       Integer targetYm,
                       SelectOptions options);

    /**
     * 1件取得
     * @param applyUserId
     * @param targetYm
     * @param version
     * @throws NoResultException
     * @return the TReport entity
     */
    @Select(ensureResult = true)
    TReport selectByIdAndVersion(String applyUserId,
                                 Integer targetYm,
                                 Integer version) throws NoResultException;

    /**
     * 存在チェック
     * @param applyUserId
     * @param targetYm
     * @return
     */
    @Select
    boolean existsById(String applyUserId,
                       Integer targetYm);

    /**
     * 挿入
     * @param entity
     * @return affected rows
     */
    @Insert(excludeNull = true)
    int insert(TReport entity);

    /**
     * 更新（楽観的排他制御）
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
     * 削除（楽観的排他制御）
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Delete
    int delete(TReport entity) throws OptimisticLockException;

    /**
     * 削除
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Delete(ignoreVersion = true)
    int deleteNoOptimisticLockException(TReport entity);

    /* 独自メソッド ------------------------------------------------------------- */

}