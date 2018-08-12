package retryable.annotation.components;

import retryable.annotation.MyException;

public interface HelloService {
	
	void sayHello() throws MyException;
}
