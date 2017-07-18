package rms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringWebApplicationクラス
 * @author
 */
@SpringBootApplication
public class SpringWebApplication {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SpringWebApplication.class);

    /**
     * メイン起動
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringWebApplication.class, args);
    }

}
