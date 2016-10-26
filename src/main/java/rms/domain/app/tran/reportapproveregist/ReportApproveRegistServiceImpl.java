package rms.domain.app.tran.reportapproveregist;

import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rms.common.consts.Const;
import rms.common.consts.MCodeConst;
import rms.common.dao.TReportApproveFlowDao;
import rms.common.dao.TReportDao;
import rms.common.dao.VTReportDao;
import rms.common.entity.TReport;
import rms.common.entity.TReportApproveFlow;
import rms.common.entity.VTReport;
import rms.common.utils.BeanUtilsImpl;
import rms.common.utils.StringUtilsImpl;
import rms.domain.app.shared.service.SharedReportFileService;

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

    /** 月報ファイル関連共通サービス */
    @Autowired
    SharedReportFileService sharedReportFileService;

    /** TReportDao */
    @Autowired
    TReportDao tReportDao;

    /** TReportApproveFlowDao */
    @Autowired
    TReportApproveFlowDao tReportApproveFlowDao;

    /** VTReportDao */
    @Autowired
    VTReportDao vTReportDao;

    @Override
    public ReportApproveRegistDto initDisplay(String applyUserId,
                                              Integer targetYm) {
        // 月報情報の取得
        VTReport entity = vTReportDao.selectById(applyUserId, targetYm);

        // 返却用DTOに反映
        ReportApproveRegistDto dto = BeanUtilsImpl.createCopyProperties(entity, ReportApproveRegistDto.class);

        return dto;
    }

    @Override
    public void approve(ReportApproveRegistDto dto) throws IOException {

        // 月報テーブル更新処理(承認)
        updateReportApprove(dto);

        // 月報承認フローテーブル登録処理
        updateReportApproveFlow(dto);

        // 月報ファイル保存処理
        saveReportFile(dto);
    }

    @Override
    public void deny(ReportApproveRegistDto dto) throws IOException {

        // 月報テーブル更新処理
        updateReportDeny(dto);
    }

    /**
     * 月報テーブル更新処理(承認)
     * @param dto
     */
    private void updateReportApprove(ReportApproveRegistDto dto) {

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
        // 承認状況（現在の承認状況と承認者の有無で判断）
        String newStatus = null;
        switch (dto.getStatus()) {
        case MCodeConst.A001_Y01:
            newStatus = MCodeConst.A001_Y02;
            if (StringUtilsImpl.isEmpty(dto.getApproveUserId2())) {
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
     * 月報テーブル更新処理(否認)
     * @param dto
     */
    private void updateReportDeny(ReportApproveRegistDto dto) {

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
        // 承認状況（現在の承認状況と承認者の有無で判断）
        String newStatus = null;
        switch (dto.getStatus()) {
        case MCodeConst.A001_Y01:
            newStatus = MCodeConst.A001_N01;
            break;
        case MCodeConst.A001_Y02:
            newStatus = MCodeConst.A001_N02;
            break;
        case MCodeConst.A001_Y03:
            newStatus = MCodeConst.A001_N03;
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
