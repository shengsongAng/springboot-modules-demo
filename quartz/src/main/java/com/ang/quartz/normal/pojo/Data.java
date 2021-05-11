package com.ang.quartz.normal.pojo;

/**
 * @description: ${description}
 * @author: ssang
 * @create: 2021/5/11 0011 15:48
 **/
public class Data {
    String id;
    String flag;

    public Data(String id, String flag) {
        this.id = id;
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
