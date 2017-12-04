package wz.test.jdk.compile.parent;

/**
 * Created by wangz on 17-11-24.
 */

/**
 * 课件范围测试,
 * 1. 测试本包中的 默认字段和protected字段可见范围
 * 2. 测试其他包中,子类Child对 Parent字段的可见程度
 *
 * 结论 : 默认的可见范围是最小的 : 只在本包中可见.
 * 2. protected的可见范围是 本包或继承子类中
 */
public class Parent {
    String defaultField = "default";
    protected String protectedField = "protected";
}
