package com.ftdd2.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ftdd2.domain.entity.MenuTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
public interface MenuTableMapper extends BaseMapper<MenuTable> {
    public List<MenuTable> getMenuListByUserId(@Param("userId") String userId,
                                               @Param("pid") Integer pid);

}
