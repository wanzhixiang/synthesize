package com.wan.synthesize.domain;

import java.util.List;

/**
 * Created by zhixiang.wan on 2016/10/24.
 * 用户基本信息
 */
public class UserInfo {
    private String id;
    private String userName;
    private String email;
    private String password;
    private List<Role> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String validate(){
        StringBuffer sb = new StringBuffer();
        if (userName==null || userName.equals("")){
            sb.append("用户名不能为空!;");
        }if (password==null || password.equals("")){
            sb.append("密码不能为空!;");
        }
        return sb.toString();
    }
}
