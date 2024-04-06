package com.ftdd2.service.impl;

import com.ftdd2.domain.entity.MenuTable;
import com.ftdd2.mapper.MenuTableMapper;
import com.ftdd2.service.IMenuTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
@Service
public class MenuTableServiceImpl extends ServiceImpl<MenuTableMapper, MenuTable> implements IMenuTableService {

    @Override
    public List<MenuTable> getMenuListByUserId(String userId) {
            // 一级菜单
            List<MenuTable> menuList = this.getBaseMapper().getMenuListByUserId(userId, 0);
            // 子菜单
            setMenuTableChildrenByUserId(userId, menuList);
            return menuList;
        }

        private void setMenuTableChildrenByUserId(String userId, List<MenuTable> menuList) {
            if (menuList != null) {
                for (MenuTable menu : menuList) {
                    List<MenuTable> subMenuTableList = this.getBaseMapper().getMenuListByUserId(userId, menu.getMenuId());
                    menu.setChildren(subMenuTableList);
                    // 递归
                    setMenuTableChildrenByUserId(userId,subMenuTableList);
                }
            }
        }

    }
