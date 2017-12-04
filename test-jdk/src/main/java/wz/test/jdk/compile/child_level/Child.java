package wz.test.jdk.compile.child_level;

import wz.test.jdk.compile.parent.Parent;

/**
 * Created by wangz on 17-11-24.
 */

/**
 * 可见 protected字段是可见的, 默认的不可见
 */
public class Child extends Parent{
    public static void main(String[] args) {
        System.out.println(new Child().protectedField);
    }
}
