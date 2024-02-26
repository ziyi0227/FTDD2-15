package com.ftdd2.sys.service.impl;



import com.ftdd2.common.vo.PageResult;
import com.ftdd2.sys.mapper.JobTableMapper;

import com.ftdd2.sys.Pojo.JobCategoryQuery;
import com.ftdd2.sys.entity.JobCategory;
import com.ftdd2.sys.mapper.JobCategoryMapper;
import com.ftdd2.sys.service.IJobCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ftdd2
 * @since 2024-02-16
 */
@Service
public class JobCategoryServiceImpl extends ServiceImpl<JobCategoryMapper, JobCategory> implements IJobCategoryService {

    @Autowired
    private JobCategoryMapper jobCategoryMapper;
 @Autowired
 private JobTableMapper jobTableMapper;

    @Override
    public List<JobCategory> list(String title) {

        return jobCategoryMapper.list(title);
    }

    @Override
    public void add(JobCategory jobCategory) {
        jobCategoryMapper.insert(jobCategory);
    }



    @Override
    public void deleteById(Long Tid) {
        Integer count=jobTableMapper.countCategory(Tid);
        if(count>0){

        }
        jobCategoryMapper.deleteById(Tid);
    }

    public void update(JobCategory jobCategory)
    {

        jobCategoryMapper.update(jobCategory);
    }
}
