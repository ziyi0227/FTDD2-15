package com.ftdd2.mapper;

import com.ftdd2.domain.entity.ActionTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
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
            "where job_table.jd_sub_type = #{jd_sub_type} " +
            "and month(action_table.update_time) = #{currentMonth} " +
            "and action_table.job_id = job_table.id " +
            "and action_table.delivered = 1")
    Long getDeliverCount(String jd_sub_type, int currentMonth);


    @Select("select count(*) from favor,job_table " +
            "where job_table.jd_sub_type = #{jd_sub_type} " +
            "and month(favor.update_time) = #{currentMonth} " +
            "and favor.job_id = job_table.id " )
    Long getCollectCount(String jd_sub_type, int currentMonth);


    @MapKey("major")
    List<Map<String, Long>>  getNowMajor(LocalDateTime time);


    @MapKey("major")
    Map<String, Long> getHotMajor(LocalDateTime day);
}
