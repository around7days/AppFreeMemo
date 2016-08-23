package rms.domain.mst.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import rms.com.base.BusinessException;
import rms.com.consts.Const;
import rms.domain.com.repository.MUserDao;
import rms.domain.mst.user.repository.UserSelectDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ユーザ情報検証サービス
 * @author
 */
@Service
public class UserValidateService extends rms.domain.com.abstracts.AbstractService {
    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UserValidateService.class);

    /** MUserDao */
    @Autowired
    MUserDao mUserDao;

    /** ユーザ情報取得Dao */
    @Autowired
    UserSelectDao userSelectDao;

    /**
     * ユーザIDの重複チェック<br>
     * 重複している場合はBusinessExceptionを発生
     * @param userId
     * @throws BusinessException
     */
    public void validateUniquUserId(String userId) throws BusinessException {
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
     * @param roleApplyFlg
     * @param approveUserId1
     * @param approveUserId2
     * @param approveUserId3
     * @throws BusinessException
     */
    public void validateApprovalRoute(String roleApplyFlg,
                                      String approveUserId1,
                                      String approveUserId2,
                                      String approveUserId3) throws BusinessException {
        //@formatter:off
        if (isValueEquals(approveUserId1, approveUserId2) ||
            isValueEquals(approveUserId1, approveUserId3) ||
            isValueEquals(approveUserId2, approveUserId3)) {
            // 承認者１～３に同じ承認者は設定できません
            throw new BusinessException(message.getMessage("error.004", null, Locale.getDefault()));
        }
        //@formatter:on

        if (Const.FLG_ON.equals(roleApplyFlg) && StringUtils.isEmpty(approveUserId3)) {
            // 役割が申請者の場合、承認者３は必須です
            throw new BusinessException(message.getMessage("error.005", null, Locale.getDefault()));
        }
    }

    /**
     * 値の同一チェック<br>
     * （どちらかの値が空白の場合はfalseとする）
     * @param value1
     * @param value2
     * @return true:同じ false:異なる
     */
    private boolean isValueEquals(String value1,
                                  String value2) {
        if (StringUtils.isEmpty(value1) || StringUtils.isEmpty(value2)) {
            return false;
        }
        return value1.equals(value2);
    }
}
