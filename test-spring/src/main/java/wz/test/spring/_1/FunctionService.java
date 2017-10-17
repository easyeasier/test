package wz.test.spring._1;


import org.springframework.stereotype.Service;

/**
 * Created by wangz on 17-10-16.
 */
@Service
public class FunctionService {
    public String sayHello(String name) {
        return "Hello ," + name + "!";
    }
}
