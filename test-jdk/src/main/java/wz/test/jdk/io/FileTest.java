package wz.test.jdk.io;

import com.google.common.collect.Lists;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Created by wangz on 17-12-1.
 */
public class FileTest {
    public static void maisn(String[] args) {
//        listFileProperties();
        System.out.println(FileUtil.getChildrenFile("/home/fs/Desktop/1/"));
    }



    static class FileUtil{

        /**
         * 获取.java文件类型
         * @param path
         * @return
         */
        public static List<File> getChildrenFile(String path) {
            List<File> ret = Lists.newArrayList();
            File file = new File(path);
            if (file.exists() && file.isFile() && path.endsWith(".java")) {
                ret.add(file);
            } else if (file.exists() && file.isDirectory()) {
                ret.addAll(getChildrenFile(file));
            }

            return ret;
        }

        private static List<File> getChildrenFile(File file) {
            File[] childrenFiles = file.listFiles();

            List<File> ret = Lists.newArrayList();

            if (childrenFiles == null) {
                return ret;
            }
            for (File c : childrenFiles) {
                if (c.isDirectory()) {
                    ret.addAll(getChildrenFile(c));
                } else if (c.isFile() && c.getPath().endsWith(".java")) {
                    ret.add(c);
                }
            }

            return ret;
        }

        /**
         * 查看文件信息
         */
        public static void listFileProperties() {
            String path = "/home/fs/Desktop/1/";  //在File中的path都是"/home/fs/Desktop/1"
            File file = new File(path);

            System.out.println(file.exists()); //true
            System.out.println(file.getName()); //1
            System.out.println(file.getParent()); //  /home/fs/Desktop
            System.out.println(file.canRead()); //  true
            System.out.println(file.exists()); //  true
            System.out.println(file.isDirectory()); //  true
            System.out.println(file.isFile()); //  false

            File[] childFiles = file.listFiles();
            for (File str : childFiles) {
                System.out.println(str);
            }
        }
    }




}
