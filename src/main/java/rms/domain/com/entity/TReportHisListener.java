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
 * TReportHisListenerクラス
 */
public class TReportHisListener implements EntityListener<TReportHis> {

    @Override
    public void preInsert(TReportHis entity,
                          PreInsertContext<TReportHis> context) {
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
    public void preUpdate(TReportHis entity,
                          PreUpdateContext<TReportHis> context) {
        //@formatter:off
        UserInfo userInfo = AuthenticationUtils.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        if (entity.getUpdId() == null)   entity.setUpdId(userInfo.getUserId());
        if (entity.getUpdDate() == null) entity.setUpdDate(now);
        //@formatter:on
    }

    @Override
    public void preDelete(TReportHis entity,
                          PreDeleteContext<TReportHis> context) {
    }

    @Override
    public void postInsert(TReportHis entity,
                           PostInsertContext<TReportHis> context) {
    }

    @Override
    public void postUpdate(TReportHis entity,
                           PostUpdateContext<TReportHis> context) {
    }

    @Override
    public void postDelete(TReportHis entity,
                           PostDeleteContext<TReportHis> context) {
    }
}