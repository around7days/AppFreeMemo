package rms.domain.app.mst.userlist;

import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rms.common.utils.PageInfo;
import rms.common.utils.SelectOptionsUtils;
import rms.domain.app.shared.entity.SearchResultDto;

/**
 * ユーザ一覧画面サービス
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserListServiceImpl implements UserListService {
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserListServiceImpl.class);

    /** ユーザ情報取得Dao */
    @Autowired
    UserListDao dao;

    /**
     * ユーザ情報一覧検索処理
     * @param condition
     * @param pageInfo
     * @return
     */
    @Override
    public SearchResultDto<UserListEntityResult> search(UserListDtoCondition condition,
                                                        PageInfo pageInfo) {
        // ページ情報の生成
        SelectOptions options = SelectOptionsUtils.get(pageInfo);

        // 検索処理
        List<UserListEntityResult> resultList = dao.userListByCondition(condition, options);
        logger.debug("検索結果(全件) -> {}件", options.getCount());
        logger.debug("検索結果 -> {}件", resultList.size());
        resultList.forEach(result -> logger.debug("{}", result));

        // 検索結果格納
        SearchResultDto<UserListEntityResult> resultDto = new SearchResultDto<>();
        resultDto.setResultList(resultList);
        resultDto.setCount(options.getCount());

        return resultDto;
    }

}
