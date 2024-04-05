package com.ftdd2.mapper;

import com.ftdd2.domain.entity.JobTable;
import com.ftdd2.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */

@Mapper
public interface UsersMapper extends BaseMapper<User> {


    Page<JobTable> getFavorList(int pageNo, int pageSize, String id);

    List<JobTable> getAllFavor(String id);
}
