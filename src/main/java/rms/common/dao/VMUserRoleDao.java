package rms.common.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import rms.common.entity.VMUserRole;

/**
 * VMUserRoleDaoクラス
 */
@Dao
@ConfigAutowireable
public interface VMUserRoleDao {

    /**
     * 1件取得
     * @param userId
     * @param role
     * @return the VMUserRole entity
     */
    @Select
    VMUserRole selectById(String userId,
                          String role);

    /**
     * 1件取得
     * @param userId
     * @param role
     * @param options
     * @return the VMUserRole entity
     */
    @Select
    VMUserRole selectById(String userId,
                          String role,
                          SelectOptions options);

}