package rms.domain.tran.report.service;

import rms.com.consts.MCodeConst;
import rms.domain.com.entity.TReport;
import rms.domain.com.repository.TReportDao;
import rms.domain.tran.report.entity.ReportApprovalEntity;
import rms.domain.tran.report.repository.ReportDao;
import rms.web.base.UserInfo;
import rms.web.tran.report.approval.ReportApprovalForm;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 月報承認画面サービス
 * @author
 */
@Service
public class ReportApprovalService extends rms.domain.com.abstracts.AbstractService {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApprovalService.class);

    /** 月報管理テーブルDao */
    @Autowired
    TReportDao tReportDao;

    /** 月報情報Dao */
    @Autowired
    ReportDao reportDao;

    /**
     * 初期処理
     * @param form
     */
    public void init(ReportApprovalForm form) {
        ReportApprovalEntity entity = reportDao.selectReport(form);
        BeanUtils.copyProperties(entity, form);
    }

    /**
     * 承認処理
     * @param form
     * @param userInfo
     */
    public void approval(ReportApprovalForm form,
                         UserInfo userInfo) {
        /*
         * 承認用情報の生成
         */
        TReport entity = new TReport();
        entity.setApplicantId(form.getApplicantId());
        entity.setTargetYear(Integer.valueOf(form.getTargetYm()));
        entity.setStatus(MCodeConst.A001_100); // 承認済み
        logger.debug("承認情報 -> {}", entity);

        /*
         * 更新処理
         */
        tReportDao.update(entity);
    }

    /**
     * 否認処理
     * @param form
     * @param userInfo
     */
    public void denial(ReportApprovalForm form,
                       UserInfo userInfo) {
        /*
         * 承認用情報の生成
         */
        TReport entity = new TReport();
        entity.setApplicantId(form.getApplicantId());
        entity.setTargetYear(Integer.valueOf(form.getTargetYm()));
        entity.setStatus(MCodeConst.A001_N03); // 否認
        logger.debug("否認情報 -> {}", entity);

        /*
         * 更新処理
         */
        tReportDao.update(entity);
    }

}
