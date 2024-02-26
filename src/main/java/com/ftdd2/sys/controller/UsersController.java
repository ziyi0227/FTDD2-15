package com.ftdd2.sys.controller;

import com.ftdd2.common.vo.Result;
import com.ftdd2.sys.entity.Users;
import com.ftdd2.sys.service.IUsersService;
import com.ftdd2.utils.JwtUtil;
import com.ftdd2.utils.Md5Util;
import com.ftdd2.utils.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @ApiOperation("用户信息查询")
    @GetMapping("/all")
    public Result<List<Users>> getAllUser(){
        List<Users> list = usersService.list();
        return Result.success(list,"查询成功");
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody Users user){
       Users u = usersService.login(user);

        if(u == null){
            return Result.fail("用户名或密码错误");
        }
        Map<String,Object>claims = new HashMap<>();
        claims.put("id",u.getId());
        claims.put("username",u.getUsername());
        String token = JwtUtil.genToken(claims);

        ValueOperations<String,String>operations=stringRedisTemplate.opsForValue();
        operations.set(token,token,1, TimeUnit.HOURS);//JWT存入redis，1小时后在内存中销毁

        return Result.success(token);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result  register(@Pattern(regexp = "^\\S{1,20}$") String username,
                            @Pattern(regexp = "^\\S{1,20}$") String password){
        Users user = usersService.findByUsername(username);
        if(user==null){
            usersService.register(username,password);
            return Result.success();
        }else{
            return Result.fail("用户名已经被占用");
        }
    }


    @ApiOperation("修改密码")
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String>params,@RequestHeader("token") String token)
    {

        String oldPwd= params.get("old_pwd");
        String newPwd=params.get("new_pwd");
        String rePwd=params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)
                ||!StringUtils.hasLength(rePwd))
        {
            return Result.fail("缺少必要参数");
        }

        //原密码
        Map<String,Object>map = ThreadLocalUtil.get();
        String username=(String)map.get("username");
        Users user= usersService.findByUsername(username);
        if(!user.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.fail("原密码填写错误");
        }
        if(!rePwd.equals(newPwd)){
            return Result.fail("两次填写密码不一致");
        }
        usersService.updatePwd(newPwd);
        //删除redis中的token
        ValueOperations<String,String>operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);

        return Result.success();
    }
    @ApiOperation("更新个人信息")
    @PutMapping("/update")
    public Result update(@RequestBody @Validated Users user){
        usersService.update(user);
        return Result.success();
    }
}
