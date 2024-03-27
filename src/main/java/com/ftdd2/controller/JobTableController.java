package com.ftdd2.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ftdd2.common.vo.Result;
import com.ftdd2.domain.DTO.JobQueryDTO;
import com.ftdd2.domain.entity.JobTable;
import com.ftdd2.service.IJobTableService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    @PostMapping("/add")
    public Result<Map<String, Object>> addJobTable(@RequestBody JobTable jobTable, @RequestHeader("token") String token) {
        jobTableService.addJobTable(jobTable, token);
        return Result.success("添加成功");
    }

    /**
     * @param jobQueryDTO
     * @return
     */
    @ApiOperation("分页查询接口")
    @GetMapping("/list")
    public Result<?> getJobListPage(JobQueryDTO jobQueryDTO) {
        LambdaQueryWrapper<JobTable> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(JobTable::getJdTitle, jobQueryDTO.getJdTitle()) //标题
                .like(JobTable::getCompany, jobQueryDTO.getCompany()) //公司名
                .like(JobTable::getJdSubType, jobQueryDTO.getJdSubType()) //类型
                .lt(jobQueryDTO.getMaxSalary() != null, JobTable::getMaxSalary, jobQueryDTO.getMaxSalary()) //小于最大值
                .gt(jobQueryDTO.getMinSalary() != null, JobTable::getMinSalary, jobQueryDTO.getMinSalary()); //大于最小薪资
        Page<JobTable> page = new Page<>(jobQueryDTO.getPageNo(), jobQueryDTO.getPageSize());

        jobTableService.page(page, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());
        return Result.success(data);
    }

    /**
     * 根据用户，查询用户上传的jobList
     *
     * @return
     */
    @GetMapping("/all")
    private Result<List<JobTable>> userJobAll(@RequestHeader("token") String token) {
        List<JobTable> jobList = jobTableService.listById(token);
        return Result.success(jobList, "查询成功");
    }

    @DeleteMapping("/delete/{id}")
    public Result<JobTable> deleteJobTable(@PathVariable("id") Integer id) {
        jobTableService.removeById(id);
        return Result.success("删除成功");
    }

    @PutMapping("/update")
    public Result<JobTable> updateJobTable(@RequestBody JobTable jobTable) {
        jobTableService.updateJobTable(jobTable);
        return Result.success("更新成功");
    }

    @PostMapping("/deliver/{jobId}")
    public Result<?> delverResume(@PathVariable Integer jobId) {
        int choice = jobTableService.deliver(jobId);
        if (choice == 1)
            return Result.success("成功投递");
        else
            return Result.success("已取消投递");
    }

    @GetMapping("/deliver/list")
    public Result<?>getDeliverList(@RequestParam int pageNo,
                                   @RequestParam int pageSize){
        Map<String,Object> data=jobTableService.getDeliverList(pageNo,pageSize);
        return Result.success(data);
    }
}
