package rms.common.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@Configuration
public class AppConfig {

    /**
     * Thymeleaf java8Time
     * @return
     */
    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    // TODO 設定不要。なぜに・・・
    // /**
    // * MessageSourceの拡張
    // * @return
    // */
    // @Bean
    // @Primary
    // public MessageSourceImpl messageSourceImpl() {
    // return new MessageSourceImpl();
    // }


}