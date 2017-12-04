package wz.test.jdk.compile;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wangz on 17-11-24.
 */
public class InnerClassTest {
    String a;
    public static void main(String[] args) {
        secondClassTest();
    }

    public static void secondClassTest(){
        Second second = new Second();
        System.out.println(second.getA());
    }


}

/**
 * 一个java文件中可以有多个类(非内部类)
 * 但只有有一个public修饰的,且和文件名同名;其他类的范围都是包级可见的
 * 在编译的时候,只能根据public找到编译入口,如果有多个public就会出混乱
 */
@Getter
@Setter
class Second{
    String a = "abc";
}
