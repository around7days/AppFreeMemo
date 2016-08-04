package rms.domain.mst.user.service;

import java.util.List;

import rms.com.consts.Const;
import rms.com.consts.MRoleConst;
import rms.domain.com.entity.MUserRole;
import rms.domain.com.entity.VMUser;
import rms.domain.mst.user.entity.UserEntity;
import rms.domain.mst.user.entity.UserSearchConditionEntity;
import rms.domain.mst.user.entity.UserSearchResultEntity;
import rms.domain.mst.user.repository.UserSelectDao;
import rms.web.base.SearchResultEntity;
import rms.web.com.utils.PageInfo;
import rms.web.com.utils.SelectOptionEntity;
import rms.web.com.utils.SelectOptionsUtils;

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
public class UserSelectService extends rms.domain.com.abstracts.AbstractService {
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserSelectService.class);

    /** ユーザ情報Dao */
    @Autowired
    UserSelectDao userSelectDao;

    /**
     * ユーザ情報の取得
     * @param userId
     * @return
     */
    public UserEntity getUserInfo(String userId) {
        // ユーザマスタ情報の取得
        VMUser vMUser = userSelectDao.userById(userId);

        // ユーザ役割マスタ情報の取得
        List<MUserRole> mUserRoleList = userSelectDao.userRoleListByUserId(userId);

        // 返却用ユーザ情報の生成
        UserEntity userEntity = new UserEntity();

        // ユーザマスタ情報
        userEntity.setUser(vMUser);
        // ユーザ役割マスタ情報
        userEntity.setUserRoleList(mUserRoleList);
        // 役割情報
        for (MUserRole mUserRole : mUserRoleList) {
            switch (mUserRole.getRole()) {
            case MRoleConst.APPLICANT: //申請者
                userEntity.setRoleApplicantFlg(Const.FLG_ON);
                break;
            case MRoleConst.APPROVER: //承認者
                userEntity.setRoleApproverFlg(Const.FLG_ON);
                break;
            case MRoleConst.ADMIN: //管理者者
                userEntity.setRoleAdminFlg(Const.FLG_ON);
                break;
            }
        }

        logger.debug("取得情報 -> {}", userEntity);

        return userEntity;
    }

    /**
     * ユーザ情報一覧検索処理
     * @param condition
     * @param pageInfo
     * @return
     */
    public SearchResultEntity<UserSearchResultEntity> getUserInfoList(UserSearchConditionEntity condition,
                                                                      PageInfo pageInfo) {
        logger.debug("検索条件 -> {}", condition);
        logger.debug("ページ情報 -> {}", pageInfo);

        // ページング設定
        SelectOptions options = SelectOptionsUtils.get(pageInfo);

        // 検索処理
        List<UserSearchResultEntity> resultList = userSelectDao.userListByCondition(condition, options);
        logger.debug("検索結果(全件) -> {}件", options.getCount());
        logger.debug("検索結果 -> {}件", resultList.size());
        resultList.forEach(result -> logger.debug("{}", result));

        // 検索結果格納
        SearchResultEntity<UserSearchResultEntity> searchResultEntity = new SearchResultEntity<>();
        searchResultEntity.setResultList(resultList);
        searchResultEntity.setCount(options.getCount());

        return searchResultEntity;
    }

    /**
     * セレクトボックス用 承認者一覧情報の取得
     * @return
     */
    public List<SelectOptionEntity> getSelectboxApprover() {
        // セレクトボックス用 承認者一覧の取得
        List<SelectOptionEntity> approverList = userSelectDao.selectboxApprover();

        return approverList;
    }
}
