package wz.test.jdk;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wangz
 * Created by wangz on 17-11-14.
 */
public class Test {
    public static void main(String[] args) throws IOException {

    }


    public static class Data implements Comparable<Data> {
        int num;

        public Data(int num) {
            this.num = num;
        }

        public int getNum() {
            return this.num;
        }

        @Override
        public int compareTo(Data o) {
            if (this.getNum() > o.getNum()) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public static String getText() {
        return "{\n" +
                "    \"cat\": \"all\",\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"word\": \"陈学冬恋情曝光\",\n" +
                "            \"num\": \"2778621\",\n" +
                "            \"flag\": \"4\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"word\": \"陈乔恩酒驾\",\n" +
                "            \"num\": \"1698324\",\n" +
                "            \"flag\": \"1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"word\": \"她开得蛮好 你瞎指挥\",\n" +
                "            \"num\": \"681588\",\n" +
                "            \"flag\": \"1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"word\": \"太阳闵孝琳结婚日期\",\n" +
                "            \"num\": \"558446\",\n" +
                "            \"flag\": \"1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"word\": \"洪涛哭了\",\n" +
                "            \"num\": \"551254\",\n" +
                "            \"flag\": \"2\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

}

