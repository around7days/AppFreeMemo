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

import rms.common.entity.MUser;

/**
 * MUserDaoクラス
 */
@Dao
@ConfigAutowireable
public interface MUserDao {

    /* 自動生成メソッド ------------------------------------------------------------- */

    /**
     * 1件取得
     * @param userId
     * @return the MUser entity
     */
    @Select
    MUser selectById(String userId);

    /**
     * 1件取得
     * @param userId
     * @param options
     * @return the MUser entity
     */
    @Select
    MUser selectById(String userId,
                     SelectOptions options);

    /**
     * 1件取得
     * @param userId
     * @param version
     * @throws NoResultException
     * @return the MUser entity
     */
    @Select(ensureResult = true)
    MUser selectByIdAndVersion(String userId,
                               Integer version) throws NoResultException;

    /**
     * 挿入
     * @param entity
     * @return affected rows
     */
    @Insert(excludeNull = true)
    int insert(MUser entity);

    /**
     * 更新（楽観的排他制御）
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Update(excludeNull = true)
    int update(MUser entity) throws OptimisticLockException;

    /**
     * 更新
     * @param entity
     * @return affected rows
     */
    @Update(excludeNull = true, ignoreVersion = true)
    int updateNoOptimisticLockException(MUser entity);

    /**
     * 削除（楽観的排他制御）
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Delete
    int delete(MUser entity) throws OptimisticLockException;

    /**
     * 削除
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Delete(ignoreVersion = true)
    int deleteNoOptimisticLockException(MUser entity);

    /* 独自メソッド ------------------------------------------------------------- */

}