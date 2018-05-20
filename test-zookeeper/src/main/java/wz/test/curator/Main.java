package wz.test.curator;

public class Main {


    public static void main(String[] args) {
        String path = "/test1";
        ZKClient zkClient = new ZKClient();

        EventWatcher eventWatcher = new EventWatcher(zkClient.getZkClient());
        try {
            eventWatcher.addPathEvent(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) ;
    }
}
