package wz.test.jdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

/**
 * @author wangz
 * Created by wangz on 17-11-14.
 */
public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        Child child = new Child();
        System.out.println(child);
        System.out.println(child);
    }

    public static class Child extends Test{}
}

