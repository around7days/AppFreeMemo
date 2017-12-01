package rms.domain.app.mst.userlist;

import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rms.common.utils.PageInfo;
import rms.common.utils.SearchResultDto;
import rms.common.utils.SelectOptionsUtils;

/**
 * ユーザ一覧画面サービス
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserListServiceImpl implements UserListService {
    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UserListServiceImpl.class);

    /** ユーザ情報取得Dao */
    @Autowired
    UserListDao dao;

    @Override
    public SearchResultDto<UserListEntityResult> search(UserListDtoCondition condition,
                                                        PageInfo pageInfo) {
        // ページ情報の生成
        SelectOptions options = SelectOptionsUtils.get(pageInfo);

        // 検索処理
        List<UserListEntityResult> resultList = dao.userListByCondition(condition, options);

        // 検索結果格納
        SearchResultDto<UserListEntityResult> resultDto = new SearchResultDto<>();
        resultDto.setResultList(resultList);
        resultDto.setCount(options.getCount());

        return resultDto;
    }

}
