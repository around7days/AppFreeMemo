package rms.domain.com.repository;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Update;
import rms.domain.com.entity.VMUserRole;
import org.seasar.doma.jdbc.OptimisticLockException;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * VMUserRoleDaoクラス
 */
@Dao
@ConfigAutowireable
public interface VMUserRoleDao {

    /**
     * 挿入
     * @param entity
     * @return affected rows
     */
    @Insert(excludeNull = true)
    int insert(VMUserRole entity);

    /**
     * 更新（楽観的排他制御）
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Update(excludeNull = true)
    int update(VMUserRole entity) throws OptimisticLockException;

    /**
     * 更新
     * @param entity
     * @return affected rows
     */
    @Update(excludeNull = true, ignoreVersion = true)
    int updateNoOptimisticLockException(VMUserRole entity);

    /**
     * 削除
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(VMUserRole entity);
}