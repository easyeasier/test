package wz.test.jdk.classload;

import sun.java2d.loops.GraphicsPrimitive;

/**
 * Created by wangz on 17-11-13.
 */
public class AsynInitialization {
    static class Demo{
        static String value = "demo_value";
        static{
            System.out.println("Demo init start...");
            System.out.println(Thread.currentThread());
            //不加if,提示错误
            if(true){
                while(true);
            }
            System.out.println("Demo init end...");

        }
    }

    public static void main(String[] args) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(Demo.value);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
    }
}
