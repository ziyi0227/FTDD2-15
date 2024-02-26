package com.ftdd2.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ftdd2.sys.entity.Users;
import com.ftdd2.sys.mapper.UsersMapper;
import com.ftdd2.sys.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ftdd2.utils.JwtUtil;
import com.ftdd2.utils.Md5Util;
import com.ftdd2.utils.ThreadLocalUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.security.auth.login.AccountException;
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
    private UsersMapper usersMapper;

    @Override
    public Users login(Users user) {
        //获取用户名密码
        String username = user.getUsername();
        String password = user.getPassword();


        Users users = usersMapper.getByUsername(username);
        if(users == null){
            //用户名不存在
            return null;
        }
        // TODO 密码比对

        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //校验密码
        if(!password.equals(users.getPassword())){
            return null;
        }
        return users;

    }
    public Users findByUsername(String username){
        Users user=usersMapper.getByUsername(username);
        return user;
    }

    @Override
    public void register(String username, String password) {
        //加密
        String md5String = Md5Util.getMD5String(password);
        usersMapper.add(username,md5String);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object>map= ThreadLocalUtil.get();
        Integer id=(Integer) map.get("id");
        usersMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }

    @Override
    public void update(Users user) {
        usersMapper.update(user);
    }
}
