package com.ftdd2.sys.mapper;

import com.ftdd2.sys.Pojo.JobTableQuery;
import com.ftdd2.sys.entity.JobTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 招聘信息表 Mapper 接口
 * </p>
 *
 * @author ftdd2
 * @since 2024-02-16
 */
@Mapper
public interface
JobTableMapper extends BaseMapper<JobTable> {

@Select("select count(id) from ft_demo.job_table where title_id=#{Tid}")
    Integer countCategory(Long Tid);



    Page<JobTable> pageQuery(JobTableQuery jobTableQuery);
}
