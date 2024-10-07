package retryable.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RetryListenerImpl implements RetryListener {
    @Autowired
    ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        applicationContext.getBean(RetryTemplate.class)
                .registerListener(new RetryListenerImpl());
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext context,
                                               RetryCallback<T, E> callback, Throwable throwable) {
        System.out.println("onClose");
    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext context,
                                                 RetryCallback<T, E> callback, Throwable throwable) {
        System.out.println("onError count=" + context.toString());
    }

    @Override
    public <T, E extends Throwable> boolean open(RetryContext context,
                                                 RetryCallback<T, E> callback) {
        System.out.println("onOpen");
        return true;
    }
}