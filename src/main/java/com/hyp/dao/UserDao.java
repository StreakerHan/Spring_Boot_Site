package com.hyp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hyp.dto.cond.UserCond;
import com.hyp.model.UserDomain;

import java.util.List;

/**
 * 

* @Title: UserDao  

* @Description: 用户接口类  

* @author HanYupeng  

* @date 2018-06-15 09:53
 */
@Mapper
public interface UserDao {

    /**
     * @Author: Donghua.Chen
     * @Description: 更改用户信息
     * @Date: 2018/4/20
     * @param user
     */
    int updateUserInfo(UserDomain user);

    /**
     * @Author: Donghua.Chen
     * @Description: 根据主键编号获取用户信息
     * @Date: 2018/4/20
     * @param uId 主键
     */
    UserDomain getUserInfoById(@Param("uid") Integer uId);

    /**
     * 根据用户名和密码获取用户信息
     * @param username
     * @param password
     * @return
     */
    UserDomain getUserInfoByCond(@Param("username") String username, @Param("password") String password);

}
