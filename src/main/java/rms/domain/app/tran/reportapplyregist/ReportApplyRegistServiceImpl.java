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
        VTReport entity = vTReportDao.selectById(applyUserId, Integer.valueOf(targetYm));
        logger.debug("取得情報 -> {}", entity);

        return entity;
    }

    /**
     * 月報情報の申請処理<br>
     * 補足：承認状況はメソッド内で自動設定
     * @param dto
     * @throws IOException
     * @throws BusinessException
     * @throws NumberFormatException
     */
    @Override
    public void apply(ReportApplyRegistDto dto) throws IOException, NumberFormatException, BusinessException {

        // 月報の重複チェック
        validateUniquReport(dto.getApplyUserId(), Integer.valueOf(dto.getTargetYm()));

        // 月報申請処理
        insertReport(dto);

        //月報承認フロー登録処理
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
        TReport entity = tReportDao.selectById(applyUserId, targetYm);
        if (entity != null) {
            // 「対象年月の月報は既に登録されています」
            throw new BusinessException(message.getMessage("error.003", null, Locale.getDefault()));
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
        entity.setTargetYm(Integer.valueOf(dto.getTargetYm()));
        entity.setApplyDate(LocalDateTime.now());
        entity.setPublishFlg(dto.getPublishFlg());
        entity.setFilePath("");

        // 承認者の有無に合わせてステータスを設定
        if (!StringUtils.isEmpty(dto.getApproveUserId1())) {
            entity.setStatus(MCodeConst.A001_Y01);
        } else if (!StringUtils.isEmpty(dto.getApproveUserId2())) {
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
        entity.setTargetYm(Integer.valueOf(dto.getTargetYm()));

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
     * 月報ファイル保存処理
     * @param dto
     * @throws IOException
     */
    private void saveReportFile(ReportApplyRegistDto dto) throws IOException {
        // 月報保存ファイルパスの生成
        Path filePath = FileUtils.createReportFilePath(properties.getString("myapp.report.storage"),
                                                       dto.getApplyUserId(),
                                                       dto.getTargetYm());
        // 月報保存処理
        FileUtils.reportSave(dto.getFile().getInputStream(), filePath);
    }

}
