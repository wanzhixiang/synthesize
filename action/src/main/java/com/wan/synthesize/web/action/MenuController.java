package com.wan.synthesize.web.action;

import com.wan.synthesize.baseenum.ConsisEnum;
import com.wan.synthesize.domain.Role;
import com.wan.synthesize.domain.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wzx on 2016/12/27.
 * 菜单管理
 */
@RequestMapping("/menu")
public class MenuController {

    /**
     * 进入菜单页
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "/WEB-INF/system/menu";
    }

    /**
     * 获取菜单
     */
    @RequestMapping(value = "/getMenus")
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
