package com.wan.synthesize.service.impl;

import com.wan.synthesize.dao.mapper.user.UserMapper;
import com.wan.synthesize.domain.UserInfo;
import com.wan.synthesize.service.IUserService;
import com.wan.synthesize.utils.weChat.WXMD5;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import javax.annotation.Resource;

/**
 * Created by zhixiang.wan on 2016/10/24.
 * 基础用户service实现类
 */
@Service
public class UserServiceImpl implements IUserService{

    @Resource
    private UserMapper mapper;

    public UserInfo getUserInfoById(String userId) {
        return mapper.getUserInfoById(userId);
    }

    public boolean checkUser(UserInfo userInfo) {
        UserInfo user = mapper.getUserInfoByName(userInfo.getUserName());
        if (user!=null){
            String password = user.getPassword();
            String messageDigest = WXMD5.getMessageDigest(userInfo.getPassword().getBytes());
            if (password.equals(messageDigest)){
                return true;
            }
        }
        return false;
    }
}
