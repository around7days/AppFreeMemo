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
 * TReportListenerクラス
 */
public class TReportListener implements EntityListener<TReport> {

    @Override
    public void preInsert(TReport entity,
                          PreInsertContext<TReport> context) {
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
    public void preUpdate(TReport entity,
                          PreUpdateContext<TReport> context) {
        //@formatter:off
        UserInfo userInfo = UserInfoAccessor.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        if (entity.getUpdId() == null)   entity.setUpdId(userInfo.getUserId());
        if (entity.getUpdDate() == null) entity.setUpdDate(now);
        //@formatter:on
    }

    @Override
    public void preDelete(TReport entity,
                          PreDeleteContext<TReport> context) {
    }

    @Override
    public void postInsert(TReport entity,
                           PostInsertContext<TReport> context) {
    }

    @Override
    public void postUpdate(TReport entity,
                           PostUpdateContext<TReport> context) {
    }

    @Override
    public void postDelete(TReport entity,
                           PostDeleteContext<TReport> context) {
    }
}