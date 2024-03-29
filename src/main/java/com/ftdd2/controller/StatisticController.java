package com.ftdd2.controller;


import com.ftdd2.common.vo.Result;
import com.ftdd2.service.IActionTableService;
import com.ftdd2.service.IJobTableService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Resource
    private IJobTableService jobTableService;
    @Resource
    private IActionTableService actionTableService;

    /**
     *  统计当前最热门的五个职位
     */

    @GetMapping("/hot-job")
    public Result<List<String>> getHotJob() {
        List<String>jobList=jobTableService.getNowTitle(LocalDateTime.now());
        return Result.success(jobList);
    }

    @GetMapping("/getTitleCount")
    public Result<Map<String,Long>> getTitleCount(@RequestParam String jobTitle) {
        return Result.success(actionTableService.getTitleCount(jobTitle));
    }
}
