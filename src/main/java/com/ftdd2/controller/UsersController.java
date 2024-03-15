package com.ftdd2.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ftdd2.common.vo.Result;
import com.ftdd2.domain.DTO.JobQueryDTO;
import com.ftdd2.domain.DTO.UserDTO;
import com.ftdd2.domain.entity.Favor;
import com.ftdd2.domain.entity.JobTable;
import com.ftdd2.domain.entity.User;

import com.ftdd2.service.IFavorService;
import com.ftdd2.service.IJobTableService;
import com.ftdd2.service.IUsersService;

import com.ftdd2.utils.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private IJobTableService jobTableService;
    @Autowired
    private IFavorService favorService;

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        Map<String, Object> data = usersService.login(user);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail(20002, "用户名或密码错误");
    }

    @ApiOperation("注册接口")
    @PostMapping("/register")
    public Result<?> register(@RequestBody UserDTO userDTO) {
        User register = usersService.register(userDTO);
        if (register != null) {
            return Result.success("注册成功");
        }
        return Result.fail(20001, "注册失败");
    }


    @ApiOperation("分页查询接口")
    @GetMapping("/job/list")
    public Result<?> getJobListPage(@RequestBody JobQueryDTO jobQueryDTO) {
        LambdaQueryWrapper<JobTable> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(JobTable::getJdTitle, jobQueryDTO.getJdTitle()) //标题
                .like(JobTable::getCompany, jobQueryDTO.getCompany()) //公司名
                .like(JobTable::getJdSubType, jobQueryDTO.getJdSubType()) //类型
                .lt(JobTable::getMaxSalary, jobQueryDTO.getMaxSalary()) //小于最大值
                .gt(JobTable::getMinSalary, jobQueryDTO.getMinSalary()); //大于最小薪资
        Page<JobTable> page = new Page<>(jobQueryDTO.getPageNo(), jobQueryDTO.getPageSize());

        jobTableService.page(page, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());
        return Result.success(data);
    }

    @ApiOperation("收藏列表查询")
    @GetMapping("/favor")
    public Result<?> getFavorPage(@RequestParam int pageNo,
                                  @RequestParam int pageSize) {
        Map<String,Object>data=usersService.getFavorList(pageNo,pageSize);
        return Result.success(data);
    }
    @ApiOperation("用户操作（收藏）")
    @PutMapping("/favor/{jdNo}")
    public Result<?>setFavor(@PathVariable String jdNo){
     int choice= favorService.setFavor(jdNo);
     if(choice==0)
     {
         return Result.success("已删除该收藏");
     }
     return Result.success("收藏成功!");
    }
}
