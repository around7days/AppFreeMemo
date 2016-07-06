package rms.com.doma.entity;

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
 * MUserListenerクラス
 */
public class MUserListener implements EntityListener<MUser> {

    // 認証情報
    private static UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                                                                              .getAuthentication()
                                                                              .getPrincipal();
    @Override
    public void preInsert(MUser entity, PreInsertContext<MUser> context) {
        LocalDateTime now = LocalDateTime.now();

        if (entity.getDelFlg() == null) {
            entity.setDelFlg(0);
        }
        if (entity.getInsId() == null) {
            entity.setInsId(principal.getUsername());
        }
        if (entity.getInsDate() == null) {
            entity.setInsDate(now);
        }
        if (entity.getUpdId() == null) {
            entity.setUpdId(principal.getUsername());
        }
        if (entity.getUpdDate() == null) {
            entity.setUpdDate(now);
        }
    }

    @Override
    public void preUpdate(MUser entity, PreUpdateContext<MUser> context) {
        LocalDateTime now = LocalDateTime.now();

        if (entity.getUpdId() == null) {
            entity.setUpdId(principal.getUsername());
        }
        if (entity.getUpdDate() == null) {
            entity.setUpdDate(now);
        }
    }

    @Override
    public void preDelete(MUser entity, PreDeleteContext<MUser> context) {
    }

    @Override
    public void postInsert(MUser entity, PostInsertContext<MUser> context) {
    }

    @Override
    public void postUpdate(MUser entity, PostUpdateContext<MUser> context) {
    }

    @Override
    public void postDelete(MUser entity, PostDeleteContext<MUser> context) {
    }
}