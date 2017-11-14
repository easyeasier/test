package wz.test.jdk.classload;

/**
 * Created by wangz on 17-11-13.
 * 类加载 初始化测试
 */

/**
 * 初始化有且只有4种情况才会触发:
 * 1.调用new ,getStatic, putStatic invokeStatic 时,(对静态变量操作,不包括final static,这个是在编译时放在常量池中的)
 * 2.调用reflect包中的反射的时候
 * 3.初始化一个类,发现其父类还没有初始化的情况,初始化父类
 * 4.虚拟机启动时,用户需要指定一个要执行的主函数类,这个类需要先初始化
 */
public class InitializationTest {
    static {
        System.out.println("Main class init!!");
    }


    public static void main(String[] args) {
//        System.out.println(Child.superValue);  //output = "Superclass init! supper_value"  直接定义静态的变量父类才需要初始化
//        System.out.println(Child.superFinalStaticValue);  //output= "super_finalStaticValue"
        System.out.println(Child.subValue);  //output= "SuperClass init!!  SubClass init!! sub_value"
//
//        ArrayNotInitialization[] array = new ArrayNotInitialization[10]; //output="";  对象调用不会初始化,每个元素都是ｎｕｌｌ

//        Child child = new Child();

    }

    /**
     * 被动引用 : 指的就是不会触发初始化的引用,没有被实际使用
     * 被动引用 1 :子类调用父类的static,子类不会初始化
     * 被动引用２　: final static 常量在编译时存放到常量池，不会触发初始化
     */
    public static class Parent {
        //        static ArrayNotInitialization arrayNotInitialization = new ArrayNotInitialization(); //位置不同,初始化顺序不同
        static {
            System.out.println("SuperClass init!!");
        }

        static ArrayNotInitialization arrayNotInitialization = new ArrayNotInitialization();
//        ArrayNotInitialization arrayNotInitialization = new ArrayNotInitialization();  //非静态变量,Parent初始化时不会被初始化,
// Parent实例化时才会被初始化

        static String superValue = "supper_value";   //静态变量会触发初始化
        final static String superFinalStaticValue = "super_finalStaticValue"; //常量不触发初始化
    }

    public static class Child extends Parent {
        static {
            System.out.println("SubClass init!!");
        }

        static String subValue = "sub_value";
//        ArrayNotInitialization arrayNotInitialization = new ArrayNotInitialization();
    }

    /**
     * 被动初始化三:对象的数组引用,不会触发对象初始化
     */
    static class ArrayNotInitialization {
        static {
            System.out.println("ArrayNotInitialization init!!");
        }
    }
}
