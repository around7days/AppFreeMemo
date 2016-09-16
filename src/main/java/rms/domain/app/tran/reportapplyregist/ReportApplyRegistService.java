package rms.domain.app.tran.reportapplyregist;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rms.common.base.BusinessException;
import rms.common.consts.MCodeConst;
import rms.common.dao.TReportDao;
import rms.common.dao.VMUserDao;
import rms.common.dao.VTReportDao;
import rms.common.model.TReport;
import rms.common.model.VMUser;
import rms.common.model.VTReport;
import rms.common.utils.FileUtils;
import rms.common.utils.StringUtils;

/**
 * 月報申請画面サービス
 * @author
 */
@Service
public class ReportApplyRegistService extends rms.common.abstracts.AbstractService {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApplyRegistService.class);

    /** VMUserDao */
    @Autowired
    VMUserDao vMUserDao;

    /** TReportDao */
    @Autowired
    TReportDao tReportDao;

    /** VTReportDao */
    @Autowired
    VTReportDao vTReportDao;

    /**
     * 申請者のユーザ情報を取得
     * @param userId
     * @return
     */
    public VMUser getApplyUserInfo(String userId) {
        return vMUserDao.selectById(userId);
    }

    /**
     * 月報情報の取得
     * @param applyUserId
     * @param targetYm
     * @return
     */
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
        tReport.setApproveUserId1(reportApplyRegistEntity.getApproveUserId1());
        tReport.setApproveUserId2(reportApplyRegistEntity.getApproveUserId2());
        tReport.setApproveUserId3(reportApplyRegistEntity.getApproveUserId3());
        tReport.setFilePath("");

        // 承認者の有無に合わせてステータスを設定
        if (!StringUtils.isEmpty(tReport.getApproveUserId1())) {
            tReport.setStatus(MCodeConst.A001_Y01);
        } else if (!StringUtils.isEmpty(tReport.getApproveUserId2())) {
            tReport.setStatus(MCodeConst.A001_Y02);
        } else {
            tReport.setStatus(MCodeConst.A001_Y03);
        }

        // 登録処理
        tReportDao.insert(tReport);

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
