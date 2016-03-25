package com.studease.jdemo.json;

/**
 * Author: liushaoping
 * Date: 2016/3/25.
 */
public enum Country {
    CHINA("中国", 1000),
    USA("美国", 1001),
    UK("英国", 1002);

    private String chineseName;
    private int code;

    Country(String chineseName, int code) {
        this.chineseName = chineseName;
        this.code = code;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
