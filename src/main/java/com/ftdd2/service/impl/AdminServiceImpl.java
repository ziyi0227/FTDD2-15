package com.ftdd2.service.impl;

import com.ftdd2.domain.entity.Admin;
import com.ftdd2.mapper.AdminMapper;
import com.ftdd2.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
