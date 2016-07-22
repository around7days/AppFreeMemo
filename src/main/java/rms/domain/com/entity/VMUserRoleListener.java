package rms.domain.com.entity;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PostDeleteContext;
import org.seasar.doma.jdbc.entity.PostInsertContext;
import org.seasar.doma.jdbc.entity.PostUpdateContext;
import org.seasar.doma.jdbc.entity.PreDeleteContext;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;

/**
 * VMUserRoleListenerクラス
 */
public class VMUserRoleListener implements EntityListener<VMUserRole> {

    // 認証情報
    private static UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                                                                              .getAuthentication()
                                                                              .getPrincipal();

    @Override
    public void preInsert(VMUserRole entity,
                          PreInsertContext<VMUserRole> context) {
        //@formatter:off
        LocalDateTime now = LocalDateTime.now();
        if (entity.getVersion() == null) entity.setVersion(0);
        if (entity.getDelFlg() == null)  entity.setDelFlg(0);
        if (entity.getInsId() == null)   entity.setInsId(principal.getUsername());
        if (entity.getInsDate() == null) entity.setInsDate(now);
        if (entity.getUpdId() == null)   entity.setUpdId(principal.getUsername());
        if (entity.getUpdDate() == null) entity.setUpdDate(now);
        //@formatter:on
    }

    @Override
    public void preUpdate(VMUserRole entity,
                          PreUpdateContext<VMUserRole> context) {
        //@formatter:off
        LocalDateTime now = LocalDateTime.now();
        if (entity.getUpdId() == null)   entity.setUpdId(principal.getUsername());
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