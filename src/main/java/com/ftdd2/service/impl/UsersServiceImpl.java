package com.ftdd2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ftdd2.domain.DTO.UserDTO;
import com.ftdd2.domain.DTO.UserInfoDTO;
import com.ftdd2.domain.entity.JobTable;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ftdd2.domain.entity.User;
import com.ftdd2.mapper.UsersMapper;
import com.ftdd2.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ftdd2.utils.JwtUtil;
import com.ftdd2.utils.Md5Util;
import com.ftdd2.utils.ThreadLocalUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
        Map<String, Object> map = ThreadLocalUtil.get();
        String id = (String) map.get("id");
        PageHelper.startPage(pageNo, pageSize);
        Page<JobTable> page = userMapper.getFavorList(pageNo, pageSize, id);
        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getResult());
        return data;
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


}
