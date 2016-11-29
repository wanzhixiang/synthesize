package com.wan.synthesize.dao.mapper.user;

import com.wan.synthesize.domain.UserInfo;

/**
 * Created by wzx on 2016/11/29.
 */
public interface UserMapper {

    /**
     * 通过id查询用户
     * @param userId
     * @return
     */
    UserInfo getUserInfoById(String userId);

    /**
     * 校验用户是否存在
     * @param userInfo 用户类
     * @return true：存在;false:不存在
     */
    boolean checkUser(UserInfo userInfo);
}
