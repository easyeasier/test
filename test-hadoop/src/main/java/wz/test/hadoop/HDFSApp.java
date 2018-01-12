package wz.test.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class HDFSApp {
    static final String HDFS_PATH = "hdfs://localhost:8020";
    static FileSystem fs;
    static Configuration config;

    static {
        System.out.println("init..");
        try {
            config = new Configuration();
            fs = FileSystem.get(new URI(HDFS_PATH), config);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建目录
     */
    public static void mkdir() throws IOException {
        String path = "/hdfsapi/test";
        boolean ret = fs.mkdirs(new Path("/hdfsapi/test"));
        if (ret) {
            System.out.println("创建目录成功。。");
        } else {
            System.out.println("创建目录失败。。。");
        }
    }

    /**
     * 创建文件,并写入数据
     */
    public static void create() throws IOException {
        String path = "/hdfsapi/test/a.txt";
        FSDataOutputStream fso = fs.create(new Path(path));
        fso.write("hello hdfs".getBytes());
        fso.flush();
        fso.close();
        System.out.println("写入成功。。");
    }

    public static void read() throws IOException {
        String path = "/hdfsapi/test/a.txt";
        FSDataInputStream in = fs.open(new Path(path));
        IOUtils.copyBytes(in, System.out, 1024);

        in.close();
    }

    public static void clear() {
        config = null;
        fs = null;
    }

    public static void main(String[] args) throws IOException {
        read();
        clear();
    }


}
