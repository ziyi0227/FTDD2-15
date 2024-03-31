package com.ftdd2.controller;


import com.ftdd2.common.vo.Result;
import com.ftdd2.service.IActionTableService;
import com.ftdd2.service.IJobTableService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


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

    /**
     * 获取热门职位的投递量和收藏量每月之和
     * @param jobTitle
     * @return
     */
    @GetMapping("/getTitleCount")
    public Result<Map<String,Long>> getTitleCount(@RequestParam String jobTitle) {
        return Result.success(actionTableService.getTitleCount(jobTitle));
    }

    /**
     * 使用统计一天热门的专业
     */
    // TODO 暂时弃用
    @GetMapping("/hot-major")
    public Result<List<Map<String,Long>>> getHotMajor(LocalDateTime time){
        List<Map<String, Long>> majorList=jobTableService.getNowMajor(time);
        return Result.success(majorList);
    }
    @GetMapping("/getHotMajor")
    public Result<List<Map<String, Object>>> getHotMajor() {
        List<Map<String, Object>> hotMajorData = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);

        for (int i = 0; i < 7; i++) {
            LocalDateTime day = now.minusDays(i);
            String dayOfWeek = day.format(formatter);
            Map<String, Object> dailyData = new LinkedHashMap<>();
            dailyData.put("date", day);
            dailyData.put("dayOfWeek", dayOfWeek);
            Map<String, Long> majorData = jobTableService.getHotMajor(day);
            dailyData.put("majorData", majorData);
            hotMajorData.add(dailyData);
        }

        return Result.success(hotMajorData);
    }

}
