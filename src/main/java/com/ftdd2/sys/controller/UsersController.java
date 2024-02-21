package com.ftdd2.sys.controller;

import com.ftdd2.common.vo.Result;
import com.ftdd2.sys.entity.Users;
import com.ftdd2.sys.service.IUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ftdd2
 * @since 2024-02-16
 */
@Api(tags = {"用户接口列表"})
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private IUsersService usersService;

    @ApiOperation("用户信息查询")
    @GetMapping("/all")
    public Result<List<Users>> getAllUser(){
        List<Users> list = usersService.list();
        return Result.success(list,"查询成功");
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody Users user){
        Map<String,Object> data = usersService.login(user);
        if(data != null){
            return Result.success(data,"登录成功");
        }
        return Result.fail(20002,"用户名或密码错误");
    }
}
