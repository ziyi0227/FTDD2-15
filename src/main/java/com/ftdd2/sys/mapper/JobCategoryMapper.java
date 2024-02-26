package com.ftdd2.sys.mapper;

import cn.hutool.db.Page;
import com.ftdd2.sys.Pojo.JobCategoryQuery;
import com.ftdd2.sys.entity.JobCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ftdd2
 * @since 2024-02-16
 */
@Mapper
public interface JobCategoryMapper extends BaseMapper<JobCategory> {


 //TODO 后续感觉会改为动态sql
    @Select("select * from job_category where title=#{title}" +
            " order by Tid")
    List<JobCategory> list(String title);

    //TODO 93-100行重新执行一下 sql文件...我以为mysql主键自带自增
    @Insert("insert into job_category (title) values (#{title})")
    void insert(String title);




   @Delete("delete from job_category where Tid=#{Tid}")
   void deleteById(Long Tid);

    void update(JobCategory jobCategory);
}
