package wz.test.jdk;

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
        int num = 2;
        Map<String, Integer> map = Maps.newHashMap();
        map.put("a", 2);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);
        map.put("f", 5);

        List<String> ls = map.entrySet().stream().sorted((e1, e2) -> e1.getValue() > e2.getValue() ? -1 : 1)
                .limit(4).map(entry -> entry.getKey()).collect(Collectors.toList());
        List<String> ls2 = map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
                .limit(4).map(entry -> entry.getKey()).collect(Collectors.toList());

        System.out.println(ls);
        System.out.println(ls2);
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

