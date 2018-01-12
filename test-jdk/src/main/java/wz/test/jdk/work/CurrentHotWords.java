package wz.test.jdk.work;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CurrentHotWords {
    private static CloseableHttpClient client;

    static {
        client = HttpClients.createDefault();
        System.out.println("=========== 创建httpclient===========");
    }

    public static void main(String[] args) throws IOException {
        parseCurrentHotWords();
    }

    public static void parseCurrentHotWords() throws IOException {

        //整理出当前50个热搜词
        Map<String, Object> map = JSON.parseObject(fetchHotWords(), Map.class);
        List<Map<String, String>> list = (List<Map<String, String>>) map.get("data");
        List<String> words = list.stream().map(item -> item.get("word")).collect(Collectors.toList());

        //按线上9台机器分组打印输出
        List<String>[] parts = new List[9];
        for (int i = 0; i < 9; i++) {
            parts[i] = new ArrayList<>();
        }
        words.forEach(item -> parts[(Math.abs(item.hashCode()) % 9)].add(item));
        int i = 0;
        for (List<String> l : parts) {
            System.out.println((i++) + ": " + l);
        }
    }

    public static String fetchHotWords() throws IOException {
        String url = "http://i.api.weibo.com/search/hot_word.json?source=2975945008&sid=v_message&count=50";
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String hotwordsEntity = EntityUtils.toString(entity);
            System.out.println("===== 抓取成功：" + new Date() + "  " + hotwordsEntity);
            return hotwordsEntity;
        } catch (IOException e) {
            System.out.println("===== 连接失败。。。。");
            e.printStackTrace();
            throw e;
        }
    }
}
