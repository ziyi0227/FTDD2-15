package com.ftdd2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ftdd2.domain.DTO.UserDTO;
import com.ftdd2.domain.DTO.UserInfoDTO;
import com.ftdd2.domain.entity.*;
import com.ftdd2.mapper.*;
import com.ftdd2.domain.entity.ActionTable;
import com.ftdd2.domain.entity.Favor;
import com.ftdd2.domain.entity.JobTable;
import com.ftdd2.mapper.ActionTableMapper;
import com.ftdd2.mapper.FavorMapper;
import com.ftdd2.mapper.JobTableMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ftdd2.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ftdd2.utils.JwtUtil;
import com.ftdd2.utils.Md5Util;
import com.ftdd2.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.ftdd2.utils.ThreadLocalUtil.get;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, User> implements IUsersService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UsersMapper userMapper;

    @Resource
    private ActionTableMapper actionTableMapper;
    @Resource
    private FavorMapper favorMapper;
    @Resource
    private JobTableMapper jobTableMapper;
    @Resource
    private ResumeMapper resumeMapper;

    @Resource
    private UserJobMapper userJobMapper;

    @Override
    public Map<String, Object> login(User user) {
        if (StringUtils.isAnyBlank(user.getUsername(), user.getPassword())) {
            return null;
        }
        // 查询用户是否存在以及密码
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        String encryptPassword = Md5Util.getMD5String(user.getPassword());
        wrapper.eq("username", user.getUsername())
                .eq("password", encryptPassword);
        User loginUser = this.baseMapper.selectOne(wrapper);
        if (loginUser != null) {
            Map<String, Object> claim = new HashMap<>();
            claim.put("id", loginUser.getId());
            claim.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claim);
            //存入redis
            redisTemplate.opsForValue().set(token, token, 300, TimeUnit.MINUTES);

            //返回数据
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            return data;
        }
        return null;


    }

    @Override
    public User register(UserDTO userDTO) {
        if (StringUtils.isAnyBlank(userDTO.getUsername(), userDTO.getPassword(), userDTO.getRePassword())) {
            return null;
        }
        // 密码与二次密码
        if (!userDTO.getPassword().equals(userDTO.getRePassword())) {
            return null;
        }

        // 用户名重复
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userDTO.getUsername());
        Long count = this.baseMapper.selectCount(wrapper);
        if (count > 0) {
            return null;
        }

        //加密
        userDTO.setPassword(Md5Util.getMD5String(userDTO.getPassword()));
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        baseMapper.insert(user);
        return user;
    }

    @Override
    public Map<String, Object> getFavorList(int pageNo, int pageSize) {
        Map<String, Object> map = get();
        String id = (String) map.get("id");
        PageHelper.startPage(pageNo, pageSize);
        Page<JobTable> page = userMapper.getFavorList(pageNo, pageSize, id);
        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getResult());
        return data;
    }

    @Override
    public List<JobTable> getAllFavor() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String id = (String) map.get("id");

        LambdaQueryWrapper<Favor> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favor::getUserId, id);
        List<Favor> list = favorMapper.selectList(wrapper);

        List<Integer> jobIdList = list.stream()
                                      .map(favor -> {return favor.getJobId();})
                                      .collect(Collectors.toList());

        List<JobTable> jobList = new ArrayList<>();
        if(!jobIdList.isEmpty()){
            LambdaQueryWrapper<JobTable> jobWrapper = new LambdaQueryWrapper<>();
            jobWrapper.in(JobTable::getId, jobIdList);
            jobList = jobTableMapper.selectList(jobWrapper);
        }
        return jobList;
    }


    @Override
    public Map<String, Object> getUserInfo(String token) {
        String obj = (String) redisTemplate.opsForValue().get(token);
        if (obj != null) {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            String username = (String) claims.get("username");
            String id = (String) claims.get("id");
            User user = userMapper.selectById(id);


            Map<String, Object> data = new HashMap<>();
            data.put("name", username);
            data.put("sex", user.getSex());
            data.put("live_city",user.getLiveCity());
            data.put("avatar",user.getAvatar());


//            List<String> roleList = this.baseMapper.getRoleNameByUserId(id);
            //角色
//            data.put("roles", roleList);
            return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }

    @Override
    public void updateInfo(UserInfoDTO userInfoDTO) {
        User user = new User();
        Map<String,Object>map=ThreadLocalUtil.get();
        String id = (String) map.get("id");
        user.setId(id);
        BeanUtils.copyProperties(userInfoDTO,user);
        //mp中null字段不会进行更新
        userMapper.updateById(user);
    }

    @Override
    public void updateAvatar(String filePath) {
        Map<String,Object>map=ThreadLocalUtil.get();
        String id = (String) map.get("id");
        User user=userMapper.selectById(id);
        user.setAvatar(filePath);
        userMapper.updateById(user);
    }

    @Override
    public Map<String, Object> getActionList() {
        //取得id
      Map<String,Object>map = ThreadLocalUtil.get();
        String id=(String)map.get("id");
        LambdaQueryWrapper<ActionTable> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(ActionTable::getUserId,id)
                .eq(ActionTable::getBrowsed,1);
        //简历被浏览次数
        Long browsedCount = actionTableMapper.selectCount(wrapper);
        wrapper.clear();
        //收藏job数量
        LambdaQueryWrapper<Favor> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Favor::getUserId,id);
        Long favorCount = favorMapper.selectCount(queryWrapper);
        //投递数量
        wrapper.eq(ActionTable::getUserId,id)
                .eq(ActionTable::getDelivered,1);
        Long deliveredCount = actionTableMapper.selectCount(wrapper);
        wrapper.clear();
        //被多少hr满意
        wrapper.eq(ActionTable::getUserId, id)
                .eq(ActionTable::getSatisfied, 1);
        Long satisfiedCount = actionTableMapper.selectCount(wrapper);
        //包装
        Map<String, Object> result = new HashMap<>();
        result.put("browsedCount", browsedCount);
        result.put("collectedCount", favorCount);
        result.put("deliveredCount", deliveredCount);
        result.put("satisfiedCount", satisfiedCount);

        return result;
    }

    @Override
    public Map<String, Object> getResumeList(Long pageNo, Long pageSize) {
        LambdaQueryWrapper<Resume> wrapper = new LambdaQueryWrapper<>();
        //获取当前hr id
        Map<String, Object> map = get();
        String id = (String) map.get("id");
        //获取hr发布过的招聘信息
        List<Long> jobIdList=userJobMapper.getJobList(id);
        //根据招聘信息id去Action表去找到简历id
        LambdaQueryWrapper<ActionTable> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.in(ActionTable::getJobId,jobIdList)
                .eq(ActionTable::getDelivered,"1");
        List<ActionTable> actionTables = actionTableMapper.selectList(queryWrapper);
        //根据简历id去resume查找
        List<String> userIdList=actionTables.stream().map(ActionTable::getUserId).toList();
//        List<Resume>resumeList = resumeMapper.selectByIds(userIdList);
        //分页
        PageHelper.startPage(pageNo.intValue(),pageSize.intValue());
        Page<Resume> page =  resumeMapper.selectByIds(userIdList);
        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getResult());
        return data;
    }

    @Override
    public void insertResume(Resume resume) {
      Map<String,Object> map= ThreadLocalUtil.get();
        String id= (String) map.get("id");
        resume.setUserId(id);
        resumeMapper.insert(resume);
    }

    @Override
    public Map<String, Object> getActionListHr() {
        //取得id
        Map<String,Object>map = ThreadLocalUtil.get();
        String id=(String)map.get("id");
        LambdaQueryWrapper<ActionTable> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(ActionTable::getUserId,id)
                .eq(ActionTable::getSatisfied,1);

        //满意的简历数量
        Long satisfiedCount = actionTableMapper.selectCount(wrapper);

        //发布招聘信息数量
        LambdaQueryWrapper<UserJob> jobWrapper=new LambdaQueryWrapper<>();
        jobWrapper.eq(UserJob::getUserId,id);
        Long jobCount = userJobMapper.selectCount(jobWrapper);
        //投递人数
        wrapper.clear();
        //先取得自己发布的简历id
        List<Long> jobIdList=userJobMapper.getJobList(id);
        //根据招聘信息id去Action表去找到简历id
        LambdaQueryWrapper<ActionTable> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.in(ActionTable::getJobId,jobIdList)
                .eq(ActionTable::getDelivered,"1");
        List<ActionTable> actionTables = actionTableMapper.selectList(queryWrapper);
        int deliveredCount = actionTables.size();
        //包装
        Map<String, Object> result = new HashMap<>();
        result.put("satisfiedCount", satisfiedCount);
        result.put("jobCount", jobCount);
        result.put("deliveredCount", deliveredCount);
        return result;
    }

    @Override
    public Map<String, Object> getJobList(int pageNo, int pageSize) {
        Map<String, Object> map = ThreadLocalUtil.get();
        String id = (String) map.get("id");
        List<Long> jobIdList=userJobMapper.getJobList(id);
        PageHelper.startPage(pageNo,pageSize);
        LambdaQueryWrapper<JobTable> wrapper=new LambdaQueryWrapper<>();
        wrapper.in(JobTable::getId,jobIdList);
        List<JobTable> jobList = jobTableMapper.selectList(wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("total", jobList.size());
        data.put("rows", jobList);
        return data;
    }
}
