package rms.domain.com.repository;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Update;
import rms.domain.com.entity.VTReport;
import org.seasar.doma.jdbc.OptimisticLockException;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * VTReportDaoクラス
 */
@Dao
@ConfigAutowireable
public interface VTReportDao {

    /**
     * 挿入
     * @param entity
     * @return affected rows
     */
    @Insert(excludeNull = true)
    int insert(VTReport entity);

    /**
     * 更新（楽観的排他制御）
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Update(excludeNull = true)
    int update(VTReport entity) throws OptimisticLockException;

    /**
     * 更新
     * @param entity
     * @return affected rows
     */
    @Update(excludeNull = true, ignoreVersion = true)
    int updateNoOptimisticLockException(VTReport entity);

    /**
     * 削除
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(VTReport entity);
}