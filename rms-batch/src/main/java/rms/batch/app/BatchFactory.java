package rms.batch.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rms.common.abstracts.AbstractBatch;

/**
 * バッチFactory<br>
 * @author
 */
@Component
public class BatchFactory {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(BatchFactory.class);

    /** 月報初期データ登録バッチ */
    @Autowired
    private B001Batch b001;

    /**
     * バッチクラス生成<br>
     * @param batchId
     * @return
     * @throws Exception
     */
    public AbstractBatch create(String batchId) throws Exception {
        AbstractBatch xxxBatch = null;

        switch (batchId) {
        case B001Batch.BATCH_ID:
            xxxBatch = b001;
            break;
        }

        return xxxBatch;
    }
}
