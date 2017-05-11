package com.wan.synthesize.service;

import com.wan.synthesize.domain.Menu;

import java.util.List;

/**
 * Created by wzx on 2017/1/11.
 */
public interface IMenuService {

    /**
     * 通过用户id获取用户权限
     * @param userId
     * @return
     */
    List<Menu> getMenuByUserId(Integer userId);
}
