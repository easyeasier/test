package wz.test.jdk.test;


import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wangz on 17-10-25.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        start();
    }

    public static void start() throws IOException {
        String filePath = Main.class.getResource("/").getPath() + "timeout.log";
        List<String> logs = FileUtils.readLines(new File(filePath));
        List<Model> models = transfer2Model(logs);
        Collections.sort(models, (o1, o2) -> {
            if(!o1.getTenantId().equals(o2.getTenantId())){
                return o1.getTenantId().compareTo(o2.getTenantId());
            }else{
                return o1.getTime().compareTo(o2.getTime());
            }
        });
        printFormat(models);
    }

    public static void printFormat(List<Model> models) {
        System.out.println(models.size());
        System.out.println("======");
        models.forEach(model -> {
            System.out.printf("%-10s%-10s%-10s%-100s%-20s", model.getTime(), model.getTenantId(), model.getUserId(),
                    model
                            .getUrl(),
                    model.getArg());
            System.out.println();
        });
    }

    public static List<Model> transfer2Model(List<String> logs) {
        List<Model> models = Lists.newArrayList();
        logs.forEach(log -> {
            Model model = getModel(log);
            if(!model.getArg().equals("null")){
                models.add(getModel(log));
            }
        });
        return models;
    }

    public static Model getModel(String log) {
        Model model = new Model();
        model.time(getTime(log))
                .tenantId(getTenantId(log))
                .userId(getUserId(log))
                .url(getUrl(log))
                .arg(getArg(log));

        return model;
    }

    private static String getTime(String log) {
        return log.substring(0, 9);
    }

    private static String getTenantId(String log) {
        int tenantIdStartIndex = log.indexOf("x-fs-ei=") + 8;
        int tenantIdEndIndex = log.indexOf(",", tenantIdStartIndex);

        return log.substring(tenantIdStartIndex, tenantIdEndIndex);
    }

    private static String getUserId(String log) {
        int userIdEndIndex = log.indexOf(";color=");
        int userIdStartIndex = log.lastIndexOf(".", userIdEndIndex) + 1;

        return log.substring(userIdStartIndex, userIdEndIndex);
    }

    private static String getUrl(String log) {
        int urlStartIndex = log.indexOf("/crmrest/") + 9;
        int urlEndIndex = log.indexOf(")", urlStartIndex);

        return log.substring(urlStartIndex, urlEndIndex);
    }

    private static String getArg(String log) {
        int argStartIndex = log.indexOf("arg:") + 4;
        int argEndIndex = log.indexOf("java.net.");

        return log.substring(argStartIndex, argEndIndex);
    }
}
