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
     * 画面初期表示処理（更新時）<br>
     * ユーザIDをもとにユーザ登録画面の初期表示情報を生成
     * @param userId
     * @return
     */
    public UserRegistDto initDisplayUpdate(String userId);

    /**
     * ユーザ情報登録処理
     * @param userRegistEntity
     * @throws BusinessException
     */
    public void regist(UserRegistDto userRegistEntity) throws BusinessException;

    /**
     * ユーザ情報更新処理
     * @param userRegistEntity
     * @throws BusinessException
     */
    public void update(UserRegistDto userRegistEntity) throws BusinessException;

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
