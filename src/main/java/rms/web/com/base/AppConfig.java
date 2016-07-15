package rms.web.com.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;

@Configuration
public class AppConfig {

    @Bean
    MessageSourceAccessor messageSourceAccessorResolver() {
        return new MessageSourceAccessorResolver().resolve();
    }
}