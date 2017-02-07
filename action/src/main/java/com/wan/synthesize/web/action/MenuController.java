package com.wan.synthesize.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wzx on 2016/12/27.
 * 菜单管理
 */
@RequestMapping("/menu")
@Controller
public class MenuController {


    /**
     * 获取菜单栏
     */
    @RequestMapping(value = "/getMenu")
    @ResponseBody
    public void getMenuTree() {

    }

    /**
     * 获取菜单列表
     */
    @RequestMapping(value = "/getMenuList")
    @ResponseBody
    public void getMenus() {

    }

    /**
     * 删除菜单
     */
    @RequestMapping(value = "/deleteMenu")
    @ResponseBody
    public void deleteMenu() {

    }

    /**
     * 添加菜单
     */
    @RequestMapping(value = "/addMenu")
    @ResponseBody
    public void addMenu() {

    }

    /**
     * 更新菜单
     */
    @RequestMapping(value = "/updateMenu")
    @ResponseBody
    public void updateMenu() {

    }

}
