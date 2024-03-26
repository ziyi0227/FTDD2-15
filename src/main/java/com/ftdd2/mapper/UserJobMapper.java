package com.ftdd2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ftdd2.domain.entity.UserJob;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ziyi
 * @since 2024-03-18
 */
public interface UserJobMapper extends BaseMapper<UserJob> {


    @Select("select job_id from ft_demo.user_job where user_id=#{id}")
    List<Long> getJobList(String id);
}
