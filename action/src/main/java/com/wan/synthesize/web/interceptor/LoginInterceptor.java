package com.wan.synthesize.web.interceptor;

import com.wan.synthesize.baseenum.ConsisEnum;
import com.wan.synthesize.domain.UserInfo;
import com.wan.synthesize.service.IUserService;
import com.wan.synthesize.utils.RoleUtil;
import com.wan.synthesize.utils.cookie.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zhixiang.wan on 2016/10/21.
 * 登录拦截器
 *  第一种方式是实现Spring的HandlerInterceptor 接口，或者是这个类继承实现了HandlerInterceptor 接口的类(andlerInterceptorAdapter) ；
 *  第二种方式是实现Spring的WebRequestInterceptor接口，或者是继承实现了WebRequestInterceptor的类。
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private IUserService userService;
    /**
     * 该方法将在请求处理之前进行调用
     * @param httpServletRequest
     * @param httpServletResponse
     * @param handle
     * @return false:interceptor和controller都不在调用,true:继续执行下个interceptor或controller
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handle) throws Exception {
        String requestUri = httpServletRequest.getRequestURI();
        String contextPath = httpServletRequest.getContextPath();
        String url = requestUri.substring(contextPath.length());
        //先取session
        HttpSession session = httpServletRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ConsisEnum.USER_SESSION.name());
        if(userInfo!=null){
            //判断是否有权限.
            boolean allowed = RoleUtil.isAllowed(userInfo, url);
            if (!allowed){
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "login.html");
            }
            return allowed;
        }
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "login.html");
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handle, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handle, Exception e) throws Exception {

    }
}
