package com.ftdd2.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ftdd2.sys.entity.Users;
import com.ftdd2.sys.mapper.UsersMapper;
import com.ftdd2.sys.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ftdd2.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ftdd2
 * @since 2024-02-16
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Map<String, Object> login(Users user) {
        //根据用户名查询
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getUsername,user.getUsername());
        Users loginUser = this.baseMapper.selectOne(wrapper);
        //查询结果不为空，并且密码与传入密码匹配，输出token,并将token存入redis
        if (loginUser != null && passwordEncoder.matches(user.getPassword(),loginUser.getPassword())){
            //暂时用UUID，后期改为JWT
            // String key = "user" + UUID.randomUUID();

            //存入redis
            loginUser.setPassword(null);
            // redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);

            //创建jwt
            String token = jwtUtil.createToken(loginUser);

            //返回token
            Map<String,Object> data = new HashMap<>();
            data.put("token",token);
            return data;
        }
        return null;
    }
}
