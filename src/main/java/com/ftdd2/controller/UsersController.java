package com.ftdd2.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ftdd2.common.vo.Result;
import com.ftdd2.domain.DTO.JobQueryDTO;
import com.ftdd2.domain.DTO.UserDTO;
import com.ftdd2.domain.DTO.UserInfoDTO;
import com.ftdd2.domain.entity.JobTable;
import com.ftdd2.domain.entity.User;

import com.ftdd2.service.IFavorService;
import com.ftdd2.service.IJobTableService;
import com.ftdd2.service.IUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.PeriodUnit;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
@RestController
@RequestMapping("/users")
@Api(tags = "用户相关接口")
public class UsersController {

    @Autowired
    private IUsersService usersService;

    /**
     *
     * @param user
     * @return
     */
    @ApiOperation("登录接口")
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        Map<String, Object> data = usersService.login(user);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail(20002, "用户名或密码错误");
    }

    /**
     *
     * @param userDTO
     * @return
     */
    @ApiOperation("注册接口")
    @PostMapping("/register")
    public Result<?> register(@RequestBody UserDTO userDTO) {
        User register = usersService.register(userDTO);
        if (register != null) {
            return Result.success("注册成功");
        }
        return Result.fail(20001, "注册失败");
    }

    @GetMapping("/info")
    public Result<?>getUserInfo(@RequestParam("token") String token)
    {
        Map<String,Object>data=usersService.getUserInfo(token);
        if(data!=null){
            return Result.success(data);
        }
        return Result.fail(20003,"登录信息无效，重新登录");
    }

    @PostMapping("/logout")
    public Result<?>logout(@RequestHeader("token")String token){
        usersService.logout(token);
        return Result.success();
    }


    @PutMapping("/updateInfo")
    public Result<?>updateInfo(@RequestBody UserInfoDTO userInfoDTO ){
        usersService.updateInfo(userInfoDTO);
        return Result.success("编辑成功");
    }
}
