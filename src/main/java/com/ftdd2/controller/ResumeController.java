package com.ftdd2.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ftdd2.common.vo.Result;
import com.ftdd2.domain.entity.Resume;
import com.ftdd2.service.IResumeService;
import com.ftdd2.service.IUsersService;
import com.ftdd2.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 简历表 前端控制器
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Resource
    private IResumeService resumeService;
    @Resource
    private IUsersService usersService;

    /**
     * @param
     * @return
     */
    @GetMapping("/getResumeId")
    public Result<?> getResumeId() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String id = (String) map.get("id");
        LambdaQueryWrapper<Resume> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Resume::getUserId, id);
        Resume resume = resumeService.getOne(wrapper);
        return Result.success(resume.getId());
    }
    @GetMapping("/getMyResume")
    public Result<?>getMyResume(){
       Resume resume= usersService.getMyResume();
       return Result.success(resume);
    }

}
