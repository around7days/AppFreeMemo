package rms.web.mst.user.regist;

import java.util.List;
import java.util.Map;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * ユーザ登録画面Dao
 */
@Dao
@ConfigAutowireable
public interface UserSearchDao {

    /**
     * 承認者一覧の取得<br>
     * selectbox用
     * @param form
     * @return
     */
    @Select
    List<Map<String, Object>> selectboxApprover();

}