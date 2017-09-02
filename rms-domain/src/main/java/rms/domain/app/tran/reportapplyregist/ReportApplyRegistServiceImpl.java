package rms.domain.app.tran.reportapplyregist;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rms.common.consts.Const;
import rms.common.consts.MCodeConst;
import rms.common.consts.MessageEnum;
import rms.common.dao.TReportApproveFlowDao;
import rms.common.dao.TReportDao;
import rms.common.dao.VMUserDao;
import rms.common.dao.VTReportDao;
import rms.common.entity.TReport;
import rms.common.entity.TReportApproveFlow;
import rms.common.entity.VMUser;
import rms.common.entity.VTReport;
import rms.common.exception.BusinessException;
import rms.common.utils.ProjectProperties;
import rms.common.utils.RmsBeanUtils;
import rms.common.utils.RmsStringUtils;
import rms.common.utils.RmsUtils;
import rms.domain.app.shared.service.SharedReportFileService;
import rms.domain.app.shared.service.SharedReportService;

/**
 * 月報申請画面サービス
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportApplyRegistServiceImpl implements ReportApplyRegistService {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ReportApplyRegistServiceImpl.class);

    /** application.properties */
    @Autowired
    private ProjectProperties properties;

    /** 月報関連共通サービス */
    @Autowired
    private SharedReportService sharedReportService;

    /** 月報ファイル関連共通サービス */
    @Autowired
    private SharedReportFileService sharedReportFileService;

    /** VMUserDao */
    @Autowired
    private VMUserDao vMUserDao;

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
    public ReportApplyRegistDto initDisplayApply(String applyUserId) {

        // ユーザ情報の取得
        VMUser entity = vMUserDao.selectById(applyUserId);

        // 返却用DTOに反映
        ReportApplyRegistDto dto = new ReportApplyRegistDto();
        dto.setApplyUserId(entity.getUserId());
        dto.setApplyUserNm(entity.getUserNm());
        dto.setApproveUserId1(entity.getApproveUserId1());
        dto.setApproveUserId2(entity.getApproveUserId2());
        dto.setApproveUserId3(entity.getApproveUserId3());
        dto.setApproveUserId4(entity.getApproveUserId4());
        dto.setApproveUserNm1(entity.getApproveUserNm1());
        dto.setApproveUserNm2(entity.getApproveUserNm2());
        dto.setApproveUserNm3(entity.getApproveUserNm3());
        dto.setApproveUserNm4(entity.getApproveUserNm4());

        // 初期値の設定
        Integer targetYm = RmsUtils.getThisTargetYm(properties.getSysdate(), properties.getSwitchMonthReferenceDay());
        dto.setTargetYm(targetYm); // 対象年月：当月

        return dto;
    }

    @Override
    public ReportApplyRegistDto initDisplayReApply(String applyUserId,
                                                   Integer targetYm) {

        // 月報情報の取得
        VTReport entity = vTReportDao.selectById(applyUserId, targetYm);

        // 返却用DTOに反映
        ReportApplyRegistDto dto = RmsBeanUtils.createCopyProperties(entity, ReportApplyRegistDto.class);

        return dto;
    }

    @Override
    public void apply(ReportApplyRegistDto dto) throws IOException, BusinessException {

        // 月報の重複チェック
        validateUniquReport(dto.getApplyUserId(), dto.getTargetYm());

        // 月報の未来日付チェック
        validateFutureYm(dto.getTargetYm());

        // 月報申請の物理削除
        deleteReport(dto);

        // 月報承認フローの物理削除
        deleteReportApproveFlow(dto);

        // 月報申請処理
        insertReport(dto);

        // 月報承認フロー登録処理
        insertReportApproveFlow(dto);

        // 承認状況の更新処理
        updateReportStatus(dto);

        // 月報ファイル保存処理
        saveReportFile(dto);
    }

    @Override
    public void reApply(ReportApplyRegistDto dto) throws IOException, BusinessException {

        // 月報申請の物理削除
        deleteReport(dto);

        // 月報承認フローの物理削除
        deleteReportApproveFlow(dto);

        // 月報申請処理
        insertReport(dto);

        // 月報承認フロー登録処理
        insertReportApproveFlow(dto);

        // 承認状況の更新処理
        updateReportStatus(dto);

        // 月報ファイル保存処理
        saveReportFile(dto);
    }

    /**
     * 月報の重複チェック<br>
     * 承認状況が「承認待ち」or「承認済み」の場合はBusinessExceptionを発生
     * @param applyUserId
     * @param targetYm
     * @throws BusinessException
     */
    private void validateUniquReport(String applyUserId,
                                     Integer targetYm) throws BusinessException {
        TReport entity = tReportDao.selectById(applyUserId, targetYm);
        if (entity == null) {
            return;
        }

        // 承認状況が「承認待ち」or「承認済み」の場合
        switch (entity.getStatus()) {
        case MCodeConst.A001_Y01:
        case MCodeConst.A001_Y02:
        case MCodeConst.A001_Y03:
        case MCodeConst.A001_Y04:
        case MCodeConst.A001_ZZZ:
            // 「対象年月の月報は既に申請されています」
            throw new BusinessException(MessageEnum.error003);
        }
    }

    /**
     * 月報の未来日付チェック<br>
     * 未来の月報を提出している場合はBusinessExceptionを発生<br>
     * 未来判定基準：当月のN日を経過していない
     * @param targetYm
     * @throws BusinessException
     */
    private void validateFutureYm(Integer targetYm) throws BusinessException {

        // 現在日付
        LocalDate sysdate = properties.getSysdate();

        // 月報提出可能日の取得
        int switchDay = properties.getSwitchMonthReferenceDay();
        LocalDate switchDate = RmsUtils.getSwitchDate(targetYm, switchDay);

        if (sysdate.isBefore(switchDate)) {
            // 現在日付 < 月報提出可能日
            // 「対象年月の月報は、{0}以降から申請可能です」
            String params = switchDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            throw new BusinessException(MessageEnum.error007, params);
        }
    }

    /**
     * 月報申請処理
     * @param dto
     */
    private void insertReport(ReportApplyRegistDto dto) {

        // 申請情報の生成
        TReport entity = new TReport();
        entity.setApplyUserId(dto.getApplyUserId());
        entity.setTargetYm(dto.getTargetYm());
        entity.setApplyDate(LocalDateTime.now());
        entity.setFilePath("");
        entity.setStatus(MCodeConst.A001_AAA); // 承認状況は後で再計算する。

        // 登録処理
        tReportDao.insert(entity);
    }

    /**
     * 月報承認フロー登録処理
     * @param dto
     */
    private void insertReportApproveFlow(ReportApplyRegistDto dto) {
        // 月報承認フロー情報の生成
        TReportApproveFlow entity = new TReportApproveFlow();
        entity.setApplyUserId(dto.getApplyUserId());
        entity.setTargetYm(dto.getTargetYm());

        // 登録処理：承認者１
        if (!RmsStringUtils.isEmpty(dto.getApproveUserId1())) {
            entity.setApproveSeq(Const.APPROVE_FLOW_SEQ_1);
            entity.setApproveUserId(dto.getApproveUserId1());
            tReportApproveFlowDao.insert(entity);
        }
        // 登録処理：承認者２
        if (!RmsStringUtils.isEmpty(dto.getApproveUserId2())) {
            entity.setApproveSeq(Const.APPROVE_FLOW_SEQ_2);
            entity.setApproveUserId(dto.getApproveUserId2());
            tReportApproveFlowDao.insert(entity);
        }
        // 登録処理：承認者３
        if (!RmsStringUtils.isEmpty(dto.getApproveUserId3())) {
            entity.setApproveSeq(Const.APPROVE_FLOW_SEQ_3);
            entity.setApproveUserId(dto.getApproveUserId3());
            tReportApproveFlowDao.insert(entity);
        }
        // 登録処理：承認者４
        if (!RmsStringUtils.isEmpty(dto.getApproveUserId4())) {
            entity.setApproveSeq(Const.APPROVE_FLOW_SEQ_4);
            entity.setApproveUserId(dto.getApproveUserId4());
            tReportApproveFlowDao.insert(entity);
        }
    }

    /**
     * 承認状況の更新処理
     * @param dto
     */
    private void updateReportStatus(ReportApplyRegistDto dto) {

        // 処理後の承認状況を計算
        String newStatus = sharedReportService.getNewStatus(dto.getApplyUserId(),
                                                            dto.getTargetYm(),
                                                            Const.StatusExecKbn.APPLY);

        // 月報更新情報の生成
        TReport entity = new TReport();
        entity.setApplyUserId(dto.getApplyUserId());
        entity.setTargetYm(dto.getTargetYm());
        entity.setStatus(newStatus);
        tReportDao.updateNoOptimisticLockException(entity);
    }

    /**
     * 月報申請の物理削除処理
     * @param dto
     */
    private void deleteReport(ReportApplyRegistDto dto) {
        // 月報申請情報の生成
        TReport entity = new TReport();
        entity.setApplyUserId(dto.getApplyUserId());
        entity.setTargetYm(dto.getTargetYm());

        // 削除処理
        tReportDao.deleteNoOptimisticLockException(entity);
    }

    /**
     * 月報承認フローの物理削除処理
     * @param dto
     */
    private void deleteReportApproveFlow(ReportApplyRegistDto dto) {

        // 申請者ID、対象年月に紐付くレコードを全て削除
        tReportApproveFlowDao.deleteListByApplyUserIdAndTargetYm(dto.getApplyUserId(), dto.getTargetYm());
    }

    /**
     * 月報ファイル保存処理
     * @param dto
     * @throws IOException
     */
    private void saveReportFile(ReportApplyRegistDto dto) throws IOException {
        // 月報ファイル保存処理
        sharedReportFileService.saveReportFile(dto.getFile(), dto.getApplyUserId(), dto.getTargetYm());
    }

}
