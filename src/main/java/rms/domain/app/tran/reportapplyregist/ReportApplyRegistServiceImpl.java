package rms.domain.app.tran.reportapplyregist;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rms.common.base.ApplicationProperties;
import rms.common.base.BusinessException;
import rms.common.consts.Const;
import rms.common.consts.MCodeConst;
import rms.common.dao.TReportApproveFlowDao;
import rms.common.dao.TReportDao;
import rms.common.dao.VMUserDao;
import rms.common.dao.VTReportDao;
import rms.common.entity.TReport;
import rms.common.entity.TReportApproveFlow;
import rms.common.entity.VMUser;
import rms.common.entity.VTReport;
import rms.common.utils.FileUtils;
import rms.common.utils.StringUtils;

/**
 * 月報申請画面サービス
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportApplyRegistServiceImpl implements ReportApplyRegistService {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApplyRegistServiceImpl.class);

    @Autowired
    protected MessageSource message;

    /** application.properties */
    protected ApplicationProperties properties = ApplicationProperties.INSTANCE;

    /** VMUserDao */
    @Autowired
    VMUserDao vMUserDao;

    /** TReportDao */
    @Autowired
    TReportDao tReportDao;

    /** TReportApproveFlowDao */
    @Autowired
    TReportApproveFlowDao tReportApproveFlowDao;

    /** VTReportDao */
    @Autowired
    VTReportDao vTReportDao;

    /**
     * 申請者のユーザ情報を取得
     * @param userId
     * @return
     */
    @Override
    public VMUser getApplyUserInfo(String userId) {
        return vMUserDao.selectById(userId);
    }

    /**
     * 月報情報の取得
     * @param applyUserId
     * @param targetYm
     * @return
     */
    @Override
    public VTReport getReportInfo(String applyUserId,
                                  String targetYm) {
        // 月報情報の取得
        VTReport vTReport = vTReportDao.selectById(applyUserId, Integer.valueOf(targetYm));
        logger.debug("取得情報 -> {}", vTReport);

        return vTReport;
    }

    /**
     * 月報情報の申請処理<br>
     * 補足：承認状況はメソッド内で自動設定
     * @param reportApplyRegistEntity
     * @throws IOException
     * @throws BusinessException
     * @throws NumberFormatException
     */
    @Override
    public void apply(ReportApplyRegistEntity reportApplyRegistEntity) throws IOException, NumberFormatException,
                                                                       BusinessException {

        /*
         * 業務ロジックチェック
         */
        // 月報の重複チェック
        validateUniquReport(reportApplyRegistEntity.getApplyUserId(),
                            Integer.valueOf(reportApplyRegistEntity.getTargetYm()));

        /*
         * 月報申請処理
         */
        // 申請情報の生成
        TReport tReport = new TReport();
        tReport.setApplyUserId(reportApplyRegistEntity.getApplyUserId());
        tReport.setTargetYm(Integer.valueOf(reportApplyRegistEntity.getTargetYm()));
        tReport.setApplyDate(LocalDateTime.now());
        tReport.setPublishFlg(reportApplyRegistEntity.getPublishFlg());
        tReport.setFilePath("");

        // 承認者の有無に合わせてステータスを設定
        if (!StringUtils.isEmpty(reportApplyRegistEntity.getApproveUserId1())) {
            tReport.setStatus(MCodeConst.A001_Y01);
        } else if (!StringUtils.isEmpty(reportApplyRegistEntity.getApproveUserId2())) {
            tReport.setStatus(MCodeConst.A001_Y02);
        } else {
            tReport.setStatus(MCodeConst.A001_Y03);
        }

        // 登録処理
        tReportDao.insert(tReport);

        /*
         * 月報承認フロー登録処理
         */
        // 月報承認フロー情報の生成
        TReportApproveFlow tReportApproveFlow = new TReportApproveFlow();
        tReportApproveFlow.setApplyUserId(reportApplyRegistEntity.getApplyUserId());
        tReportApproveFlow.setTargetYm(Integer.valueOf(reportApplyRegistEntity.getTargetYm()));

        // 登録処理：承認者１
        tReportApproveFlow.setApproveSeq(Const.APPROVE_FLOW_SEQ_1);
        tReportApproveFlow.setApproveUserId(reportApplyRegistEntity.getApproveUserId1());
        tReportApproveFlowDao.insert(tReportApproveFlow);
        // 登録処理：承認者２
        tReportApproveFlow.setApproveSeq(Const.APPROVE_FLOW_SEQ_2);
        tReportApproveFlow.setApproveUserId(reportApplyRegistEntity.getApproveUserId2());
        tReportApproveFlowDao.insert(tReportApproveFlow);
        // 登録処理：承認者３
        tReportApproveFlow.setApproveSeq(Const.APPROVE_FLOW_SEQ_3);
        tReportApproveFlow.setApproveUserId(reportApplyRegistEntity.getApproveUserId3());
        tReportApproveFlowDao.insert(tReportApproveFlow);

        /*
         * 月報ファイル保存処理
         */
        // 月報保存ファイルパスの生成
        Path filePath = FileUtils.createReportFilePath(properties.getString("myapp.report.storage"),
                                                       reportApplyRegistEntity.getApplyUserId(),
                                                       reportApplyRegistEntity.getTargetYm());
        // 月報保存処理
        FileUtils.reportSave(reportApplyRegistEntity.getFile().getInputStream(), filePath);

    }

    /**
     * 月報の重複チェック<br>
     * 重複している場合はBusinessExceptionを発生
     * @param applyUserId
     * @param targetYm
     * @throws BusinessException
     */
    private void validateUniquReport(String applyUserId,
                                     Integer targetYm) throws BusinessException {
        TReport tReport = tReportDao.selectById(applyUserId, targetYm);
        if (tReport != null) {
            // 「対象年月の月報は既に登録されています」
            throw new BusinessException(message.getMessage("error.003", null, Locale.getDefault()));
        }
    }

}
