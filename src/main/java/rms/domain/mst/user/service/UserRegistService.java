package rms.domain.mst.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import rms.com.base.BusinessException;
import rms.domain.com.entity.MUser;
import rms.domain.com.repository.MUserDao;
import rms.domain.mst.user.repository.UserSelectDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ユーザ情報登録サービス
 * @author
 */
@Service
public class UserRegistService extends rms.com.abstracts.AbstractService {
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserRegistService.class);

    /** ユーザマスタDao */
    @Autowired
    MUserDao mUserDao;

    /** ユーザ情報Dao */
    @Autowired
    UserSelectDao userDao;

    /**
     * ユーザマスタ新規登録処理<br>
     * 説明：ユーザIDの重複チェックを行い、問題なければ新規登録を行います。
     * @param mUser
     */
    public void insertUserMst(MUser mUser) {
        logger.debug("登録情報 -> {}", mUser.toString());

        // TODO 存在チェック用の共通SQLがほしい（削除フラグ判断込みで）
        // ユーザIDの重複チェック
        if (mUserDao.selectById(mUser.getUserId()) != null) {
            // TODO 汚い・・・
            List<Object> params = new ArrayList<Object>();
            params.add("ユーザIDが");
            throw new BusinessException(message.getMessage("error.001", params.toArray(), Locale.getDefault()));
        }

        // 登録処理
        mUserDao.insert(mUser);
    }

    /**
     * ユーザ役割マスタ登録処理<br>
     * 説明：ユーザに紐付く役割マスタを全て削除してから新規登録を行います。
     * @param mUser
     */
    public void deleteInsertUserRoleMst(MUser mUser) {
        logger.debug("登録情報 -> {}", mUser.toString());

        // TODO 存在チェック用の共通SQLがほしい（削除フラグ判断込みで）
        // ユーザーIDの重複チェック
        if (mUserDao.selectById(mUser.getUserId()) != null) {
            // TODO 汚い・・・
            List<Object> params = new ArrayList<Object>();
            params.add("ユーザIDが");
            throw new BusinessException(message.getMessage("error.001", params.toArray(), Locale.getDefault()));
        }

        // 登録処理
        mUserDao.insert(mUser);
    }

    /**
     * 更新処理
     * @param mUser
     */
    public void updateUserMst(MUser mUser) {
        logger.debug("更新情報 -> {}", mUser.toString());

        // 更新処理（楽観的排他制御）
        mUserDao.update(mUser);
    }

}
