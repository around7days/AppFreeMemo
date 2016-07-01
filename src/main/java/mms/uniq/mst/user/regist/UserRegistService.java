package mms.uniq.mst.user.regist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mms.com.doma.dao.MUserDao;
import mms.com.doma.entity.MUser;

/**
 * ユーザ登録画面サービス
 * @author
 */
@Service
public class UserRegistService extends mms.com.abstracts.AbstractService {
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserRegistService.class);

    /** ユーザ登録画面Dao */
    @Autowired
    MUserDao mUserDao;

    /**
     * 更新初期処理
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
        // TODO 登録情報の生成もこのクラスの仕事・・・？

        // 登録情報の生成
        MUser mUser = new MUser();
        BeanUtils.copyProperties(form, mUser);
        mUser.setPassword("pass"); //TODO
        logger.debug("登録情報：{}", mUser.toString());

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
        logger.debug("更新情報：{}", mUser.toString());

        // 更新処理
        mUserDao.update(mUser);
    }
}
