package com.wan.synthesize.dao.mapper.user;

import com.wan.synthesize.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wzx on 2016/11/29.
 */
public interface UserMapper {

    /**
     * 通过id查询用户
     * @param userId
     * @return 用户信息类
     */
    UserInfo getUserInfoById(String userId);

    /**
     * 通过用户名查询用户
     * @param name 用户名
     * @return 用户信息类
     */
    UserInfo getUserInfoByName(@Param(value = "name") String name);
}
