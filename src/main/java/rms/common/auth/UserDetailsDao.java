package rms.common.auth;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import rms.common.entity.MUserRole;

/**
 * 独自認証Dao
 */
@Dao
@ConfigAutowireable
public interface UserDetailsDao {

    /**
     * ユーザIDに紐付く役割一覧を取得
     * @param userId
     * @return the MUserRole entity
     */
    @Select
    List<MUserRole> userRoleListByUserId(String userId);
}