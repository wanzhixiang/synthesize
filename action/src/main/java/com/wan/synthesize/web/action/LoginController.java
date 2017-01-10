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


/**
 * Created by wzx on 2016/7/28.
 */
@Controller
public class LoginController {

    private static Logger logger = Logger.getLogger(LoginController.class);

    @Resource
    private IUserService service;

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        return "/WEB-INF/system/index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult login(HttpServletRequest request,UserInfo userInfo){
        ReturnResult result = new ReturnResult();
        String validate = userInfo.validate();
        if (validate!=null || "".equals(validate)){
            result.setSuccess(false);
            result.setMessage(validate);
            return result;
        }
        boolean b = service.checkUser(userInfo);
        if(!b){
            result.setSuccess(false);
            result.setMessage("用户名或密码错误");
        }
        return result;
    }

    @RequestMapping(value = "/logout")
    public void logout(){

    }
}
