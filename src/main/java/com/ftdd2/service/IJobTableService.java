package com.ftdd2.service;

import com.ftdd2.domain.entity.JobTable;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 招聘信息表 服务类
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
public interface IJobTableService extends IService<JobTable> {

    void addJobTable(JobTable jobTable);

    void updateJobTable(JobTable jobTable);
}
