package wz.acm;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/**
 * Created by wangz on 17-3-7.
 */
public class Test {

    public static void main(String[] args) {
        Map<String, Object> map1 = Maps.newHashMap();
        map1.put("_id", "123");
        List<Map<String, Object>> list = Lists.newArrayList(map1);
        Map<String, Object> map = Maps.newHashMap();
        map.putAll(list.stream().collect(Collectors.toMap(
                d -> {
                    return (String) d.get("_id");
                }, d -> {
                    return d.get("name");
                })
        ));
    }

    public static class Demo implements Comparable<Demo> {
        @Getter
        @Setter
        String str;

        public Demo(String str) {
            this.str = str;
        }

        @Override
        public int compareTo(Demo o) {
            return o.getStr().compareTo(this.getStr());
        }
    }


}

