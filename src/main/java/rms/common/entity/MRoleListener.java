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
import rms.common.utils.AuthenticationUtils;

/**
 * MRoleListenerクラス
 */
public class MRoleListener implements EntityListener<MRole> {

    @Override
    public void preInsert(MRole entity,
                          PreInsertContext<MRole> context) {
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
    public void preUpdate(MRole entity,
                          PreUpdateContext<MRole> context) {
        //@formatter:off
        UserInfo userInfo = AuthenticationUtils.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        if (entity.getUpdId() == null)   entity.setUpdId(userInfo.getUserId());
        if (entity.getUpdDate() == null) entity.setUpdDate(now);
        //@formatter:on
    }

    @Override
    public void preDelete(MRole entity,
                          PreDeleteContext<MRole> context) {
    }

    @Override
    public void postInsert(MRole entity,
                           PostInsertContext<MRole> context) {
    }

    @Override
    public void postUpdate(MRole entity,
                           PostUpdateContext<MRole> context) {
    }

    @Override
    public void postDelete(MRole entity,
                           PostDeleteContext<MRole> context) {
    }
}