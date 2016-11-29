package com.wan.synthesize.web.action;


import com.wan.synthesize.common.ReturnResult;
import com.wan.synthesize.domain.UserInfo;
import com.wan.synthesize.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by wzx on 2016/7/28.
 */
@Controller
public class LoginController {

    private static Logger logger = Logger.getLogger(LoginController.class);

    @Resource
    private IUserService service;
    /**
     * 登录页
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        return "WEB-INF/index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult login(HttpServletRequest request,UserInfo userInfo){
        ReturnResult result = new ReturnResult();
        service.checkUser(userInfo);

        return result;
    }

    @RequestMapping(value = "/logout")
    public void logout(){

    }
}
