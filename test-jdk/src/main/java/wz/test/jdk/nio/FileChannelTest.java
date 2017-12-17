package wz.test.jdk.nio;

import com.google.common.collect.Lists;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * Created by wangz on 17-12-13.
 */
public class FileChannelTest {
    public static void main(String[] args) throws FileNotFoundException {
//        test1();
        RandomAccessFile randomAccessFile = new RandomAccessFile("/home/fs/Desktop/curlPG.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        System.out.println(fileChannel instanceof AutoCloseable);
    }

//    public static void test1() {
//        try (RandomAccessFile randomAccessFile = new RandomAccessFile("/home/fs/Desktop/curlPG.txt", "rw");
//             FileChannel fileChannel = randomAccessFile.getChannel()) {
//
//            CharBuffer byteBuffer = CharBuffer.allocate();
//
//            int bytesRead = fileChannel.read(byteBuffer);
//            while (bytesRead != -1) {
//                byteBuffer.flip();
//                byte[] bytes = new byte[48];
//
//                byteBuffer.get(bytes, 0, bytesRead);
//
//                allBytes.addAll();
//                byteBuffer.clear();
//                bytesRead = fileChannel.read(byteBuffer);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
