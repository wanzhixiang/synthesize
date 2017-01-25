package com.wan.synthesize.web.action;

import com.wan.synthesize.baseenum.ConsisEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wzx on 2016/12/27.
 * 角色管理
 */
@RequestMapping(value = "/role")
@Controller
public class RoleController {

    /**
     * 获取角色
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "/WEB-INF/system/role";
    }
    /**
     * 获取角色
     */
    @RequestMapping(value = "/getRoleByUser")
    @ResponseBody
    public void getRoleByUser() {

    }

    /**
     * 删除角色
     */
    @RequestMapping(value = "/deleteRole")
    @ResponseBody
    public void deleteRole() {

    }

    /**
     * 添加角色
     */
    @RequestMapping(value = "/addRole")
    @ResponseBody
    public void addRole() {

    }

    /**
     * 更新角色
     */
    @RequestMapping(value = "/updateRole")
    @ResponseBody
    public void updateRole() {

    }
}
