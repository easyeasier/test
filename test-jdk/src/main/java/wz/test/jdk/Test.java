package wz.test.jdk;

import com.google.common.collect.Sets;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author wangz
 * Created by wangz on 17-11-14.
 */
public class Test {
    public static void main(String[] args) {
        String path = "/configs/test";
        System.out.println(path.lastIndexOf("/"));
        System.out.println(path.substring(path.lastIndexOf("/")+1));
    }

}

