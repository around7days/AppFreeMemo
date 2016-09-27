package rms.common.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import rms.common.model.TReportApproveFlow;
import org.seasar.doma.jdbc.NoResultException;
import org.seasar.doma.jdbc.OptimisticLockException;
import org.seasar.doma.jdbc.SelectOptions;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * TReportApproveFlowDaoクラス
 */
@Dao
@ConfigAutowireable
public interface TReportApproveFlowDao {

    /**
     * 1件取得
     * @param applyUserId
     * @param targetYm
     * @param approveSeq
     * @return the TReportApproveFlow entity
     */
    @Select
    TReportApproveFlow selectById(String applyUserId,
                                  Integer targetYm,
                                  Integer approveSeq);

    /**
     * 1件取得
     * @param applyUserId
     * @param targetYm
     * @param approveSeq
     * @param options
     * @return the TReportApproveFlow entity
     */
    @Select
    TReportApproveFlow selectById(String applyUserId,
                                  Integer targetYm,
                                  Integer approveSeq,
                                  SelectOptions options);

    /**
     * 1件取得
     * @param applyUserId
     * @param targetYm
     * @param approveSeq
     * @param version
     * @throws NoResultException
     * @return the TReportApproveFlow entity
     */
    @Select(ensureResult = true)
    TReportApproveFlow selectByIdAndVersion(String applyUserId,
                                            Integer targetYm,
                                            Integer approveSeq,
                                            Integer version) throws NoResultException;

    /**
     * 挿入
     * @param entity
     * @return affected rows
     */
    @Insert(excludeNull = true)
    int insert(TReportApproveFlow entity);

    /**
     * 更新（楽観的排他制御）
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Update(excludeNull = true)
    int update(TReportApproveFlow entity) throws OptimisticLockException;

    /**
     * 更新
     * @param entity
     * @return affected rows
     */
    @Update(excludeNull = true, ignoreVersion = true)
    int updateNoOptimisticLockException(TReportApproveFlow entity);

    /**
     * 削除
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(TReportApproveFlow entity);
}