package rms.domain.app.shared.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 月報関連共通サービス
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SharedReportService extends rms.common.abstracts.AbstractService {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SharedReportService.class);

}
