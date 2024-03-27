package com.ftdd2.mapper;

import com.ftdd2.domain.entity.ActionTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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


}
