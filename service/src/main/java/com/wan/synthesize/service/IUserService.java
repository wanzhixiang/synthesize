package com.wan.synthesize.service;

import com.wan.synthesize.domain.UserInfo;

/**
 * Created by zhixiang.wan on 2016/10/24.
 * 基础用户service类
 */
public interface IUserService {

    /**
     * 通过id查询用户
     * @param userId
     * @return
     */
    UserInfo getUserInfoById(String userId);
}
