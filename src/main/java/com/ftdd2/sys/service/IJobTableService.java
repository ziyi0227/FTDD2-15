package com.ftdd2.sys.service;

import com.ftdd2.common.vo.PageResult;
import com.ftdd2.sys.Pojo.JobTableQuery;
import com.ftdd2.sys.entity.JobTable;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 招聘信息表 服务类
 * </p>
 *
 * @author ftdd2
 * @since 2024-02-16
 */
public interface IJobTableService extends IService<JobTable> {

    void add(JobTable jobTable);

    PageResult pageQuery(JobTableQuery jobTableQuery);
}
