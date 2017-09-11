package rms.domain.app.batch.reportmail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 月報メール配信バッチサービス
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
// TODO 未実装
public class ReportMailServiceImpl implements ReportMailService {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ReportMailServiceImpl.class);

    // /** application.properties */
    // @Autowired
    // private ProjectProperties properties;
    //
    // /** MUserApproveFlowDao */
    // @Autowired
    // private MUserApproveFlowDao mUserApproveFlowDao;
    //
    // /** TReportDao */
    // @Autowired
    // private TReportDao tReportDao;
    //
    // /** TReportApproveFlowDao */
    // @Autowired
    // private TReportApproveFlowDao tReportApproveFlowDao;
    //
    // /** 月報初期データ登録Dao */
    // @Autowired
    // private ReportMailDao dao;

    @Override
    public void sendmailApproved() throws Exception {

    }

}
