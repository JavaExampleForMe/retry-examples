package retryable.annotation.components;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;
import retryable.annotation.RetryListenerImpl;

@Configuration
public class RetryConfig {
    @Bean
    public RetryListenerImpl getRetryListener()  {
        return new RetryListenerImpl();
    }

    @Bean
    public RetryTemplate dbRetry() {
        return new RetryTemplate();
    }
}
