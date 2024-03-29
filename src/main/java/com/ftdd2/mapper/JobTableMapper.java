package com.ftdd2.mapper;

import com.ftdd2.domain.entity.JobTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 招聘信息表 Mapper 接口
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
public interface JobTableMapper extends BaseMapper<JobTable> {


    @MapKey("company")
    List<Map<String, Object>> getHotCompany();

    @Select("select count(*) from action_table join job_table on action_table.job_id = job_table.id where action_table.delivered = 1")
    Long countUnique();

    List<String> getHotJob(LocalDateTime nowTime);
}
