package rms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import rms.batch.app.BatchFacade;
import rms.common.base.BusinessException;

/**
 * SpringBatchApplicationクラス*
 * @author
 */
@SpringBootApplication
public class SpringBatchApplication {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(SpringBatchApplication.class);

    /** バッチ処理Facade */
    @Autowired
    private BatchFacade batchFacade;

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
            logger.debug("--------------------- Batch Application Start --------------------- ");
            SpringBatchApplication app = ctx.getBean(SpringBatchApplication.class);
            app.run(args);
        } catch (BusinessException be) {
            logger.error(be.toString());
            ret = 1;
        } catch (Exception e) {
            logger.error("batch error", e);
            ret = 1;
        }
        logger.debug("--------------------- Batch Application End --------------------- ");
        System.exit(ret);
    }

    private void run(String[] args) throws Exception {
        batchFacade.call(args);
    }

}
