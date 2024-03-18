package com.ftdd2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ftdd2.domain.entity.Favor;
import com.ftdd2.domain.entity.JobTable;
import com.ftdd2.mapper.JobTableMapper;
import com.ftdd2.service.IJobTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ftdd2.utils.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 招聘信息表 服务实现类
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
@Service
public class JobTableServiceImpl extends ServiceImpl<JobTableMapper, JobTable> implements IJobTableService {


    @Override
    public void addJobTable(JobTable jobTable) {
        this.baseMapper.insert(jobTable);
    }
}
