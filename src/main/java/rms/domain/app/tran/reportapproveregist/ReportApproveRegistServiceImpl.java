package rms.domain.app.tran.reportapproveregist;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rms.common.base.ApplicationProperties;
import rms.common.base.BusinessException;
import rms.common.consts.MCodeConst;
import rms.common.dao.TReportDao;
import rms.common.dao.VTReportDao;
import rms.common.model.TReport;
import rms.common.model.VTReport;
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
        VTReport vTReport = vTReportDao.selectById(applyUserId, Integer.valueOf(targetYm));
        logger.debug("取得情報 -> {}", vTReport);

        return vTReport;
    }

    /**
     * 月報情報の承認処理<br>
     * 補足：承認状況はメソッド内で自動設定
     * @param reportApproveRegistEntity
     * @throws BusinessException
     * @throws IOException
     */
    @Override
    public void approve(ReportApproveRegistEntity reportApproveRegistEntity) throws BusinessException, IOException {

        /*
         * 承認処理
         */
        // 承認情報の生成
        TReport tReport = new TReport();
        // 主キーの設定
        tReport.setApplyUserId(reportApproveRegistEntity.getApplyUserId());
        tReport.setTargetYm(Integer.valueOf(reportApproveRegistEntity.getTargetYm()));
        // 排他制御用バージョンの設定
        tReport.setVersion(reportApproveRegistEntity.getVersion());

        // 現在の承認状況と承認者の有無に合わせてステータスを設定
        String newStatus;
        switch (reportApproveRegistEntity.getStatus()) {
        case MCodeConst.A001_Y01:
            newStatus = MCodeConst.A001_Y02;
            if (StringUtils.isEmpty(reportApproveRegistEntity.getApproveUserNm2())) {
                newStatus = MCodeConst.A001_Y03;
            }
            break;
        case MCodeConst.A001_Y02:
            newStatus = MCodeConst.A001_Y03;
            break;
        case MCodeConst.A001_Y03:
            newStatus = MCodeConst.A001_ZZZ;
            break;
        default:
            throw new BusinessException("例外エラー");
        }
        tReport.setStatus(newStatus);

        // 更新処理
        tReportDao.update(tReport);

        /*
         * 月報ファイル保存処理
         */
        // 月報保存ファイルパスの生成
        Path filePath = FileUtils.createReportFilePath(properties.getString("myapp.report.storage"),
                                                       reportApproveRegistEntity.getApplyUserId(),
                                                       reportApproveRegistEntity.getTargetYm());
        // 月報保存処理
        FileUtils.reportSave(reportApproveRegistEntity.getFile().getInputStream(), filePath);
    }

}
