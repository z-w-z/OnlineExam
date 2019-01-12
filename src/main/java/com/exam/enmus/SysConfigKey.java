package com.exam.enmus;

public enum SysConfigKey {
    CLOUD_STORAGE_CONFIG("CLOUD_STORAGE_CONFIG","云存储配置"),
    BAIDU_PUSH_URL("BAIDU_PUSH_URL", "百度推送地址"),
    SITE_NAME("SITE_NAME","网站名称"),
    SITE_DESC("SITE_DESC","网站描述"),
    SITE_KWD("SITE_KWD","网站关键字"),
    SITE_PERSON_NAME("SITE_PERSON_NAME","站长名称"),
    SITE_PERSON_DESC("SITE_PERSON_DESC","站长描述"),
    SITE_PERSON_PIC("SITE_PERSON_PIC","站长头像"),
    ;

    private String value;
    private String describe;

    private SysConfigKey(String value, String describe) {
        this.value = value;
        this.describe = describe;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescribe() {
        return this.describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

}