package rms.domain.app.mst.userregist;

import java.util.List;

import rms.common.base.BusinessException;
import rms.common.utils.SelectOptionEntity;

/**
 * ユーザ登録画面サービスインタフェース
 * @author
 */
public interface UserRegistService {

    /**
     * ユーザ情報の取得
     * @param userId
     * @return
     */
    public UserRegistEntity getUserInfo(String userId);

    /**
     * ユーザ情報登録処理
     * @param userRegistEntity
     * @throws BusinessException
     */
    public void regist(UserRegistEntity userRegistEntity) throws BusinessException;

    /**
     * ユーザ情報更新処理
     * @param userRegistEntity
     * @throws BusinessException
     */
    public void update(UserRegistEntity userRegistEntity) throws BusinessException;

    /**
     * セレクトボックス用 承認者一覧情報の取得
     * @return
     */
    public List<SelectOptionEntity> getSelectboxApprove();

    /**
     * セレクトボックス用 部署一覧情報の取得
     * @return
     */
    public List<SelectOptionEntity> getSelectboxDepartment();

}
