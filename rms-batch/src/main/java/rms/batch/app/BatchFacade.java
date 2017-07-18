package rms.batch.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
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

    /** 起動引数 */
    @Autowired
    private ApplicationArguments arguments;

    /** 月報初期データ登録バッチサービス */
    @Autowired
    private ReportInitRegistService service;

    /** バッチID：月報初期データ登録バッチ */
    private static final String BATCH_ID_B001 = "B001";

    /**
     * サービス呼び出し<br>
     * @throws Exception
     */
    public void call() throws Exception {
        /*
         * Springオプションを除いたコマンドライン引数を取得
         * args[0] :"batch"固定
         * args[1] :バッチID
         * args[2]～:パラメータ
         */
        List<String> args = new ArrayList<>();
        args.addAll(arguments.getNonOptionArgs());
        if (args.size() >= 2) {
            String batchId = args.get(1);
            args.remove(0); // "batch"固定
            args.remove(0); // バッチIDを除去
            call(batchId, args);
        } else {
            // エラー終了
            call(null, args);
            return;
        }
    }

    /**
     * サービス呼び出し<br>
     * @param batchId
     * @param args 各処理のパラメータ
     * @throws Exception
     */
    public void call(String batchId,
                     List<String> args) throws Exception {
        logger.info("サービス呼び出し開始");
        logger.info("バッチID -> {}", batchId);
        if (!args.isEmpty()) {
            args.forEach(val -> logger.info("パラメータ -> {}", val));
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
             * @param args[0]:対象年月
             */
            Integer targetYm;
            if (args.size() != 0) {
                targetYm = Integer.valueOf(args.get(0));
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
