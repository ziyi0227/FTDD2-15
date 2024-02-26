package com.ftdd2.sys.controller;

import com.ftdd2.common.vo.PageResult;
import com.ftdd2.common.vo.Result;
import com.ftdd2.sys.Pojo.JobTableQuery;
import com.ftdd2.sys.entity.JobTable;
import com.ftdd2.sys.service.IJobTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 招聘信息表 前端控制器
 * </p>
 *
 * @author ftdd2
 * @since 2024-02-16
 */
@Controller
@RequestMapping("/sys/jobTable")
@Api(tags = "招聘信息相关接口")
public class JobTableController {
    @Autowired
    private IJobTableService jobTableService;
    @PostMapping
    @ApiOperation("新增招聘信息")
    public Result add(@RequestBody JobTable jobTable){
        jobTableService.add(jobTable);
        return Result.success();
    }
    @GetMapping("/page")
    public Result<PageResult>page(JobTableQuery jobTableQuery){
        PageResult pageResult=jobTableService.pageQuery(jobTableQuery);
        return Result.success(pageResult);
    }
}
