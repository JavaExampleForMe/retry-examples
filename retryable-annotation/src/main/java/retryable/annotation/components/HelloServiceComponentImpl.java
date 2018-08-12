package retryable.annotation.components;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Component;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import retryable.annotation.MyException;

import java.sql.SQLException;

@Service
public class HelloServiceComponentImpl implements HelloService {

    @Retryable(value = {MyException.class},
            maxAttemptsExpression = "${demo.maxAttempts}",
            backoff = @Backoff(delayExpression = "${demo.retry.delay}")
    )
    public void sayHello() {
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("------------ retry  -------------");
        System.out.println("-------------------------------------------------------------------------------------------");
        throw new MyException("this is exception");
    }

    @Recover
    // parameters between recover method needs to be aligne with sayHello  !!
    // https://stackoverflow.com/questions/46730928/spring-retry-recover-passing-parameters
    public void recover(MyException e){
        System.out.println("------------ in recover only once at the end  -------------");
    }

}
