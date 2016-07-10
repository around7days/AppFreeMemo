package rms.web.tran.report.approval;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rms.com.doma.dao.TReportDao;
import rms.web.com.auth.UserInfo;

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
    }

    /**
     * 否認処理
     * @param form
     * @param userInfo
     */
    public void denial(ReportApprovalForm form,
                       UserInfo userInfo) {
    }

}
