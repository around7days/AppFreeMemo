package rms.common.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import rms.common.model.VMUser;

/**
 * VMUserDaoクラス
 */
@Dao
@ConfigAutowireable
public interface VMUserDao {

    /**
     * 1件取得
     * @param userId
     * @return the VMUser entity
     */
    @Select
    VMUser selectById(String userId);

    /**
     * 1件取得
     * @param userId
     * @param options
     * @return the VMUser entity
     */
    @Select
    VMUser selectById(String userId,
                      SelectOptions options);

}