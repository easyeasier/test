package wz.test.jdk.classload;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wangz on 17-11-14.
 */
public class CustomClassLoader {
    public static void main(String[] args) throws ClassNotFoundException {
        Class clz = badClassloader().loadClass("wz.test.jdk.classload.CustomClassLoader");
        try {
            Object obj = clz.newInstance();
            System.out.println(obj.getClass());  //class wz.test.jdk.classload.CustomClassLoader
            System.out.println(obj instanceof  CustomClassLoader);  //false
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 直接覆盖ClassLoader的classload,破坏了双亲委派模型
     * @return
     */
    static ClassLoader badClassloader() {
        return new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";

                    InputStream is = getClass().getResourceAsStream(fileName);//内调classLoader.getResourceAsStream,加载类文件

                    if (is == null) {
                        return super.loadClass(name);
                    }


                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);

                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
    }

}
