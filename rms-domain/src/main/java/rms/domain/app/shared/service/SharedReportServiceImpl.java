package rms.domain.app.shared.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rms.common.consts.Const.StatusExecKbn;
import rms.common.consts.MCodeConst;
import rms.common.dao.VTReportDao;
import rms.common.entity.VTReport;
import rms.common.utils.ProjectProperties;
import rms.common.utils.RmsStringUtils;

/**
 * 月報関連共通サービス実装
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SharedReportServiceImpl implements SharedReportService {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(SharedReportServiceImpl.class);

    /** application.properties */
    @SuppressWarnings("unused")
    @Autowired
    private ProjectProperties properties;

    @Autowired
    protected VTReportDao vTReportDao;

    @Override
    public String getNewStatus(String applyUserId,
                               Integer targetYm,
                               StatusExecKbn execKbn) {
        // 現在の承認状況、承認者１～４の取得
        VTReport entity = vTReportDao.selectById(applyUserId, targetYm);
        return getNewStatus(entity, execKbn);
    }

    // TODO publicの理由はテストコードから直接参照させる為（リフレクション使った方がいい？）
    public String getNewStatus(VTReport entity,
                               StatusExecKbn execKbn) {
        // 処理後の承認状況
        String newStatus = null;

        String status = entity.getStatus();
        String approveUserId1 = entity.getApproveUserId1();
        String approveUserId2 = entity.getApproveUserId2();
        String approveUserId3 = entity.getApproveUserId3();
        String approveUserId4 = entity.getApproveUserId4();

        // 承認状況の計算（現在の承認状況と承認者の有無で判断）
        switch (execKbn) {
        case APPLY:
            // 申請
            if (!RmsStringUtils.isEmpty(approveUserId1)) {
                newStatus = MCodeConst.A001_Y01;
            } else if (!RmsStringUtils.isEmpty(approveUserId2)) {
                newStatus = MCodeConst.A001_Y02;
            } else if (!RmsStringUtils.isEmpty(approveUserId3)) {
                newStatus = MCodeConst.A001_Y03;
            } else if (!RmsStringUtils.isEmpty(approveUserId4)) {
                newStatus = MCodeConst.A001_Y04;
            } else {
                newStatus = MCodeConst.A001_ZZZ;
            }
            break;

        case APPROVE:
            // 承認
            if (MCodeConst.A001_Y01.equals(status)) {
                if (!RmsStringUtils.isEmpty(approveUserId2)) {
                    newStatus = MCodeConst.A001_Y02;
                } else if (!RmsStringUtils.isEmpty(approveUserId3)) {
                    newStatus = MCodeConst.A001_Y03;
                } else if (!RmsStringUtils.isEmpty(approveUserId4)) {
                    newStatus = MCodeConst.A001_Y04;
                } else {
                    newStatus = MCodeConst.A001_ZZZ;
                }
            } else if (MCodeConst.A001_Y02.equals(status)) {
                if (!RmsStringUtils.isEmpty(approveUserId3)) {
                    newStatus = MCodeConst.A001_Y03;
                } else if (!RmsStringUtils.isEmpty(approveUserId4)) {
                    newStatus = MCodeConst.A001_Y04;
                } else {
                    newStatus = MCodeConst.A001_ZZZ;
                }
            } else if (MCodeConst.A001_Y03.equals(status)) {
                if (!RmsStringUtils.isEmpty(approveUserId4)) {
                    newStatus = MCodeConst.A001_Y04;
                } else {
                    newStatus = MCodeConst.A001_ZZZ;
                }
            } else if (MCodeConst.A001_Y04.equals(status)) {
                newStatus = MCodeConst.A001_ZZZ;
            }
            break;

        case REMAND:
            // 差戻
            if (MCodeConst.A001_Y04.equals(status)) {
                if (!RmsStringUtils.isEmpty(approveUserId3)) {
                    newStatus = MCodeConst.A001_Y03;
                } else if (!RmsStringUtils.isEmpty(approveUserId2)) {
                    newStatus = MCodeConst.A001_Y02;
                } else if (!RmsStringUtils.isEmpty(approveUserId1)) {
                    newStatus = MCodeConst.A001_Y01;
                } else {
                    newStatus = MCodeConst.A001_N04;
                }
            } else if (MCodeConst.A001_Y03.equals(status)) {
                if (!RmsStringUtils.isEmpty(approveUserId2)) {
                    newStatus = MCodeConst.A001_Y02;
                } else if (!RmsStringUtils.isEmpty(approveUserId1)) {
                    newStatus = MCodeConst.A001_Y01;
                } else {
                    newStatus = MCodeConst.A001_N03;
                }
            } else if (MCodeConst.A001_Y02.equals(status)) {
                if (!RmsStringUtils.isEmpty(approveUserId1)) {
                    newStatus = MCodeConst.A001_Y01;
                } else {
                    newStatus = MCodeConst.A001_N02;
                }
            } else if (MCodeConst.A001_Y01.equals(status)) {
                newStatus = MCodeConst.A001_N01;
            }
            break;

        case DENY:
            // 否認
            switch (status) {
            case MCodeConst.A001_Y01:
                newStatus = MCodeConst.A001_N01;
                break;
            case MCodeConst.A001_Y02:
                newStatus = MCodeConst.A001_N02;
                break;
            case MCodeConst.A001_Y03:
                newStatus = MCodeConst.A001_N03;
                break;
            case MCodeConst.A001_Y04:
                newStatus = MCodeConst.A001_N04;
                break;
            }
            break;

        }

        logger.debug("承認状況 -> {} → {} ", status, newStatus);

        return newStatus;
    }

}
