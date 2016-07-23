package rms.domain.com.entity;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PostDeleteContext;
import org.seasar.doma.jdbc.entity.PostInsertContext;
import org.seasar.doma.jdbc.entity.PostUpdateContext;
import org.seasar.doma.jdbc.entity.PreDeleteContext;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;
import rms.web.base.UserInfo;
import rms.web.com.utils.AuthenticationUtils;
import java.time.LocalDateTime;

/**
 * VMUserRoleListenerクラス
 */
public class VMUserRoleListener implements EntityListener<VMUserRole> {

    @Override
    public void preInsert(VMUserRole entity,
                          PreInsertContext<VMUserRole> context) {
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
    public void preUpdate(VMUserRole entity,
                          PreUpdateContext<VMUserRole> context) {
        //@formatter:off
        UserInfo userInfo = AuthenticationUtils.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        if (entity.getUpdId() == null)   entity.setUpdId(userInfo.getUserId());
        if (entity.getUpdDate() == null) entity.setUpdDate(now);
        //@formatter:on
    }

    @Override
    public void preDelete(VMUserRole entity,
                          PreDeleteContext<VMUserRole> context) {
    }

    @Override
    public void postInsert(VMUserRole entity,
                           PostInsertContext<VMUserRole> context) {
    }

    @Override
    public void postUpdate(VMUserRole entity,
                           PostUpdateContext<VMUserRole> context) {
    }

    @Override
    public void postDelete(VMUserRole entity,
                           PostDeleteContext<VMUserRole> context) {
    }
}