package rms.domain.mst.user.service;

import java.util.List;

import rms.com.base.PageInfo;
import rms.com.base.SearchResultEntity;
import rms.domain.com.entity.MUser;
import rms.domain.com.repository.MUserDao;
import rms.domain.mst.user.entity.UserSearchConditionEntity;
import rms.domain.mst.user.entity.UserSearchResultEntity;
import rms.domain.mst.user.repository.UserDao;
import rms.web.com.utils.SelectOptionsUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.seasar.doma.jdbc.SelectOptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ユーザ情報取得サービス
 * @author
 */
@Service
public class UserServiceSelect extends rms.com.abstracts.AbstractService {
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserServiceSelect.class);

    /** ユーザマスタDao */
    @Autowired
    MUserDao mUserDao;

    /** ユーザ情報Dao */
    @Autowired
    UserDao userDao;

    /**
     * ユーザ情報の取得
     * @param userId
     * @return ユーザマスタ情報
     */
    public MUser getUserInfo(String userId) {
        // ユーザ情報の取得
        MUser mUser = mUserDao.selectById(userId);
        logger.debug("ユーザ情報 -> {}", mUser.toString());
        // パスワードは消しておく
        mUser.setPassword(null);

        // ユーザ役割マスタ情報の取得

        return mUser;
    }

    /**
     * ユーザ情報一覧検索処理
     * @param condition
     * @param pageInfo
     * @return
     */
    public SearchResultEntity<UserSearchResultEntity> getUserInfoList(UserSearchConditionEntity condition,
                                                                      PageInfo pageInfo) {
        // ページング設定
        SelectOptions options = SelectOptionsUtil.get(pageInfo);

        // 検索処理
        List<UserSearchResultEntity> resultList = userDao.userListByCondition(condition, options);
        logger.debug("検索結果(全件) -> {}件", options.getCount());
        logger.debug("検索結果 -> {}件", resultList.size());
        resultList.forEach(result -> logger.debug(result.toString()));

        // 検索結果格納
        SearchResultEntity<UserSearchResultEntity> searchResultEntity = new SearchResultEntity<UserSearchResultEntity>();
        searchResultEntity.setResultList(resultList);
        searchResultEntity.setCount(options.getCount());

        return searchResultEntity;
    }
}
