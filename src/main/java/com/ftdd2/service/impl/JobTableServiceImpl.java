package com.ftdd2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ftdd2.domain.entity.JobTable;
import com.ftdd2.domain.entity.UserJob;
import com.ftdd2.mapper.JobTableMapper;
import com.ftdd2.mapper.UserJobMapper;
import com.ftdd2.service.IJobTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ftdd2.utils.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Resource
    private UserJobMapper userJobMapper;

    @Resource
    private JobTableMapper jobTableMapper;

    @Override
    @Transactional
    public void addJobTable(JobTable jobTable, String token) {
        this.baseMapper.insert(jobTable);

        Map<String,Object> claims = JwtUtil.parseToken(token);
        String userId = (String) claims.get("id");
        Integer jobId = jobTable.getId();
        if (jobId != null) {
            userJobMapper.insert(new UserJob(null, userId, jobId));
        }
    }

    @Override
    public void updateJobTable(JobTable jobTable) {
        this.baseMapper.updateById(jobTable);
    }

    @Override
    public List<JobTable> listById(String token) {
        Map<String,Object> claims = JwtUtil.parseToken(token);
        String userId = (String) claims.get("id");

        LambdaQueryWrapper<UserJob> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserJob::getUserId,userId);
        List<UserJob> userJobList = userJobMapper.selectList(wrapper);

        List<Integer> jobIdList = userJobList.stream()
                                             .map(userJob -> {return userJob.getJobId();})
                                             .collect(Collectors.toList());

        // 查询对应的岗位信息
        List<JobTable> jobTableList = new ArrayList<>();
        if (!jobIdList.isEmpty()) {
            LambdaQueryWrapper<JobTable> jobWrapper = new LambdaQueryWrapper<>();
            jobWrapper.in(JobTable::getId, jobIdList);
            jobTableList = jobTableMapper.selectList(jobWrapper);
        }

        return jobTableList;
    }
}
