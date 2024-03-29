package com.ftdd2.mapper;

import com.ftdd2.domain.entity.ActionTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
public interface ActionTableMapper extends BaseMapper<ActionTable> {


    List<Integer> getDeliverList(int pageNo, int pageSize, String id);


    List<String> getNowTitle(LocalDateTime now);


    @Select("select count(*) from action_table,job_table " +
            "where job_table.jd_title = #{jobTitle} " +
            "and month(updateTime) = #{currentMonth} " +
            "and action_table.job_id = job_table.id " +
            "and action_table.delivered = 1")
    Long getDeliverCount(String jobTitle, int currentMonth);


    @Select("select count(*) from favor,job_table " +
            "where job_table.jd_title = #{jobTitle} " +
            "and month(updateTime) = #{currentMonth} " +
            "and favor.job_id = job_table.id " )
    Long getCollectCount(String jobTitle, int currentMonth);
}
