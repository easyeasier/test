package wz.test.jdk.io;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by wangz on 17-12-1.
 */
public class FileTest {
    public static void main(String[] args) {
        int codeLines = 0;
        int annotationLines = 0;

        List<File> files = FileUtil.getChildrenFile("/home/fs/Desktop/1");
        System.out.println("获取文件完毕.共" + files.size() + " 个源码文件");
        TaskExecutor taskExecutor = new TaskExecutor();
        List<Future<Integer[]>> results = Lists.newArrayList();
        files.forEach(file -> {
            CountTask countTask = new CountTask(file);
            results.add(taskExecutor.submit(countTask));
        });

        System.out.println("提交任务完毕.共" + results.size() + " 个任务再执行");
        try {
            Thread.sleep(10000);

            for (Future<Integer[]> future : results) {
                Integer[] thisLines = future.get();
                codeLines += thisLines[0];
                annotationLines += thisLines[1];
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        System.out.println(codeLines + ":" + annotationLines);
    }

    static class TaskExecutor<T> {
        private static ExecutorService executorService;

        static {
            ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("Thread-%d").setDaemon(true).build();
            executorService = new ThreadPoolExecutor(100, 100, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50),
                    threadFactory);
        }

        public Future<T> submit(Callable<T> callable) {
            return executorService.submit(callable);
        }
    }

    static class CountTask implements Callable<Integer[]> {

        private File file;
        private int codeLines;
        private int annotationLines;

        public CountTask(File file) {
            this.file = file;
        }

        @Override
        public Integer[] call() throws Exception {
            Reader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String tempLine;
            while ((tempLine = br.readLine()) != null) {
                if (isCode(tempLine)) {
                    codeLines++;
                } else {
                    annotationLines++;
                }
            }

            Integer[] ret = new Integer[2];
            ret[0] = codeLines;
            ret[1] = annotationLines;
            return ret;
        }

        public boolean isCode(String line) {
            String trimLine = line.trim();

            if (trimLine.startsWith("/*") || trimLine.startsWith("*") || trimLine.startsWith("//")) {
                return false;

            }

            return true;
        }
    }

    static class FileUtil {

        /**
         * 获取.java文件类型
         *
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
