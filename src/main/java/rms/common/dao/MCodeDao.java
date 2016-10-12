package rms.common.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.NoResultException;
import org.seasar.doma.jdbc.OptimisticLockException;
import org.seasar.doma.jdbc.SelectOptions;

import rms.common.entity.MCode;
import rms.common.utils.SelectOptionEntity;

/**
 * MCodeDaoクラス
 */
@Dao
@ConfigAutowireable
public interface MCodeDao {

    /* 自動生成メソッド ------------------------------------------------------------- */

    /**
     * 1件取得
     * @param codeKbn
     * @param code
     * @return the MCode entity
     */
    @Select
    MCode selectById(String codeKbn,
                     String code);

    /**
     * 1件取得
     * @param codeKbn
     * @param code
     * @param options
     * @return the MCode entity
     */
    @Select
    MCode selectById(String codeKbn,
                     String code,
                     SelectOptions options);

    /**
     * 1件取得
     * @param codeKbn
     * @param code
     * @param version
     * @throws NoResultException
     * @return the MCode entity
     */
    @Select(ensureResult = true)
    MCode selectByIdAndVersion(String codeKbn,
                               String code,
                               Integer version) throws NoResultException;

    /**
     * 存在チェック
     * @param codeKbn
     * @param code
     * @return
     */
    @Select
    boolean existsById(String codeKbn,
                       String code);

    /**
     * 挿入
     * @param entity
     * @return affected rows
     */
    @Insert(excludeNull = true)
    int insert(MCode entity);

    /**
     * 更新（楽観的排他制御）
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Update(excludeNull = true)
    int update(MCode entity) throws OptimisticLockException;

    /**
     * 更新
     * @param entity
     * @return affected rows
     */
    @Update(excludeNull = true, ignoreVersion = true)
    int updateNoOptimisticLockException(MCode entity);

    /**
     * 削除（楽観的排他制御）
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Delete
    int delete(MCode entity) throws OptimisticLockException;

    /**
     * 削除
     * @param entity
     * @return affected rows
     * @throws OptimisticLockException
     */
    @Delete(ignoreVersion = true)
    int deleteNoOptimisticLockException(MCode entity);

    /* 独自メソッド ------------------------------------------------------------- */

    /**
     * 指定されたコード区分に紐付く一覧を取得<br>
     * （ソート順はコードの昇順）
     * @param codeKbn
     * @return the MCode entity
     */
    @Select
    List<MCode> selectListByCodeKbn(String codeKbn);

    /**
     * 部署略称一覧を取得を取得（selectbox専用）<br>
     * （ソート順はコードの昇順）
     * @return
     */
    @Select
    List<SelectOptionEntity> selectboxDepartmentRnm();

}