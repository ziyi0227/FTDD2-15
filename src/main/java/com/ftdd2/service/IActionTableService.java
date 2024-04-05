package com.ftdd2.service;

import com.ftdd2.domain.entity.ActionTable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
public interface IActionTableService extends IService<ActionTable> {

    Map<String,Long> getTitleCount(String jd_sub_type);
}
