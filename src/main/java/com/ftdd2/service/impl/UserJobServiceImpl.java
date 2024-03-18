package com.ftdd2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ftdd2.domain.entity.UserJob;
import com.ftdd2.domain.entity.UserRole;
import com.ftdd2.mapper.UserJobMapper;
import com.ftdd2.mapper.UserRoleMapper;
import com.ftdd2.service.IUserJobService;
import com.ftdd2.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
@Service
public class UserJobServiceImpl extends ServiceImpl<UserJobMapper, UserJob> implements IUserJobService {

}
