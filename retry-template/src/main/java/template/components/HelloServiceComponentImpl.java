package template.components;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import template.MyException;

@Service
public class HelloServiceComponentImpl implements HelloService {

    @Autowired
    RetryTemplate retryTemplate;

    public void sayHello() {
        retryTemplate.execute(new RetryCallback<Void, RuntimeException>() {
            @Override
            public Void doWithRetry(RetryContext arg0) {
                sayHelloInner();
                return null;
            }
        });
    }


    private Integer sayHelloInner() {
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("------------ retry  sayHelloInner -------------");
        System.out.println("-------------------------------------------------------------------------------------------");
        throw new MyException("this is exception");

        // in RuntimeException retry doesn't happen
        //throw new RuntimeException();
    }

}
