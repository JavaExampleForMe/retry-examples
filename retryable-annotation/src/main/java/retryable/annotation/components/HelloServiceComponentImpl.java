package retryable.annotation.components;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Component;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import retryable.annotation.MyException;

import java.sql.SQLException;
import java.util.function.Function;

@Service
public class HelloServiceComponentImpl implements HelloService {


//    public static void main(String[] args) {
//        Function<Integer, Integer> f1 = o -> {
//            System.out.println("------------ in f1  -------------");
//            if (o>3)
//                return o/0;
//            else
//                return o++;
//        };
//        final Function<Integer, Integer> f2 = f(f1);
//        f2.apply(3);
//        f2.apply(4);
//
//    }
//
//    private static Function<Integer, Integer> f (Function<Integer, Integer> f1){
//        final boolean[] failed = {false};
//        return number -> {
//           try{
//               System.out.println("------------ in f2  -------------");
//               if (failed[0])
//                   return null;
//               return f1.apply(number);
//           }
//           catch(Exception e) {
//               failed[0] = true;
//               return null;
//           }
//       };
//    }
//

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
