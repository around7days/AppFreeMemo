package rms.domain.app.mst.userregist;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import rms.common.utils.SelectOptionEntity;

/**
 * ユーザ情報登録Dao
 */
@Dao
@ConfigAutowireable
public interface UserRegistDao {

    /**
     * 承認者一覧の取得（selectbox専用）
     * @return
     */
    @Select
    List<SelectOptionEntity> selectboxApproveUser();
}