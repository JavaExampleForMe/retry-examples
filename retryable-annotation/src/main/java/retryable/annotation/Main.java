package retryable.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.retry.support.RetryTemplate;
import retryable.annotation.components.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;


@EnableAutoConfiguration
@ComponentScan(basePackages = {"retryable.annotation.components"})
@EnableRetry
public class Main {
    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        context.getBean(HelloService.class).sayHello();
    }
}
