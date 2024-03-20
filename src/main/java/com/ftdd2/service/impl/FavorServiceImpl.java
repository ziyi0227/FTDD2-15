package com.ftdd2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ftdd2.domain.entity.Favor;
import com.ftdd2.mapper.FavorMapper;
import com.ftdd2.service.IFavorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ftdd2.utils.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
@Service
public class FavorServiceImpl extends ServiceImpl<FavorMapper, Favor> implements IFavorService {

    @Override
    public int setFavor(String jdNo) {
        Map<String,Object> map= ThreadLocalUtil.get();
        String id= (String) map.get("id");
        //查询是否收藏过
        LambdaQueryWrapper<Favor> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Favor::getUserId,id);
        Favor favor = this.baseMapper.selectOne(wrapper);

        //无，则收藏 返回1
        if(favor==null){
            Favor newFavor=new Favor();
            newFavor.setJdNo(jdNo);
            newFavor.setUserId(id);

            this.baseMapper.insert(newFavor);
            return 1;
        }
        //有，则取消 返回0
        this.baseMapper.deleteById(favor);
        return 0;

    }
}
