package rms.domain.app.tran.reportapproveregistbulk;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import rms.common.auth.UserInfo;
import rms.common.base.BusinessException;
import rms.common.consts.Const;
import rms.common.consts.MCodeConst;
import rms.common.consts.MessageEnum;
import rms.common.dao.TReportApproveFlowDao;
import rms.common.dao.TReportDao;
import rms.common.dao.VTReportDao;
import rms.common.entity.TReport;
import rms.common.entity.TReportApproveFlow;
import rms.common.entity.VTReport;
import rms.common.utils.NumberUtils;
import rms.common.utils.StringUtils;
import rms.domain.app.shared.dto.ReportFileDto;
import rms.domain.app.shared.service.SharedReportFileService;

/**
 * 月報一括承認画面サービス
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportApproveRegistBulkServiceImpl implements ReportApproveRegistBulkService {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ReportApproveRegistBulkServiceImpl.class);

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

    /**
     * 月報情報の承認処理<br>
     * @param file
     * @param userInfo
     * @return
     * @throws IOException
     * @throws BusinessException
     */
    @Override
    public List<ReportApproveRegistBulkDto> approveBulk(MultipartFile file,
                                                        UserInfo userInfo) throws IOException, BusinessException {

        List<ReportApproveRegistBulkDto> resultList = new ArrayList<>();

        // 月報zipファイルを解凍して月報ファイル一覧を取得
        List<ReportFileDto> reportList = sharedReportFileService.unZipReportFileInfo(file);
        for (ReportFileDto dto : reportList) {

            ReportApproveRegistBulkDto result = new ReportApproveRegistBulkDto();

            try {
                result.setFileNm(dto.getFileNm());

                // 月報ファイル名の確認
                validateReportFileNm(dto.getFileNm());

                // データベースから月報ファイル名を基に月報情報を取得
                VTReport entity = getReportInfo(dto.getFileNm());
                result.setTargetYm(entity.getTargetYm());
                result.setApplyUserId(entity.getApplyUserId());
                result.setApplyUserNm(entity.getApplyUserNm());

                // 承認権限有無の確認
                validateApproveAuthority(entity, userInfo);

                // 月報テーブル更新処理(承認)
                updateReportApprove(entity);

                // 月報承認フローテーブル登録処理
                updateReportApproveFlow(entity);

                // 月報ファイル保存処理
                saveReportFile(dto.getFilePath(), entity);

                result.setStatus(ReportApproveRegistBulkConst.RESULT_OK);

            } catch (BusinessException e) {
                // エラー自体は握りつぶす

                result.setStatus(ReportApproveRegistBulkConst.RESULT_NG);
                result.setComment(e.getErrorMessage());
            }

            resultList.add(result);
        }

        return resultList;
    }

    /**
     * 月報ファイル名の確認<br>
     * 正しくない場合はBusinessExceptionを発生させる
     * @param reportFileNm
     * @throws BusinessException
     */
    private void validateReportFileNm(String reportFileNm) throws BusinessException {
        String[] arys = reportFileNm.split(Const.REPORT_FILE_DELIMITER);
        if (arys.length <= 2) {
            // 「月報ファイル名のフォーマットが正しくありません(yyyymm_userId_userNm.xlsx)」
            throw new BusinessException(MessageEnum.error008);

        }
        if (!NumberUtils.isInteger(arys[0])) {
            // 「月報ファイル名のフォーマットが正しくありません(yyyymm_userId_userNm.xlsx)」
            throw new BusinessException(MessageEnum.error008);
        }
    }

    /**
     * 月報情報の取得<br>
     * 月報情報が取得できない場合、BusinessExceptionを発生させる<br>
     * @param reportFileNm
     * @return
     * @throws BusinessException
     */
    private VTReport getReportInfo(String reportFileNm) throws BusinessException {

        // 月報ファイル名から対象年月と申請者IDを取得
        String[] arys = reportFileNm.split(Const.REPORT_FILE_DELIMITER);
        Integer targetYm = Integer.valueOf(arys[0]);
        String applyUserId = arys[1];

        // データベースから月報情報を取得
        VTReport entity = vTReportDao.selectById(applyUserId, targetYm);
        if (entity == null) {
            // 「月報情報が見つかりません」
            throw new BusinessException(MessageEnum.error009);
        }

        return entity;
    }

    /**
     * 承認権限有無の確認<br>
     * 承認権限がない場合、BusinessExceptionを発生させる<br>
     * @param vTReport
     * @param userInfo
     * @throws BusinessException
     */
    private void validateApproveAuthority(VTReport vTReport,
                                          UserInfo userInfo) throws BusinessException {

        // 承認者
        String approveUserId = userInfo.getUserId();

        // 承認権限有無（true:あり false:なし）
        boolean isApproveAuth = false;

        // TODO 登録画面と判断処理が同じ・・・共通化すべき？（ウソ）

        // 承認権限有無判定（現在の承認状況と承認者から判断）
        switch (vTReport.getStatus()) {
        case MCodeConst.A001_Y01:
            if (approveUserId.equals(vTReport.getApproveUserId1())) {
                isApproveAuth = true;
            }
            break;
        case MCodeConst.A001_Y02:
            if (approveUserId.equals(vTReport.getApproveUserId2())) {
                isApproveAuth = true;
            }
            break;
        case MCodeConst.A001_Y03:
            if (approveUserId.equals(vTReport.getApproveUserId3())) {
                isApproveAuth = true;
            }
            break;
        }

        if (!isApproveAuth) {
            // 「承認権限がありません」
            throw new BusinessException(MessageEnum.error010);
        }
    }

    /**
     * 月報テーブル更新処理(承認)
     * @param vTReport
     */
    private void updateReportApprove(VTReport vTReport) {

        TReport entity = new TReport();

        /*
         * 主キー
         */
        entity.setApplyUserId(vTReport.getApplyUserId());
        entity.setTargetYm(vTReport.getTargetYm());
        entity.setVersion(vTReport.getVersion()); // 排他制御用バージョン

        // TODO 登録画面と判断処理が同じ・・・共通化すべき？
        /*
         * 更新項目
         */
        // 承認状況（現在の承認状況と承認者の有無で判断）
        String newStatus = null;
        switch (vTReport.getStatus()) {
        case MCodeConst.A001_Y01:
            newStatus = MCodeConst.A001_Y02;
            if (StringUtils.isEmpty(vTReport.getApproveUserId2())) {
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
     * @param vTReport
     */
    private void updateReportApproveFlow(VTReport vTReport) {

        TReportApproveFlow entity = new TReportApproveFlow();

        // TODO 登録画面と判断処理が同じ・・・共通化すべき？
        /*
         * 主キー
         */
        entity.setApplyUserId(vTReport.getApplyUserId());
        entity.setTargetYm(vTReport.getTargetYm());
        // SEQ（現在の承認状況で判断）
        int approveSeq = -1;
        switch (vTReport.getStatus()) {
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
     * @param filePath
     * @param vTReport
     * @throws IOException
     */
    private void saveReportFile(Path filePath,
                                VTReport vTReport) throws IOException {
        // 月報ファイル保存処理
        sharedReportFileService.saveReportFile(filePath, vTReport.getApplyUserId(), vTReport.getTargetYm());
    }

}
