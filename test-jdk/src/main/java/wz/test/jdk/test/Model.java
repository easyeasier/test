package wz.test.jdk.test;

import lombok.Data;

/**
 * Created by wangz on 17-10-25.
 */
@Data
public class Model {
    private String time;
    private String tenantId;
    private String userId;
    private String url;
    private String arg;

    public Model time(String time){
        setTime(time);
        return this;
    }

    public Model tenantId(String tenantId){
        setTenantId(tenantId);
        return this;
    }

    public Model userId(String userId){
        setUserId(userId);
        return this;
    }

    public Model url(String url){
        setUrl(url);
        return this;
    }

    public Model arg(String arg){
        setArg(arg);
        return this;
    }
}
