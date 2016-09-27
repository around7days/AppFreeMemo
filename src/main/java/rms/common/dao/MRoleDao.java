package rms.common.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import rms.common.model.MRole;
import org.seasar.doma.jdbc.NoResultException;
import org.seasar.doma.jdbc.OptimisticLockException;
import org.seasar.doma.jdbc.SelectOptions;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * MRoleDaoクラス
 */
@Dao
@ConfigAutowireable
public interface MRoleDao {

    /**
     * 1件取得
     * @param role
     * @return the MRole entity
     */
    @Select
    MRole selectById(String role);

    /**
     * 1件取得
     * @param role
     * @param options
     * @return the MRole entity
     */
    @Select
    MRole selectById(String role,
                     SelectOptions options);

    /**
     * 1件取得
     * @param role
     * @param version
     * @throws NoResultException
     * @return the MRole entity
     */
    @Select(ensureResult = true)
    MRole selectByIdAndVersion(String role,
                               Integer version) throws NoResultException;

    /**
     * 挿入
     * @param entity
     * @return affected rows
     */
    @Insert(excludeNull = true)
    int insert(MRole entity);

    /**
     * 更新（楽観的排他制御）
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Update(excludeNull = true)
    int update(MRole entity) throws OptimisticLockException;

    /**
     * 更新
     * @param entity
     * @return affected rows
     */
    @Update(excludeNull = true, ignoreVersion = true)
    int updateNoOptimisticLockException(MRole entity);

    /**
     * 削除
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(MRole entity);
}