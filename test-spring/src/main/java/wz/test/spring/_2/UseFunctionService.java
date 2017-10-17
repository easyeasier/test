package wz.test.spring._2;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wangz on 17-10-16.
 */
public class UseFunctionService {
    @Getter
    @Setter
    FunctionService functionService;

    public String sayHello(String name) {
        return functionService.sayHello(name);
    }
}
