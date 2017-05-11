package com.wan.synthesize.baseenum;

/**
 * Created by wzx on 2017/1/9.
 * 系统配置Enum
 */
public enum  ConsisEnum {
    USER_SESSION("用户登录session"),MENU_SESSION("用户菜单session");
    private String des;

    ConsisEnum(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }
}
