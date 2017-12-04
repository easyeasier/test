package wz.test.jdk.sysn;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by wangz on 17-11-26.
 */
public class InterruptTest extends Thread {
    @Getter
    @Setter
    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            System.out.println("running ..." + new Date());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { //这个异常就是interrupt发出的 . interrupt后,将线程至一个interupt标识,当sleep,wait,join,
                // yield的时候都会抛出这个异常
                e.printStackTrace();
                System.out.println(this.getState());
                break;  //如果不break,会继续执行,看来,interrupt只是一次中断,后会恢复;stop则真的停了
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptTest thread = new InterruptTest();
        thread.start();

        Thread.sleep(5000);
        thread.interrupt(); //1
//        thread.stop(); //2
        System.out.println("main_out " + thread.getState());
//        thread.setFlag(false); //3 正确的安全方式 ; 其他可能会导致资源没有释放
    }

}
