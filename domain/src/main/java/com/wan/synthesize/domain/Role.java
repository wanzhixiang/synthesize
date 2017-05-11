package com.wan.synthesize.domain;

import java.util.List;

/**
 * Created by wzx on 2017/1/9.
 * 角色
 */
public class Role {
    //角色名
    private String name;
    //菜单
    private List<Menu> menus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
