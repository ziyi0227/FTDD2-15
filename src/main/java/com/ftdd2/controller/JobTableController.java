package com.ftdd2.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ftdd2.common.vo.Result;
import com.ftdd2.domain.DTO.JobQueryDTO;
import com.ftdd2.domain.entity.JobTable;
import com.ftdd2.service.IJobTableService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 招聘信息表 前端控制器
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
@RestController
@RequestMapping("/job-table")
public class JobTableController {

    @Autowired
    private IJobTableService jobTableService;

    @GetMapping("/add")
    public Result<Map<String, Object>> addJobTable() {
        return Result.success("添加成功");
    }

    /**
     *
     * @param jobQueryDTO
     * @return
     */
    @ApiOperation("分页查询接口")
    @GetMapping("/list")
    public Result<?> getJobListPage(@RequestBody JobQueryDTO jobQueryDTO) {
        LambdaQueryWrapper<JobTable> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(JobTable::getJdTitle, jobQueryDTO.getJdTitle()) //标题
                .like(JobTable::getCompany, jobQueryDTO.getCompany()) //公司名
                .like(JobTable::getJdSubType, jobQueryDTO.getJdSubType()) //类型
                .lt(jobQueryDTO.getMaxSalary()!=null,JobTable::getMaxSalary, jobQueryDTO.getMaxSalary()) //小于最大值
                .gt(jobQueryDTO.getMinSalary()!=null,JobTable::getMinSalary, jobQueryDTO.getMinSalary()); //大于最小薪资
        Page<JobTable> page = new Page<>(jobQueryDTO.getPageNo(), jobQueryDTO.getPageSize());

        jobTableService.page(page, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());
        return Result.success(data);
    }
}
