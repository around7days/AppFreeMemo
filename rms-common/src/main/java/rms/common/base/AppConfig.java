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

    /**
     * MessageSourceの拡張
     * @return
     */
    @Bean
    public MessageSourceEnumAccessor messageSourceEnumAccessor() {
        return new MessageSourceEnumAccessor();
    }

    /**
     * URL生成補助ヘルパー
     * @return
     */
    @Bean
    public UrlCreateHelper urlCreateHelper() {
        return new UrlCreateHelper();
    }

    /**
     * ProjectProperties
     * @return
     */
    @Bean
    public ProjectProperties projectProperties() {
        return new ProjectProperties();
    }

}