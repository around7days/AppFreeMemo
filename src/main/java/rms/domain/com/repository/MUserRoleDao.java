package rms.domain.com.repository;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import rms.domain.com.entity.MUserRole;
import org.seasar.doma.jdbc.NoResultException;
import org.seasar.doma.jdbc.OptimisticLockException;
import org.seasar.doma.jdbc.SelectOptions;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * MUserRoleDaoクラス
 */
@Dao
@ConfigAutowireable
public interface MUserRoleDao {

    /**
     * 1件取得
     * @param userId
     * @param roleId
     * @return the MUserRole entity
     */
    @Select
    MUserRole selectById(String userId, String roleId);

    /**
     * 1件取得
     * @param userId
     * @param roleId
     * @param options
     * @return the MUserRole entity
     */
    @Select
    MUserRole selectById(String userId, String roleId, SelectOptions options);

    /**
     * 1件取得
     * @param userId
     * @param roleId
     * @param version
     * @throws NoResultException
     * @return the MUserRole entity
     */
    @Select(ensureResult = true)
    MUserRole selectByIdAndVersion(String userId, String roleId, Integer version) throws NoResultException;

    /**
     * 挿入
     * @param entity
     * @return affected rows
     */
    @Insert(excludeNull = true)
    int insert(MUserRole entity);


    /**
     * 更新（楽観的排他制御）<br>
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Update(excludeNull = true)
    int update(MUserRole entity) throws OptimisticLockException;

    /**
     * 更新
     * @param entity
     * @return affected rows
     */
    @Update(excludeNull = true, ignoreVersion = true)
    int updateNoOptimisticLockException(MUserRole entity);

    /**
     * 削除
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(MUserRole entity);
}