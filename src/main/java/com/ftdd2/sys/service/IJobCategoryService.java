package com.ftdd2.sys.service;

import com.ftdd2.common.vo.PageResult;
import com.ftdd2.sys.Pojo.JobCategoryQuery;
import com.ftdd2.sys.entity.JobCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ftdd2
 * @since 2024-02-16
 */
public interface IJobCategoryService extends IService<JobCategory> {


    List<JobCategory> list(String title);


    void add(JobCategory jobCategory);


    void deleteById(Long id);
}
