package rms.domain.app.mst.userlist;

import java.util.List;

import rms.common.utils.PageInfo;
import rms.common.utils.SelectOptionsUtils;
import rms.domain.app.shared.entity.SearchResultEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.seasar.doma.jdbc.SelectOptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ユーザ一覧画面サービス
 * @author
 */
@Service
public class UserListService extends rms.common.abstracts.AbstractService {
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserListService.class);

    /** ユーザ情報取得Dao */
    @Autowired
    UserListDao dao;

    /**
     * ユーザ情報一覧検索処理
     * @param condition
     * @param pageInfo
     * @return
     */
    public SearchResultEntity<UserListEntityResult> search(UserListEntityCondition condition,
                                                           PageInfo pageInfo) {
        // ページ情報の生成
        SelectOptions options = SelectOptionsUtils.get(pageInfo);

        // 検索処理
        List<UserListEntityResult> resultList = dao.userListByCondition(condition, options);
        logger.debug("検索結果(全件) -> {}件", options.getCount());
        logger.debug("検索結果 -> {}件", resultList.size());
        resultList.forEach(result -> logger.debug("{}", result));

        // 検索結果格納
        SearchResultEntity<UserListEntityResult> resultEntity = new SearchResultEntity<>();
        resultEntity.setResultList(resultList);
        resultEntity.setCount(options.getCount());

        return resultEntity;
    }

}
