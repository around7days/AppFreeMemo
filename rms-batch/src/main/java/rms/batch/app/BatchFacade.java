package rms.batch.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rms.common.base.BusinessException;
import rms.common.consts.MessageEnum;
import rms.domain.app.batch.reportinitregist.ReportInitRegistService;

/**
 * バッチ処理Facadeクラス<br>
 * @author
 */
@Component
public class BatchFacade {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(BatchFacade.class);

    /** 月報初期データ登録バッチサービス */
    @Autowired
    private ReportInitRegistService service;

    /** バッチID：月報初期データ登録バッチ */
    private static final String BATCH_ID_B001 = "B001";

    /**
     * サービス呼び出し<br>
     * @param args
     *            args[1]:バッチID
     *            args[2]～:各処理のパラメータ
     * @throws Exception
     */
    public void call(String[] args) throws Exception {
        if (args == null || args.length == 1) {
            call(null, null);
        } else {
            String batchId = args[1];
            call(batchId, ArrayUtils.removeAll(args, 0, 1));
        }
    }

    /**
     * サービス呼び出し<br>
     * @param batchId
     * @param args 各処理のパラメータ
     * @throws Exception
     */
    public void call(String batchId,
                     String[] args) throws Exception {
        logger.info("サービス呼び出し開始");
        logger.info("バッチID -> {}", batchId);
        if (args != null && args.length > 0) {
            logger.info("パラメータ -> {}", ToStringBuilder.reflectionToString(args, ToStringStyle.SIMPLE_STYLE));
        }
        // パラメータチェック
        if (batchId == null || batchId.isEmpty()) {
            // 「パラメータにバッチIDが設定されていません」
            throw new BusinessException(MessageEnum.error013);
        }

        /*
         * サービス呼び出し
         */
        switch (batchId) {
        case BATCH_ID_B001:
            /*
             * 月報初期データ登録バッチ<br>
             * 対象年月が未指定の場合はシステム日付から取得
             * @param args[1]:対象年月
             */
            Integer targetYm;
            if (args.length != 0) {
                targetYm = Integer.valueOf(args[0]);
            } else {
                LocalDate sysdate = LocalDate.now();
                targetYm = Integer.valueOf(sysdate.format(DateTimeFormatter.ofPattern("yyyyMM")));
            }
            // 実行
            service.regist(targetYm);

            break;
        default:
            // 「バッチIDが不正です」
            throw new BusinessException(MessageEnum.error014);
        }

        logger.info("サービス呼び出し終了");
    }
}
