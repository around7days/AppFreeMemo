package rms.common.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@Configuration
public class AppConfig {

    //    @Bean
    //    MessageSourceAccessor messageSourceAccessorResolver() {
    //        return new MessageSourceAccessorResolver().resolve();
    //    }

    //    @Bean
    //    UserInfo userInfoResolver() {
    //        return new UserInfoResolverImpl().resolve();
    //    }

    /**
     * Thymeleaf java8Time
     * @return
     */
    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }
}