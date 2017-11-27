package rms.common.entity;

import java.time.LocalDateTime;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PostDeleteContext;
import org.seasar.doma.jdbc.entity.PostInsertContext;
import org.seasar.doma.jdbc.entity.PostUpdateContext;
import org.seasar.doma.jdbc.entity.PreDeleteContext;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;

import rms.common.auth.UserInfo;
import rms.common.auth.UserInfoAccessor;

/**
 * TInfomationListenerクラス
 */
public class TInfomationListener implements EntityListener<TInfomation> {

    @Override
    public void preInsert(TInfomation entity,
                          PreInsertContext<TInfomation> context) {
        //@formatter:off
        UserInfo userInfo = UserInfoAccessor.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        if (entity.getVersion() == null) entity.setVersion(0);
        if (entity.getDelFlg() == null)  entity.setDelFlg(0);
        if (entity.getInsId() == null)   entity.setInsId(userInfo.getUserId());
        if (entity.getInsDate() == null) entity.setInsDate(now);
        if (entity.getUpdId() == null)   entity.setUpdId(userInfo.getUserId());
        if (entity.getUpdDate() == null) entity.setUpdDate(now);
        //@formatter:on
    }

    @Override
    public void preUpdate(TInfomation entity,
                          PreUpdateContext<TInfomation> context) {
        //@formatter:off
        UserInfo userInfo = UserInfoAccessor.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        if (entity.getUpdId() == null)   entity.setUpdId(userInfo.getUserId());
        if (entity.getUpdDate() == null) entity.setUpdDate(now);
        //@formatter:on
    }

    @Override
    public void preDelete(TInfomation entity,
                          PreDeleteContext<TInfomation> context) {
    }

    @Override
    public void postInsert(TInfomation entity,
                           PostInsertContext<TInfomation> context) {
    }

    @Override
    public void postUpdate(TInfomation entity,
                           PostUpdateContext<TInfomation> context) {
    }

    @Override
    public void postDelete(TInfomation entity,
                           PostDeleteContext<TInfomation> context) {
    }
}