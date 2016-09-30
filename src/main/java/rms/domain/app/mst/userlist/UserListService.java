package rms.domain.app.mst.userlist;

import rms.common.dto.SearchResultDto;
import rms.common.utils.PageInfo;

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
    public SearchResultDto<UserListEntityResult> search(UserListDtoCondition condition,
                                                        PageInfo pageInfo);
}
