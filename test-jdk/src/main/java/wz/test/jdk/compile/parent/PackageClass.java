package wz.test.jdk.compile.parent;

/**
 * Created by wangz on 17-11-24.
 */
public class PackageClass {
    Parent parent = new Parent();

    /**
     * 可见, 默认和protected在本包中都是可见的
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new PackageClass().parent.defaultField);
        System.out.println(new PackageClass().parent.protectedField);
    }
}
