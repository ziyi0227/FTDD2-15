package com.ftdd2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ftdd2.domain.entity.ActionTable;
import com.ftdd2.domain.entity.JobTable;
import com.ftdd2.domain.entity.UserJob;
import com.ftdd2.mapper.ActionTableMapper;
import com.ftdd2.mapper.JobTableMapper;
import com.ftdd2.mapper.UserJobMapper;
import com.ftdd2.service.IJobTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ftdd2.utils.JwtUtil;
import com.ftdd2.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
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
    @Resource
    private ActionTableMapper actionTableMapper;

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

    @Override
    public int deliver(Integer jobId) {
        //取得当前用户id
        Map<String,Object>map=  ThreadLocalUtil.get();
        String userId= (String) map.get("id");
        //查询是否投递过
        LambdaQueryWrapper<ActionTable> wrapper= new LambdaQueryWrapper<>();
        wrapper.eq(ActionTable::getUserId,userId)
                .eq(ActionTable::getJobId,jobId);
        ActionTable table=actionTableMapper.selectOne(wrapper);
       if(table==null){
           ActionTable actionTable=new ActionTable();
           actionTable.setJobId(jobId);
           actionTable.setUserId(userId);
           actionTable.setDelivered("1");
           actionTable.setBrowsed("1");
           actionTableMapper.insert(actionTable);
           return 1;
       }
        //投递过则取消投递
        actionTableMapper.deleteById(table);
        return 0;
    }

    @Override
    public Map<String, Object> getDeliverList(int pageNo, int pageSize) {
        Map<String, Object> map = ThreadLocalUtil.get();
        String id = (String) map.get("id");

        List<Integer> jobIdList = actionTableMapper.getDeliverList(pageNo, pageSize, id);
        List<JobTable> jobList = new ArrayList<>();
        if (!jobIdList.isEmpty()) {
            LambdaQueryWrapper<JobTable> jobWrapper = new LambdaQueryWrapper<>();
            jobWrapper.in(JobTable::getId, jobIdList);
            jobList = jobTableMapper.selectList(jobWrapper);
        }

        return Map.of("total", jobList.size(), "rows", jobList);
    }

    @Override
    public List<Map<String, Object>> getHotCompany() {
        return jobTableMapper.getHotCompany();
    }

    @Override
    public Long countUnique() {
        return jobTableMapper.countUnique();
    }
}
