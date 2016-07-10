package rms.web.tran.report.approval;

import rms.com.consts.MCodeConst;
import rms.com.doma.dao.TReportDao;
import rms.com.doma.entity.TReport;
import rms.web.com.auth.UserInfo;

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
public class ReportApprovalService extends rms.com.abstracts.AbstractService {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApprovalService.class);

    /** 月報管理テーブルDao */
    @Autowired
    TReportDao tReportDao;

    /** 月報承認画面Dao */
    @Autowired
    ReportApprovalDao reportApprovalDao;

    /**
     * 初期処理
     * @param form
     */
    public void init(ReportApprovalForm form) {
        ReportApprovalEntity entity = reportApprovalDao.select(form);
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
        entity.setTargetYm(Integer.valueOf(form.getTargetYm()));
        entity.setStatus(MCodeConst.A001_100); // 承認済み
        logger.debug("承認情報 -> {}", entity.toString());

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
        entity.setTargetYm(Integer.valueOf(form.getTargetYm()));
        entity.setStatus(MCodeConst.A001_N03); // 否認
        logger.debug("否認情報 -> {}", entity.toString());

        /*
         * 更新処理
         */
        tReportDao.update(entity);
    }

}
