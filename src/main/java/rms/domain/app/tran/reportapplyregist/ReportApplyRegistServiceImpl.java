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

import rms.common.base.BusinessException;
import rms.common.base.ProjectProperties;
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
import rms.common.utils.BeanUtilsImpl;
import rms.common.utils.StringUtilsImpl;
import rms.domain.app.shared.service.SharedReportFileService;

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
    private static final ProjectProperties properties = ProjectProperties.INSTANCE;

    /** 月報ファイル関連共通サービス */
    @Autowired
    SharedReportFileService sharedReportFileService;

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
        dto.setApproveUserNm1(entity.getApproveUserNm1());
        dto.setApproveUserNm2(entity.getApproveUserNm2());
        dto.setApproveUserNm3(entity.getApproveUserNm3());

        // 初期値の設定
        dto.setPublishFlg(MCodeConst.B001_1); // 公開有無：公開

        return dto;
    }

    @Override
    public ReportApplyRegistDto initDisplayReApply(String applyUserId,
                                                   Integer targetYm) {

        // 月報情報の取得
        VTReport entity = vTReportDao.selectById(applyUserId, targetYm);

        // 返却用DTOに反映
        ReportApplyRegistDto dto = BeanUtilsImpl.createCopyProperties(entity, ReportApplyRegistDto.class);

        return dto;
    }

    @Override
    public void apply(ReportApplyRegistDto dto) throws IOException, BusinessException {

        // 月報の重複チェック
        validateUniquReport(dto.getApplyUserId(), dto.getTargetYm());

        // 月報の未来日付チェック
        validateFutureYm(dto.getTargetYm());

        // 月報申請処理
        insertReport(dto);

        // 月報承認フロー登録処理
        insertReportApproveFlow(dto);

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

        // 月報ファイル保存処理
        saveReportFile(dto);
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
        boolean hasExists = tReportDao.existsById(applyUserId, targetYm);
        if (hasExists) {
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

        // 月報提出可能日を生成
        int targetYear = Integer.valueOf(targetYm.toString().substring(0, 4));
        int targetMonth = Integer.valueOf(targetYm.toString().substring(4, 6));
        int applyPossibleDay = properties.getInteger("report.apply.possible.day");
        LocalDate applyPossibleDate = LocalDate.of(targetYear, targetMonth, applyPossibleDay);

        // 現在の年月日を取得
        LocalDate nowdate = LocalDate.now();

        if (applyPossibleDate.compareTo(nowdate) >= 0) {
            // 月報提出可能日 >= 現在日付の場合
            // 「対象年月の月報は、{0}以降から申請可能です」
            String params = applyPossibleDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
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
        entity.setPublishFlg(dto.getPublishFlg());
        entity.setFilePath("");

        // 承認者の有無に合わせてステータスを設定
        if (!StringUtilsImpl.isEmpty(dto.getApproveUserId1())) {
            entity.setStatus(MCodeConst.A001_Y01);
        } else if (!StringUtilsImpl.isEmpty(dto.getApproveUserId2())) {
            entity.setStatus(MCodeConst.A001_Y02);
        } else {
            entity.setStatus(MCodeConst.A001_Y03);
        }

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
        entity.setApproveSeq(Const.APPROVE_FLOW_SEQ_1);
        entity.setApproveUserId(dto.getApproveUserId1());
        tReportApproveFlowDao.insert(entity);
        // 登録処理：承認者２
        entity.setApproveSeq(Const.APPROVE_FLOW_SEQ_2);
        entity.setApproveUserId(dto.getApproveUserId2());
        tReportApproveFlowDao.insert(entity);
        // 登録処理：承認者３の
        entity.setApproveSeq(Const.APPROVE_FLOW_SEQ_3);
        entity.setApproveUserId(dto.getApproveUserId3());
        tReportApproveFlowDao.insert(entity);
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
        entity.setVersion(dto.getVersion());

        // 削除処理
        tReportDao.delete(entity);
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
