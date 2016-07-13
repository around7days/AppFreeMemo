package rms.web.mst.user.regist;

import rms.com.doma.dao.MUserDao;
import rms.com.doma.entity.MUser;
import rms.web.com.base.BusinessException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ユーザ登録画面サービス
 * @author
 */
@Service
public class UserRegistService extends rms.com.abstracts.AbstractService {
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserRegistService.class);

    /** ユーザマスタDao */
    @Autowired
    MUserDao mUserDao;

    /**
     * 初期表示処理（更新時）
     * @param form
     * @param userId
     */
    public void initUpdate(UserRegistForm form,
                           String userId) {
        // ユーザ情報の取得
        MUser mUser = mUserDao.selectById(userId);

        // 画面表示設定
        // 詰め替え
        BeanUtils.copyProperties(mUser, form);
        // パスワードは消しておく
        form.setPassword(null);
    }

    /**
     * 新規登録処理
     * @param form
     */
    public void insert(UserRegistForm form) {

        // 登録情報の生成
        MUser mUser = new MUser();
        BeanUtils.copyProperties(form, mUser);
        logger.debug("登録情報 -> {}", mUser.toString());

        // TODO 存在チェック用の共通SQLがほしい（削除フラグも）
        // ユーザーIDの重複チェック
        if (mUserDao.selectById(mUser.getUserId()) != null) {
            throw new BusinessException("ユーザIDが重複しています。");
        }

        // 登録処理
        mUserDao.insert(mUser);
    }

    /**
     * 更新処理
     * @param form
     */
    public void update(UserRegistForm form) {

        // 更新情報の生成
        MUser mUser = new MUser();
        BeanUtils.copyProperties(form, mUser);

        logger.debug("更新情報 -> {}", mUser.toString());

        // 更新処理（楽観的排他制御）
        mUserDao.update(mUser);
    }

}
