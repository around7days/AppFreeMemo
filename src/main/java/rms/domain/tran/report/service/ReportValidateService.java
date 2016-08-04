package rms.domain.tran.report.service;

import java.util.Locale;

import rms.com.base.BusinessException;
import rms.domain.com.repository.TReportDao;
import rms.domain.tran.report.repository.ReportSelectDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 月報情報取得サービス
 * @author
 */
@Service
public class ReportValidateService extends rms.domain.com.abstracts.AbstractService {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ReportValidateService.class);

    /** 月報管理テーブルDao */
    @Autowired
    TReportDao tReportDao;

    /** 月報情報取得Dao */
    @Autowired
    ReportSelectDao reportSelectDao;

    /**
     * 月報の重複チェック<br>
     * 重複している場合はBusinessExceptionを発生
     * @param applyUserId
     * @param targetYm
     * @throws BusinessException
     */
    public void validateUniquReport(String applyUserId,
                                    Integer targetYm) throws BusinessException {
        if (tReportDao.selectById(applyUserId, targetYm) != null) {
            // 「対象年月の月報は既に登録されています」
            throw new BusinessException(message.getMessage("error.003", null, Locale.getDefault()));
        }
    }

}
