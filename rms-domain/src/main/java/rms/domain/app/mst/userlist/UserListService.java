package rms.domain.app.mst.userlist;

import rms.common.utils.PageInfo;
import rms.common.utils.SearchResultDto;

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
    public SearchResultDto<UserListResultEntity> search(UserListDto condition,
                                                        PageInfo pageInfo);
}
