package retryable.annotation.components;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;
import retryable.annotation.RetryListener;

@Configuration
public class RetryConfig {
    @Bean
    public RetryListener getRetryListener()  {
        return new RetryListener();
    }

    @Bean
    public RetryTemplate dbRetry() {
        return new RetryTemplate();
    }
}
