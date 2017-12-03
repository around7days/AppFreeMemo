package rms.domain.app.mst.userlist;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

/**
 * ユーザ情報取得Dao
 */
@Dao
@ConfigAutowireable
public interface UserListDao {

    /**
     * ユーザ一覧の取得<br>
     * @param condition
     * @param options
     * @return
     */
    @Select
    List<UserListResultEntity> userListByCondition(UserListDto condition,
                                                   SelectOptions options);
}