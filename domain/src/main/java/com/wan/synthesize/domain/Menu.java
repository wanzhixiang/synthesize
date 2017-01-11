package com.wan.synthesize.domain;

import java.util.List;

/**
 * Created by wzx on 2017/1/9.
 * 菜单
 */
public class Menu {
    /**
     * 菜单地址
     */
    private String url;
    /**
     * 菜单名称
     */
    private String name;

    /**
     * 子菜单
     */
    private List<Menu> subMenu;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<Menu> subMenu) {
        this.subMenu = subMenu;
    }
}
