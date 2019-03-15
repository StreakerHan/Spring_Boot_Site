package com.hyp.service.user.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyp.constant.ErrorConstant;
import com.hyp.dao.UserDao;
import com.hyp.dto.cond.UserCond;
import com.hyp.exception.BusinessException;
import com.hyp.model.UserDomain;
import com.hyp.service.user.UserService;
import com.hyp.utils.TaleUtils;

/**
 * 

* @Title: UserServiceImpl  

* @Description:  站点服务实现层 

* @author HanYupeng  

* @date 2018-06-15 10:34
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;//这里会报错，但是并不会影响


    @Transactional
    @Override
    public int updateUserInfo(UserDomain user) {
        if (null == user.getUid())
            throw BusinessException.withErrorCode("用户编号不可能为空");
        return userDao.updateUserInfo(user);
    }

    @Override
    public UserDomain getUserInfoById(Integer uId) {
        return userDao.getUserInfoById(uId);
    }

    @Override
    public UserDomain login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password))
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_IS_EMPTY);

        String pwd = TaleUtils.MD5encode(username + password);
        //String pwd = password;
        UserDomain user = userDao.getUserInfoByCond(username, pwd);
        if (null == user)
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_ERROR);

        return user;
    }


}
