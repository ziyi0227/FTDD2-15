package com.ftdd2.mapper;

import com.ftdd2.domain.entity.Resume;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * <p>
 * 简历表 Mapper 接口
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
public interface ResumeMapper extends BaseMapper<Resume> {

    Page<Resume> selectByIds(List<String> userIdList);
}
