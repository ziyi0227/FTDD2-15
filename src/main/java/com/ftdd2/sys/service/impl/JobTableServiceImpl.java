package com.ftdd2.sys.service.impl;

import com.ftdd2.common.vo.PageResult;
import com.ftdd2.sys.Pojo.JobTableQuery;
import com.ftdd2.sys.entity.JobTable;
import com.ftdd2.sys.mapper.JobTableMapper;
import com.ftdd2.sys.service.IJobTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 招聘信息表 服务实现类
 * </p>
 *
 * @author ftdd2
 * @since 2024-02-16
 */
@Service
public class JobTableServiceImpl extends ServiceImpl<JobTableMapper, JobTable> implements IJobTableService {


    @Autowired
    private JobTableMapper jobTableMapper;
    @Override
    @Transactional
    public void add(JobTable jobTable) {
        jobTableMapper.insert(jobTable);

    }

//    @Override
//    public PageResult pageQuery(JobTableQuery jobTableQuery) {
//        PageHelper.startPage(jobTableQuery.getPage(),jobTableQuery.getPageSize());
//        Page<JobTable> page= jobTableMapper.pageQuery(jobTableQuery);
//
//        return new PageResult(page.getTotal(),page.getResult());
//    }
}
