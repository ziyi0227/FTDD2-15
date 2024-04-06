package com.ftdd2.service;

import com.ftdd2.domain.entity.MenuTable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
public interface IMenuTableService extends IService<MenuTable> {

    List<MenuTable> getMenuListByUserId(String id);
}
