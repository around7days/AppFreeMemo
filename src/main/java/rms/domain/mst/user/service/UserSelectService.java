package rms.domain.mst.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import rms.com.base.BusinessException;
import rms.com.consts.Const;
import rms.com.consts.MRoleConst;
import rms.domain.com.entity.MUser;
import rms.domain.com.entity.MUserRole;
import rms.domain.com.repository.MUserDao;
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

import com.mysql.jdbc.StringUtils;

/**
 * ユーザ情報取得サービス
 * @author
 */
@Service
public class UserSelectService extends rms.domain.com.abstracts.AbstractService {
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserSelectService.class);

    /** ユーザマスタDao */
    @Autowired
    MUserDao mUserDao;

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
        MUser mUser = mUserDao.selectById(userId);

        // ユーザ役割マスタ情報の取得
        List<MUserRole> mUserRoleList = userSelectDao.userRoleListByUserId(userId);

        // 返却用ユーザ情報の生成
        UserEntity userEntity = new UserEntity();
        // ユーザマスタ情報
        userEntity.setMUser(mUser);
        // 役割
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

        logger.debug("取得情報 -> {}", userEntity.toString());

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
        logger.debug("検索条件 -> {}", condition.toString());
        logger.debug("ページ情報 -> {}", pageInfo.toString());

        // ページング設定
        SelectOptions options = SelectOptionsUtils.get(pageInfo);

        // 検索処理
        List<UserSearchResultEntity> resultList = userSelectDao.userListByCondition(condition, options);
        logger.debug("検索結果(全件) -> {}件", options.getCount());
        logger.debug("検索結果 -> {}件", resultList.size());
        resultList.forEach(result -> logger.debug(result.toString()));

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

    /**
     * ユーザIDの重複チェック<br>
     * 重複している場合はBusinessExceptionを発生
     * @param userId
     * @throws BusinessException
     */
    public void checkUniquUserId(String userId) throws BusinessException {
        if (mUserDao.selectById(userId) != null) {
            // TODO 汚い・・・
            List<Object> params = new ArrayList<Object>();
            params.add("ユーザIDが");
            throw new BusinessException(message.getMessage("error.001", params.toArray(), Locale.getDefault()));
        }
    }

    /**
     * 承認ルート設定チェック<br>
     * 役割に申請者を保持している場合、承認者３は必須入力になります。 <br>
     * 未入力の場合はBusinessExceptionを発生させます。
     * @param roleApplicantFlg
     * @param approver3Id
     * @throws BusinessException
     */
    public void checkApprovalRoute(String roleApplicantFlg,
                                   String approver3Id) throws BusinessException {
        if (Const.FLG_ON.equals(roleApplicantFlg) && StringUtils.isNullOrEmpty(approver3Id)) {
            throw new BusinessException("役割が申請者の場合、承認者３は必須です。");
        }
    }
}
