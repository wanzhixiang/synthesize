package com.wan.web.action;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by wzx on 2016/7/28.
 */
@Controller
@RequestMapping(value = "/wzx")
public class LoginController {

    private static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(value = "/index")
    public String index(){
        return "WEB-INF/index";
    }
}
