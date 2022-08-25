package com.jonefywang.blog.controller;


import com.jonefywang.blog.common.lang.Result;
import com.jonefywang.blog.entity.User;
import com.jonefywang.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JonefyWAng
 * @since 2022-08-23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public Object index(){
        User user = userService.getById(1L);
        return Result.success(200,"操作成功！", user);
    }

    @GetMapping("/save")
    public Object save(@Validated @RequestBody User user){
        return Result.success(user);
    }
}
