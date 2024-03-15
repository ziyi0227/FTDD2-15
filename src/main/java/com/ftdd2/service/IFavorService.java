package com.ftdd2.service;

import com.ftdd2.domain.entity.Favor;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 收藏表 服务类
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
public interface IFavorService extends IService<Favor> {

    int setFavor(String jdNo);
}
