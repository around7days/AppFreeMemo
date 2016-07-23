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
 * VMUserListenerクラス
 */
public class VMUserListener implements EntityListener<VMUser> {

    @Override
    public void preInsert(VMUser entity,
                          PreInsertContext<VMUser> context) {
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
    public void preUpdate(VMUser entity,
                          PreUpdateContext<VMUser> context) {
        //@formatter:off
        UserInfo userInfo = AuthenticationUtils.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        if (entity.getUpdId() == null)   entity.setUpdId(userInfo.getUserId());
        if (entity.getUpdDate() == null) entity.setUpdDate(now);
        //@formatter:on
    }

    @Override
    public void preDelete(VMUser entity,
                          PreDeleteContext<VMUser> context) {
    }

    @Override
    public void postInsert(VMUser entity,
                           PostInsertContext<VMUser> context) {
    }

    @Override
    public void postUpdate(VMUser entity,
                           PostUpdateContext<VMUser> context) {
    }

    @Override
    public void postDelete(VMUser entity,
                           PostDeleteContext<VMUser> context) {
    }
}