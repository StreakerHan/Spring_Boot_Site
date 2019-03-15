package com.hyp.dto.cond;

import com.hyp.utils.TaleUtils;

/**
 * 

* @Title: UserCond  

* @Description: 用户查找条件  

* @author HanYupeng  

* @date 2018-06-15 10:04
 */
public class UserCond {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
