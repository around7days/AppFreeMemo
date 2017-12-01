package rms.domain.app.system.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rms.common.dao.TInfomationDao;
import rms.common.entity.TInfomation;
import rms.domain.app.mst.userlist.UserListServiceImpl;

/**
 * ログイン画面サービス
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {
    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UserListServiceImpl.class);

    /** TInfomationDao */
    @Autowired
    TInfomationDao tInfomationDao;

    @Override
    public TInfomation getInfomation() {
        // お知らせ情報の取得
        TInfomation entity = tInfomationDao.selectById(1);
        return entity;
    }

}
