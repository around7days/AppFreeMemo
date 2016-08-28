package rms.common.model;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PostDeleteContext;
import org.seasar.doma.jdbc.entity.PostInsertContext;
import org.seasar.doma.jdbc.entity.PostUpdateContext;
import org.seasar.doma.jdbc.entity.PreDeleteContext;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;

import rms.common.auth.UserInfo;
import rms.common.utils.AuthenticationUtils;

import java.time.LocalDateTime;

/**
 * MUserListenerクラス
 */
public class MUserListener implements EntityListener<MUser> {

    @Override
    public void preInsert(MUser entity,
                          PreInsertContext<MUser> context) {
        //@formatter:off
        UserInfo userInfo = AuthenticationUtils.getPrincipal();
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
    public void preUpdate(MUser entity,
                          PreUpdateContext<MUser> context) {
        //@formatter:off
        UserInfo userInfo = AuthenticationUtils.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        if (entity.getUpdId() == null)   entity.setUpdId(userInfo.getUserId());
        if (entity.getUpdDate() == null) entity.setUpdDate(now);
        //@formatter:on
    }

    @Override
    public void preDelete(MUser entity,
                          PreDeleteContext<MUser> context) {
    }

    @Override
    public void postInsert(MUser entity,
                           PostInsertContext<MUser> context) {
    }

    @Override
    public void postUpdate(MUser entity,
                           PostUpdateContext<MUser> context) {
    }

    @Override
    public void postDelete(MUser entity,
                           PostDeleteContext<MUser> context) {
    }
}