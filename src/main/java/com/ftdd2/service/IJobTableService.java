package com.ftdd2.service;

import com.ftdd2.common.vo.Result;
import com.ftdd2.domain.entity.JobTable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 招聘信息表 服务类
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
public interface IJobTableService extends IService<JobTable> {

    void addJobTable(JobTable jobTable, String token);

    void updateJobTable(JobTable jobTable);

    List<JobTable> listById(String token);

    int deliver(Integer jobId);

    Map<String, Object> getDeliverList(int pageNo, int pageSize);

    List<Map<String, Object>> getHotCompany();

    Long countUnique();


    List<String> getNowTitle(LocalDateTime now);
}
