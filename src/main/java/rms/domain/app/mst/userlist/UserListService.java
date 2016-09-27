package rms.domain.app.mst.userlist;

import rms.common.utils.PageInfo;
import rms.domain.app.shared.entity.SearchResultEntity;

/**
 * ユーザ一覧画面サービスインタフェース
 * @author
 */
public interface UserListService {

    /**
     * ユーザ情報一覧検索処理
     * @param condition
     * @param pageInfo
     * @return
     */
    public SearchResultEntity<UserListEntityResult> search(UserListEntityCondition condition,
                                                           PageInfo pageInfo);
}
