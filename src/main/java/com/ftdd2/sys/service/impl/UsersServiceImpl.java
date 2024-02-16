package com.ftdd2.sys.service.impl;

import com.ftdd2.sys.entity.Users;
import com.ftdd2.sys.mapper.UsersMapper;
import com.ftdd2.sys.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
