package rms.domain.app.tran.reportapproveregist;

import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rms.common.consts.Const;
import rms.common.consts.Const.StatusExecKbn;
import rms.common.consts.MCodeConst;
import rms.common.dao.TReportApproveFlowDao;
import rms.common.dao.TReportDao;
import rms.common.dao.VTReportDao;
import rms.common.entity.TReport;
import rms.common.entity.TReportApproveFlow;
import rms.common.entity.VTReport;
import rms.common.utils.RmsBeanUtils;
import rms.domain.app.shared.service.SharedReportFileService;
import rms.domain.app.shared.service.SharedReportService;

/**
 * 月報承認画面サービス
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportApproveRegistServiceImpl implements ReportApproveRegistService {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ReportApproveRegistServiceImpl.class);

    /** 月報関連共通サービス */
    @Autowired
    private SharedReportService sharedReportService;

    /** 月報ファイル関連共通サービス */
    @Autowired
    private SharedReportFileService sharedReportFileService;

    /** TReportDao */
    @Autowired
    private TReportDao tReportDao;

    /** TReportApproveFlowDao */
    @Autowired
    private TReportApproveFlowDao tReportApproveFlowDao;

    /** VTReportDao */
    @Autowired
    private VTReportDao vTReportDao;

    @Override
    public ReportApproveRegistDto initDisplay(String applyUserId,
                                              Integer targetYm) {
        // 月報情報の取得
        VTReport entity = vTReportDao.selectById(applyUserId, targetYm);

        // 返却用DTOに反映
        ReportApproveRegistDto dto = RmsBeanUtils.createCopyProperties(entity, ReportApproveRegistDto.class);

        return dto;
    }

    @Override
    public void approve(ReportApproveRegistDto dto) throws IOException {

        // 月報テーブル更新処理（承認）
        updateReport(dto, Const.StatusExecKbn.APPROVE);

        // 月報承認フローテーブル登録処理
        updateReportApproveFlow(dto);

        // 月報ファイル保存処理
        saveReportFile(dto);
    }

    @Override
    public void remand(ReportApproveRegistDto dto) throws IOException {

        // 月報テーブル更新処理（差戻）
        updateReport(dto, Const.StatusExecKbn.REMAND);
    }

    @Override
    public void deny(ReportApproveRegistDto dto) throws IOException {

        // 月報テーブル更新処理（否認）
        updateReport(dto, Const.StatusExecKbn.DENY);
    }

    /**
     * 月報テーブル更新処理
     * @param dto
     * @param execKbn
     */
    private void updateReport(ReportApproveRegistDto dto,
                              StatusExecKbn execKbn) {

        TReport entity = new TReport();

        /*
         * 主キー
         */
        entity.setApplyUserId(dto.getApplyUserId());
        entity.setTargetYm(dto.getTargetYm());
        entity.setVersion(dto.getVersion()); // 排他制御用バージョン

        /*
         * 更新項目
         */
        // 処理後の承認状況を計算
        String newStatus = sharedReportService.getNewStatus(dto.getApplyUserId(), dto.getTargetYm(), execKbn);
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
        entity.setTargetYm(dto.getTargetYm());
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
        case MCodeConst.A001_Y04:
            approveSeq = Const.APPROVE_FLOW_SEQ_4;
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
        // 月報ファイル保存処理
        sharedReportFileService.saveReportFile(dto.getFile(), dto.getApplyUserId(), dto.getTargetYm());
    }

}
