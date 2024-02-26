package com.ftdd2.sys.controller;

import cn.hutool.db.PageResult;
import com.ftdd2.common.vo.Result;
import com.ftdd2.sys.Pojo.JobCategoryQuery;
import com.ftdd2.sys.entity.JobCategory;
import com.ftdd2.sys.service.IJobCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ftdd2
 * @since 2024-02-16
 */
@Controller
@RequestMapping("/sys/jobCategory")
@Api(tags = "职业分类相关接口")
@Slf4j
public class JobCategoryController {
    @Autowired
    private IJobCategoryService jobCategoryService;

    @ApiOperation("根据职业分类查询")
    @GetMapping("/list")
    public Result<List<JobCategory>>list(String title){
        List<JobCategory> js=jobCategoryService.list(title);
        return Result.success(js);
    }
    @PostMapping
    @ApiOperation("新增分类")
    public Result<String> save(@RequestBody JobCategory jobCategory){

        jobCategoryService.add(jobCategory);
        return Result.success();
    }
  @DeleteMapping
  @ApiOperation("删除分类")
  public Result<String> deleteById(Long id){
        jobCategoryService.deleteById(id);
        return Result.success();
  }

}
