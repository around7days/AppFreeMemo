package rms.domain.app.mst.userregist;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import rms.common.model.MUserRole;
import rms.common.utils.SelectOptionEntity;

/**
 * ユーザ情報登録Dao
 */
@Dao
@ConfigAutowireable
public interface UserRegistDao {

    // TODO ユーザ役割マスタDaoに持っていく方がいいかも？ログイン認証時にも使用している。
    /**
     * ユーザIDに紐付く役割一覧を取得
     * @param userId
     * @return the MUserRole entity
     */
    @Select
    List<MUserRole> userRoleListByUserId(String userId);

    /**
     * ユーザIDに紐付く役割を全て削除
     * @param userId
     * @return
     */
    @Delete(sqlFile = true)
    int deleteUserRoleByUserId(String userId);

    /**
     * 承認者一覧の取得（selectbox専用）
     * @return
     */
    @Select
    List<SelectOptionEntity> selectboxApproveUser();

}