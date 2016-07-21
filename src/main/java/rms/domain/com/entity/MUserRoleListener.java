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
 * MUserRoleListenerクラス
 */
public class MUserRoleListener implements EntityListener<MUserRole> {

    // 認証情報
    private static UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                                                                              .getAuthentication()
                                                                              .getPrincipal();
    @Override
    public void preInsert(MUserRole entity, PreInsertContext<MUserRole> context) {
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
    public void preUpdate(MUserRole entity, PreUpdateContext<MUserRole> context) {
        //@formatter:off
        LocalDateTime now = LocalDateTime.now();
        if (entity.getUpdId() == null)   entity.setUpdId(principal.getUsername());
        if (entity.getUpdDate() == null) entity.setUpdDate(now);
        //@formatter:on
    }

    @Override
    public void preDelete(MUserRole entity, PreDeleteContext<MUserRole> context) {
    }

    @Override
    public void postInsert(MUserRole entity, PostInsertContext<MUserRole> context) {
    }

    @Override
    public void postUpdate(MUserRole entity, PostUpdateContext<MUserRole> context) {
    }

    @Override
    public void postDelete(MUserRole entity, PostDeleteContext<MUserRole> context) {
    }
}