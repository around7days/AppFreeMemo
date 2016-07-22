package rms.domain.com.repository;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Update;
import rms.domain.com.entity.VMUser;
import org.seasar.doma.jdbc.OptimisticLockException;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * VMUserDaoクラス
 */
@Dao
@ConfigAutowireable
public interface VMUserDao {

    /**
     * 挿入
     * @param entity
     * @return affected rows
     */
    @Insert(excludeNull = true)
    int insert(VMUser entity);

    /**
     * 更新（楽観的排他制御）
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Update(excludeNull = true)
    int update(VMUser entity) throws OptimisticLockException;

    /**
     * 更新
     * @param entity
     * @return affected rows
     */
    @Update(excludeNull = true, ignoreVersion = true)
    int updateNoOptimisticLockException(VMUser entity);

    /**
     * 削除
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(VMUser entity);
}