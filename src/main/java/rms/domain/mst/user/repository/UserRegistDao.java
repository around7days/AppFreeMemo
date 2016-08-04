package rms.domain.mst.user.repository;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * ユーザ情報登録Dao
 */
@Dao
@ConfigAutowireable
public interface UserRegistDao {

    /**
     * ユーザIDに紐付く役割を全て削除
     * @param userId
     * @return
     */
    @Delete(sqlFile = true)
    int deleteUserRoleByUserId(String userId);
}