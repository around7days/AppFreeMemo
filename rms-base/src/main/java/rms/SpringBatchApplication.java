package rms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import rms.batch.app.BatchMain;
import rms.common.exception.BusinessException;

/**
 * SpringBatchApplicationクラス*
 * @author
 */
@SpringBootApplication
public class SpringBatchApplication {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(SpringBatchApplication.class);

    /** バッチ処理Main */
    @Autowired
    private BatchMain batchMain;

    /**
     * メイン起動
     * @param args
     */
    public static void main(String[] args) {
        // SpringApplication起動クラスの生成
        SpringApplication application = new SpringApplication(SpringBatchApplication.class);
        application.setWebEnvironment(false); // 内臓tomcatの起動を抑制

        // 処理開始
        int ret = 0;
        try (ConfigurableApplicationContext ctx = application.run(args)) {
            logger.info("--------------------- Batch Application Start --------------------- ");
            SpringBatchApplication app = ctx.getBean(SpringBatchApplication.class);
            app.run(args);
        } catch (BusinessException be) {
            logger.error(be.toString());
            ret = 1;
        } catch (Exception e) {
            logger.error("batch error", e);
            ret = 1;
        }
        logger.info("--------------------- Batch Application End --------------------- ");
        System.exit(ret);
    }

    /**
     * 実行<br>
     * コマンドライン引数はDI経由で取得する為、batchMainに渡さない。
     * @param args
     * @throws Exception
     */
    private void run(String[] args) throws Exception {
        batchMain.call();
    }

}
