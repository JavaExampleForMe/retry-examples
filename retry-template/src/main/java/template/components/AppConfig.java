package template.components;

import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.policy.NeverRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import template.DefaultListenerSupport;
import template.MyException;

@Configuration
public class AppConfig {
    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(1000);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        // for specific exception
        MyExceptionRetryPolicy retryPolicy = new MyExceptionRetryPolicy();
        // for all Exceptions
//        final SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
//        retryPolicy.setMaxAttempts(2);
        retryTemplate.setRetryPolicy(retryPolicy);

        // The refistration can be done in the listener
        //retryTemplate.registerListener(new DefaultListenerSupport());
        return retryTemplate;
    }

    public class MyExceptionRetryPolicy extends
            ExceptionClassifierRetryPolicy
    {
        public MyExceptionRetryPolicy()
        {
            final SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
            simpleRetryPolicy.setMaxAttempts(2);

            this.setExceptionClassifier( new Classifier<Throwable, RetryPolicy>()
            {
                @Override
                public RetryPolicy classify( Throwable classifiable )
                {
                    // Retry only when MyException was thrown
                    if ( classifiable instanceof MyException)
                    {
                        return simpleRetryPolicy;
                    }

                    // Do not retry for other exceptions
                    return new NeverRetryPolicy();
                }
            } );
        }
    }
}