package rms.domain.app.batch.reportinitregist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rms.common.base.BusinessException;
import rms.common.base.ProjectProperties;
import rms.common.consts.Const;
import rms.common.consts.MCodeConst;
import rms.common.consts.MessageEnum;
import rms.common.dao.MUserApproveFlowDao;
import rms.common.dao.TReportApproveFlowDao;
import rms.common.dao.TReportDao;
import rms.common.entity.MUser;
import rms.common.entity.MUserApproveFlow;
import rms.common.entity.TReport;
import rms.common.entity.TReportApproveFlow;
import rms.common.utils.RmsUtils;

/**
 * 月報初期データ登録バッチサービス
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportInitRegistServiceImpl implements ReportInitRegistService {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportInitRegistServiceImpl.class);

    /** application.properties */
    @Autowired
    private ProjectProperties properties;

    /** MUserApproveFlowDao */
    @Autowired
    private MUserApproveFlowDao mUserApproveFlowDao;

    /** TReportDao */
    @Autowired
    private TReportDao tReportDao;

    /** TReportApproveFlowDao */
    @Autowired
    private TReportApproveFlowDao tReportApproveFlowDao;

    /** 月報初期データ登録Dao */
    @Autowired
    private ReportInitRegistDao dao;

    @Override
    public void regist(Integer targetYm) throws Exception {

        // 実行日付の取得
        LocalDate execDate = properties.getSysdate();

        // 月報提出可能日を取得
        int switchDay = properties.getSwitchMonthReferenceDay();
        LocalDate switchDate = RmsUtils.getSwitchDate(targetYm, switchDay);

        if (execDate.isBefore(switchDate)) {
            // 実行日付 < 月報提出可能日
            String params = switchDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            // 「{}の月報初期データは{}以降に作成可能です。」
            throw new BusinessException(MessageEnum.error017, RmsUtils.formatTargetYm(targetYm), params);
        }

        // 指定された対象年月の月報が存在しないユーザ一覧を取得
        List<MUser> list = dao.noReportUserListByTargetYm(targetYm);
        for (MUser user : list) {
            logger.info("月報初期データ登録 -> {}", user.getUserId());
            /* 月報の初期データ登録 */
            TReport report = new TReport();
            report.setApplyUserId(user.getUserId());
            report.setTargetYm(targetYm);
            report.setStatus(MCodeConst.A001_AAA); // 未提出
            report.setInsId(Const.SYSTEM_USER_ID);
            report.setUpdId(Const.SYSTEM_USER_ID);
            tReportDao.insert(report);

            /* 月報承認フローの登録 */
            List<MUserApproveFlow> userFlowList = mUserApproveFlowDao.selectListByUserId(user.getUserId());
            for (MUserApproveFlow userFlow : userFlowList) {
                TReportApproveFlow reportFlow = new TReportApproveFlow();
                reportFlow.setApplyUserId(userFlow.getUserId());
                reportFlow.setTargetYm(targetYm);
                reportFlow.setApproveSeq(userFlow.getApproveSeq());
                reportFlow.setApproveUserId(userFlow.getApproveUserId());
                reportFlow.setInsId(Const.SYSTEM_USER_ID);
                reportFlow.setUpdId(Const.SYSTEM_USER_ID);
                tReportApproveFlowDao.insert(reportFlow);
            }
        }
    }

}
