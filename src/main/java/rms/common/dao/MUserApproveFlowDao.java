package rms.common.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import rms.common.model.MUserApproveFlow;
import org.seasar.doma.jdbc.NoResultException;
import org.seasar.doma.jdbc.OptimisticLockException;
import org.seasar.doma.jdbc.SelectOptions;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * MUserApproveFlowDaoクラス
 */
@Dao
@ConfigAutowireable
public interface MUserApproveFlowDao {

    /**
     * 1件取得
     * @param userId
     * @param approveSeq
     * @return the MUserApproveFlow entity
     */
    @Select
    MUserApproveFlow selectById(String userId,
                                Integer approveSeq);

    /**
     * 1件取得
     * @param userId
     * @param approveSeq
     * @param options
     * @return the MUserApproveFlow entity
     */
    @Select
    MUserApproveFlow selectById(String userId,
                                Integer approveSeq,
                                SelectOptions options);

    /**
     * 1件取得
     * @param userId
     * @param approveSeq
     * @param version
     * @throws NoResultException
     * @return the MUserApproveFlow entity
     */
    @Select(ensureResult = true)
    MUserApproveFlow selectByIdAndVersion(String userId,
                                          Integer approveSeq,
                                          Integer version) throws NoResultException;

    /**
     * 挿入
     * @param entity
     * @return affected rows
     */
    @Insert(excludeNull = true)
    int insert(MUserApproveFlow entity);

    /**
     * 更新（楽観的排他制御）
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Update(excludeNull = true)
    int update(MUserApproveFlow entity) throws OptimisticLockException;

    /**
     * 更新
     * @param entity
     * @return affected rows
     */
    @Update(excludeNull = true, ignoreVersion = true)
    int updateNoOptimisticLockException(MUserApproveFlow entity);

    /**
     * 削除
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(MUserApproveFlow entity);
}