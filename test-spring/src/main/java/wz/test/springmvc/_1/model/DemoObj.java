package wz.test.springmvc._1.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wangz on 17-10-17.
 */
public class DemoObj {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;

    @Override
    public String toString() {
        return "Demoobj{id : " + id + ", name : " + name + "}";
    }
}
