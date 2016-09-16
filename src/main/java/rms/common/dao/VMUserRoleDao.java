package rms.common.dao;

import rms.common.model.VMUserRole;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

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
     * @return the MUserRole entity
     */
    @Select
    VMUserRole selectById(String userId,
                          String role);

    /**
     * 1件取得
     * @param userId
     * @param role
     * @param options
     * @return the MUserRole entity
     */
    @Select
    VMUserRole selectById(String userId,
                          String role,
                          SelectOptions options);

}