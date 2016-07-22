package rms.domain.mst.user.repository;

import java.util.List;

import rms.domain.com.entity.VMUserRole;
import rms.domain.mst.user.entity.UserSearchConditionEntity;
import rms.domain.mst.user.entity.UserSearchResultEntity;
import rms.web.com.utils.SelectOptionEntity;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

/**
 * ユーザ情報取得Dao
 */
@Dao
@ConfigAutowireable
public interface UserSelectDao {

    /**
     * ユーザIDに紐付く役割一覧を取得
     * @param userId
     * @return the MUserRole entity
     */
    @Select
    List<VMUserRole> userRoleListByUserId(String userId);

    /**
     * ユーザ検索処理
     * <p>
     * 検索条件に一致したユーザ情報を取得する。
     * </p>
     * @param condition
     * @param options
     * @return
     */
    @Select
    List<UserSearchResultEntity> userListByCondition(UserSearchConditionEntity condition,
                                                     SelectOptions options);

    /**
     * 承認者一覧の取得（selectbox専用）
     * @return
     */
    @Select
    List<SelectOptionEntity> selectboxApprover();
}