package retryable.annotation;

import retryable.annotation.components.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;


@EnableAutoConfiguration
@ComponentScan(basePackages = {"retryable.annotation.components"})
@Slf4j
@EnableRetry
public class Main {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        context.getBean(HelloService.class).sayHello();
        log.error("Something else is wrong here");
        log.error(">> Application Started");
    }
}
