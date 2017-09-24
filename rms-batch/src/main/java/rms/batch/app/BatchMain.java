package rms.batch.app;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import rms.common.abstracts.AbstractBatch;
import rms.common.consts.MessageEnum;
import rms.common.exception.BusinessException;

/**
 * バッチ処理Mainクラス<br>
 * @author
 */
@Component
public class BatchMain {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(BatchMain.class);

    /** 起動引数 */
    @Autowired
    private ApplicationArguments arguments;

    /** BatchFactory */
    @Autowired
    private BatchFactory factory;

    /**
     * 各バッチ処理呼び出し<br>
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
     * 各バッチ処理呼び出し<br>
     * @param batchId
     * @param args 各処理のパラメータ
     * @throws Exception
     */
    private void call(String batchId,
                      List<String> args) throws Exception {
        logger.info("バッチID -> {}", batchId);
        if (!args.isEmpty()) {
            args.forEach(val -> logger.info("パラメータ -> {}", val));
        }

        // バッチIDチェック
        if (batchId == null || batchId.isEmpty()) {
            // 「パラメータにバッチIDが設定されていません」
            throw new BusinessException(MessageEnum.error013);
        }

        // 各バッチクラスの生成
        AbstractBatch xxxBatch = factory.create(batchId);
        if (xxxBatch == null) {
            // 「バッチIDが不正です」
            throw new BusinessException(MessageEnum.error014);
        }

        // バッチ実行
        xxxBatch.execute(args);
    }
}
