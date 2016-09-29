package rms.domain.app.tran.reportapproveregist;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rms.common.base.ApplicationProperties;
import rms.common.consts.Const;
import rms.common.consts.MCodeConst;
import rms.common.dao.TReportApproveFlowDao;
import rms.common.dao.TReportDao;
import rms.common.dao.VTReportDao;
import rms.common.entity.TReport;
import rms.common.entity.TReportApproveFlow;
import rms.common.entity.VTReport;
import rms.common.utils.FileUtils;
import rms.common.utils.StringUtils;

/**
 * 月報承認画面サービス
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportApproveRegistServiceImpl implements ReportApproveRegistService {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApproveRegistServiceImpl.class);

    /** application.properties */
    protected ApplicationProperties properties = ApplicationProperties.INSTANCE;

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
     * 月報情報の取得
     * @param applyUserId
     * @param targetYm
     * @return
     */
    @Override
    public VTReport getReportInfo(String applyUserId,
                                  String targetYm) {
        // 月報情報の取得
        VTReport entity = vTReportDao.selectById(applyUserId, Integer.valueOf(targetYm));
        logger.debug("取得情報 -> {}", entity);

        return entity;
    }

    /**
     * 月報情報の承認処理<br>
     * 補足：承認状況はメソッド内で自動設定
     * @param dto
     * @throws IOException
     */
    @Override
    public void approve(ReportApproveRegistDto dto) throws IOException {

        // 月報テーブル更新処理
        updateReport(dto);

        // 月報承認フローテーブル登録処理
        updateReportApproveFlow(dto);

        // 月報ファイル保存処理
        saveReportFile(dto);
    }

    /**
     * 月報テーブル更新処理
     * @param dto
     */
    private void updateReport(ReportApproveRegistDto dto) {

        TReport entity = new TReport();

        /*
         * 主キー
         */
        entity.setApplyUserId(dto.getApplyUserId());
        entity.setTargetYm(Integer.valueOf(dto.getTargetYm()));
        entity.setVersion(dto.getVersion()); // 排他制御用バージョン

        /*
         * 更新項目
         */
        // 承認状況（現在の承認状況と承認者の有無で判断）
        String newStatus = null;
        switch (dto.getStatus()) {
        case MCodeConst.A001_Y01:
            newStatus = MCodeConst.A001_Y02;
            if (StringUtils.isEmpty(dto.getApproveUserNm2())) {
                newStatus = MCodeConst.A001_Y03;
            }
            break;
        case MCodeConst.A001_Y02:
            newStatus = MCodeConst.A001_Y03;
            break;
        case MCodeConst.A001_Y03:
            newStatus = MCodeConst.A001_ZZZ;
            break;
        }
        entity.setStatus(newStatus);

        /*
         * 更新処理
         */
        tReportDao.update(entity);
    }

    /**
     * 月報承認フローテーブル更新処理
     * @param dto
     */
    private void updateReportApproveFlow(ReportApproveRegistDto dto) {

        TReportApproveFlow entity = new TReportApproveFlow();

        /*
         * 主キー
         */
        entity.setApplyUserId(dto.getApplyUserId());
        entity.setTargetYm(Integer.valueOf(dto.getTargetYm()));
        // SEQ（現在の承認状況で判断）
        int approveSeq = -1;
        switch (dto.getStatus()) {
        case MCodeConst.A001_Y01:
            approveSeq = Const.APPROVE_FLOW_SEQ_1;
            break;
        case MCodeConst.A001_Y02:
            approveSeq = Const.APPROVE_FLOW_SEQ_2;
            break;
        case MCodeConst.A001_Y03:
            approveSeq = Const.APPROVE_FLOW_SEQ_3;
            break;
        }
        entity.setApproveSeq(approveSeq);

        /*
         * 更新項目
         */
        // 承認日
        entity.setApproveDate(LocalDateTime.now());

        /*
         * 更新処理（排他制御なし）
         */
        tReportApproveFlowDao.updateNoOptimisticLockException(entity);
    }

    /**
     * 月報ファイル保存処理
     * @param dto
     * @throws IOException
     */
    private void saveReportFile(ReportApproveRegistDto dto) throws IOException {
        // 月報保存ファイルパスの生成
        Path filePath = FileUtils.createReportFilePath(properties.getString("myapp.report.storage"),
                                                       dto.getApplyUserId(),
                                                       dto.getTargetYm());
        // 月報保存処理
        FileUtils.reportSave(dto.getFile().getInputStream(), filePath);
    }

}
