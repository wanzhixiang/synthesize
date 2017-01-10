package com.wan.synthesize.utils;

import com.wan.synthesize.domain.Menu;
import com.wan.synthesize.domain.Role;
import com.wan.synthesize.domain.UserInfo;

import java.util.List;

/**
 * Created by wzx on 2017/1/9.
 * 权限公共类
 */
public class RoleUtil {

    /**
     * 判断当前是否具有传入参数的权限
     * @param userInfo 登录信息
     * @param url 请求路径
     * @return
     */
    public static boolean isAllowed(UserInfo userInfo, String url){
        if (userInfo!=null){
            List<Role> roles = userInfo.getRoles();
            if (roles!=null && roles.size()>0){
                for (Role role : roles) {
                    List<Menu> menus = role.getMenus();
                    for (Menu menu : menus) {
                        if (menu.equals(url.trim())){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
