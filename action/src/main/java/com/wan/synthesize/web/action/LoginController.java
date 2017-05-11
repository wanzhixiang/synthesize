package com.wan.synthesize.web.action;


import com.wan.synthesize.baseenum.ConsisEnum;
import com.wan.synthesize.common.ReturnResult;
import com.wan.synthesize.domain.UserInfo;
import com.wan.synthesize.service.IUserService;
import com.wan.synthesize.utils.weChat.WXMD5;
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



    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult login(HttpServletRequest request,UserInfo userInfo){
        ReturnResult result = new ReturnResult();
        String validate = userInfo.validate();
        if (validate!=null && !"".equals(validate)){
            result.setSuccess(false);
            result.setMessage(validate);
            return result;
        }
        try{
            UserInfo user = service.getUserInfoByName(userInfo.getUserName());
            boolean b = false;
            if (user!=null){
                String password = user.getPassword();
                String messageDigest = WXMD5.getMessageDigest(userInfo.getPassword().getBytes());
                if (password.equals(messageDigest)){
                    //将用户放入session
                    b = true;
                    request.getSession().getAttribute(ConsisEnum.USER_SESSION.name());
                }
            }
            if(!b){
                result.setSuccess(false);
                result.setMessage("用户名或密码错误");
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return result;
    }

    @RequestMapping(value = "/logout")
    public void logout(){

    }
}
