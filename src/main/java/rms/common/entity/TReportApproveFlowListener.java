package rms.common.entity;

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
 * TReportApproveFlowListenerクラス
 */
public class TReportApproveFlowListener implements EntityListener<TReportApproveFlow> {

    @Override
    public void preInsert(TReportApproveFlow entity,
                          PreInsertContext<TReportApproveFlow> context) {
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
    public void preUpdate(TReportApproveFlow entity,
                          PreUpdateContext<TReportApproveFlow> context) {
        //@formatter:off
        UserInfo userInfo = AuthenticationUtils.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        if (entity.getUpdId() == null)   entity.setUpdId(userInfo.getUserId());
        if (entity.getUpdDate() == null) entity.setUpdDate(now);
        //@formatter:on
    }

    @Override
    public void preDelete(TReportApproveFlow entity,
                          PreDeleteContext<TReportApproveFlow> context) {
    }

    @Override
    public void postInsert(TReportApproveFlow entity,
                           PostInsertContext<TReportApproveFlow> context) {
    }

    @Override
    public void postUpdate(TReportApproveFlow entity,
                           PostUpdateContext<TReportApproveFlow> context) {
    }

    @Override
    public void postDelete(TReportApproveFlow entity,
                           PostDeleteContext<TReportApproveFlow> context) {
    }
}