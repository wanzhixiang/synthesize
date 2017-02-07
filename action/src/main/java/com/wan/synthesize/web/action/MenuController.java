package com.wan.synthesize.web.action;

import com.wan.synthesize.baseenum.ConsisEnum;
import com.wan.synthesize.common.ReturnResult;
import com.wan.synthesize.domain.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public ReturnResult getMenuTree(HttpServletRequest request) {
        ReturnResult returnResult = new ReturnResult();
        List<Menu> menus = ( List<Menu>) request.getSession().getAttribute(ConsisEnum.MENU_SESSION.name());
        if (menus!=null && menus.size()>0){
            returnResult.setData(menus);
        }
        return returnResult;
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
