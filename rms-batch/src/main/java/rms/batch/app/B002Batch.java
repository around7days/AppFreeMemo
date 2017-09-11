package rms.batch.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rms.common.abstracts.AbstractBatch;
import rms.common.consts.MessageEnum;
import rms.common.exception.BusinessException;
import rms.domain.app.batch.reportmail.ReportMailService;

/**
 * 月報メール配信バッチ<br>
 * @author
 */
@Component
public class B002Batch extends AbstractBatch {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(B002Batch.class);

    /** バッチID */
    public static final String BATCH_ID = "B002";

    /** 月報メール配信バッチサービス */
    @Autowired
    private ReportMailService service;

    /**
     * 月報メール配信バッチ<br>
     * @param args
     * @throws Exception
     */
    @Override
    public void execute(List<String> args) throws Exception {
        logger.info("{}:月報メール配信バッチ開始", BATCH_ID);

        /*
         * パラメータ取得
         */
        if (args.size() != 0) {
            // 「バッチIDに対するパラメータ数が不正です」
            throw new BusinessException(MessageEnum.error015);
        }

        /*
         * 実行
         */
        // 承認完了メール配信
        service.sendmailApproved();

        logger.info("{}:月報メール配信バッチ終了", BATCH_ID);
    }

    @Override
    protected String getBatchId() {
        return BATCH_ID;
    }
}
