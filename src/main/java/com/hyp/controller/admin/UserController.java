package com.hyp.controller.admin;

import com.github.pagehelper.PageHelper;
import com.hyp.model.UserDomain;
import com.hyp.service.user.UserService;
import com.hyp.utils.APIResponse;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 

* @Title: UserController  

* @Description: 用户管理控制器  

* @author HanYupeng  

* @date 2018-06-14 15:24
 */
@Api("用户管理类")
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/docker")
    @ResponseBody
    public APIResponse<String> dockerTest(){
        return APIResponse.success((Object) "hello docker");
    }
    
}
