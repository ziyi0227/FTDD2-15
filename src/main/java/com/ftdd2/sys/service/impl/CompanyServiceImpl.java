package com.ftdd2.sys.service.impl;

import com.ftdd2.sys.entity.Company;
import com.ftdd2.sys.mapper.CompanyMapper;
import com.ftdd2.sys.service.ICompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 企业表 服务实现类
 * </p>
 *
 * @author ftdd2
 * @since 2024-02-16
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

}
