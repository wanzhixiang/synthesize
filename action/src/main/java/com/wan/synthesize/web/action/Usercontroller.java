package com.wan.synthesize.web.action;

import com.wan.synthesize.domain.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wzx on 2017/1/9.
 * 用户管理
 */
@RequestMapping(value = "/user")
@Controller
public class Usercontroller {

    /**
     * 获取用户
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "/WEB-INF/system/user";
    }

    /**
     * 获取用户
     */
    @RequestMapping(value = "/getUsers")
    @ResponseBody
    public void getUsers() {

    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "/deleteUser")
    @ResponseBody
    public void deleteUser() {

    }

    /**
     * 添加用户
     */
    @RequestMapping(value = "/addUser")
    @ResponseBody
    public void addUser() {

    }

    /**
     * 更新用户
     */
    @RequestMapping(value = "/updateUser")
    @ResponseBody
    public void updateUser(UserInfo userInfo) {

    }
}
