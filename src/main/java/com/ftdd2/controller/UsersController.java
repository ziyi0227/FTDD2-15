package com.ftdd2.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ftdd2.common.vo.Result;
import com.ftdd2.domain.DTO.JobQueryDTO;
import com.ftdd2.domain.DTO.UserDTO;
import com.ftdd2.domain.DTO.UserInfoDTO;
import com.ftdd2.domain.entity.JobTable;
import com.ftdd2.domain.entity.Resume;
import com.ftdd2.domain.entity.User;

import com.ftdd2.domain.entity.UserJob;
import com.ftdd2.service.IFavorService;
import com.ftdd2.service.IJobTableService;
import com.ftdd2.service.IUsersService;
import com.ftdd2.utils.ThreadLocalUtil;
import com.ftdd2.utils.XinUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.PeriodUnit;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpHeaders;
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
    public Result<?> getUserInfo(@RequestParam("token") String token) {
        Map<String, Object> data = usersService.getUserInfo(token);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail(20003, "登录信息无效，重新登录");
    }

    @GetMapping("/Info")
    public Result<?> UserInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();

        String id = (String) map.get("id");
        User user = usersService.getById(id);
        return Result.success(user);
    }


    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("token") String token) {
        usersService.logout(token);
        return Result.success();
    }


    @PutMapping("/updateInfo")
    public Result<?> updateInfo(@RequestBody UserInfoDTO userInfoDTO) {
        usersService.updateInfo(userInfoDTO);
        return Result.success("编辑成功");
    }

    @PostMapping("/uploadResume")
    public Result<?> uploadResume(MultipartFile File) throws Exception {
        Resume resume = new Resume();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        System.out.println(File.getOriginalFilename());
        File file = convertMultiPartToFile(File);
        try {
            body.add("Resume", new FileSystemResource(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String filePath = file.getAbsolutePath();
        resume = XinUtils.parseResume(filePath);
        Map<String, Object> map = ThreadLocalUtil.get();
        String id = (String) map.get("id");
        resume.setUserId(id);
        return Result.success(resume);
    }
    @PostMapping("/addResume")
    public Result<?> addResume(@RequestBody Resume resume) {
        usersService.insertResume(resume);
        return Result.success("添加成功");
    }

    /**
     * 针对用户
     * @return
     */
    @GetMapping("/actionInfo")
    public Result<?> getActionInfo() {
        Map<String, Object> data = usersService.getActionList();
        return Result.success(data);
    }
    /**
     * 针对hr
     */
    @GetMapping("/actionInfoHr")
    public Result<?> getActionInfoHr() {
        Map<String, Object> data = usersService.getActionListHr();
        return Result.success(data);
    }

    /**
     * hr查看已投简历
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/resumeList")
    public Result<?> getResume(@RequestParam Long pageNo,
                               @RequestParam Long pageSize) {
        Map<String,Object>data=usersService.getResumeList(pageNo,pageSize);
        return Result.success(data);
    }

    /**
     * hr自己发布的职位
     * @return
     */
    @GetMapping("/jobList")
    public Result<?> getJobList(@RequestParam int pageNo,
                                @RequestParam int pageSize) {
      Map<String,Object>data=usersService.getJobList(pageNo,pageSize);
        return Result.success(data);
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        file.transferTo(convFile);
        return convFile;
    }


}
