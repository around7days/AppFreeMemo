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

import rms.common.entity.TInfomation;

/**
 * TInfomationDaoクラス
 */
@Dao
@ConfigAutowireable
public interface TInfomationDao {

    /* 自動生成メソッド ------------------------------------------------------------- */

    /**
     * 1件取得
     * @param seq
     * @return the TInfomation entity
     */
    @Select
    TInfomation selectById(Integer seq);

    /**
     * 1件取得
     * @param seq
     * @param options
     * @return the TInfomation entity
     */
    @Select
    TInfomation selectById(Integer seq,
                           SelectOptions options);

    /**
     * 1件取得
     * @param seq
     * @param version
     * @throws NoResultException
     * @return the TInfomation entity
     */
    @Select(ensureResult = true)
    TInfomation selectByIdAndVersion(Integer seq,
                                     Integer version) throws NoResultException;

    /**
     * 存在チェック
     * @param seq
     * @return
     */
    @Select
    boolean existsById(Integer seq);

    /**
     * 挿入
     * @param entity
     * @return affected rows
     */
    @Insert(excludeNull = true)
    int insert(TInfomation entity);

    /**
     * 更新（楽観的排他制御）
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Update(excludeNull = true)
    int update(TInfomation entity) throws OptimisticLockException;

    /**
     * 更新
     * @param entity
     * @return affected rows
     */
    @Update(excludeNull = true, ignoreVersion = true)
    int updateNoOptimisticLockException(TInfomation entity);

    /**
     * 削除（楽観的排他制御）
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Delete
    int delete(TInfomation entity) throws OptimisticLockException;

    /**
     * 削除
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Delete(ignoreVersion = true)
    int deleteNoOptimisticLockException(TInfomation entity);

    /* 独自メソッド ------------------------------------------------------------- */

}