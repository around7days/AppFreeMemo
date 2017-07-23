package rms.batch.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rms.common.abstracts.AbstractBatch;
import rms.common.base.BusinessException;
import rms.common.base.ProjectProperties;
import rms.common.consts.MessageEnum;
import rms.common.utils.RmsUtils;
import rms.domain.app.batch.reportinitregist.ReportInitRegistService;

/**
 * 月報初期データ登録バッチ<br>
 * @author
 */
@Component
public class B001Batch extends AbstractBatch {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(B001Batch.class);

    /** バッチID */
    public static final String BATCH_ID = "B001";

    /** application.properties */
    @Autowired
    private ProjectProperties properties;

    /** 月報初期データ登録バッチサービス */
    @Autowired
    private ReportInitRegistService service;

    /**
     * 月報初期データ登録バッチ<br>
     * 対象年月が未指定の場合はシステム日付から取得
     * @param args 0:対象年月
     * @throws Exception
     */
    @Override
    public void execute(List<String> args) throws Exception {
        logger.info("{}:月報初期データ登録バッチ開始", BATCH_ID);

        // 対象年月
        Integer targetYm;

        /*
         * パラメータ取得
         */
        if (args.size() == 0) {
            // システム日付から取得
            LocalDate sysdate = properties.getSysdate();
            targetYm = Integer.valueOf(sysdate.format(DateTimeFormatter.ofPattern("yyyyMM")));

        } else if (args.size() == 1) {
            // パラメータから取得
            String strTargetYm = args.get(0);
            if (!RmsUtils.isTargetYmCheck(strTargetYm)) {
                logger.error("対象年月(yyyymm)の指定が不正です -> {}", strTargetYm);
                throw new BusinessException(MessageEnum.error016);
            }
            targetYm = Integer.valueOf(strTargetYm);

        } else {
            // 「バッチIDに対するパラメータ数が不正です」
            throw new BusinessException(MessageEnum.error015);
        }

        /*
         * 実行
         */
        service.regist(targetYm);

        logger.info("{}:月報初期データ登録バッチ終了", BATCH_ID);
    }

    @Override
    protected String getBatchId() {
        return BATCH_ID;
    }
}
