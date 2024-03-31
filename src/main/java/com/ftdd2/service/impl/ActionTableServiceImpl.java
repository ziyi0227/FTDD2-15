package com.ftdd2.service.impl;

import com.ftdd2.domain.entity.ActionTable;
import com.ftdd2.mapper.ActionTableMapper;
import com.ftdd2.service.IActionTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
@Service
public class ActionTableServiceImpl extends ServiceImpl<ActionTableMapper, ActionTable> implements IActionTableService {

    @Override
    public Map<String, Long> getTitleCount(String jobTitle) {
        // 计算该职位的投递量和收藏量
        Map<String, Long> data = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        // 获取当前月份
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        for (int i = 1; i <= currentMonth; i++) {
            // 获取当前月份的投递量
            Long deliverCount = this.baseMapper.getDeliverCount(jobTitle, i);
            // 获取当前月份的收藏量
            Long collectCount = this.baseMapper.getCollectCount(jobTitle, i);
            Long totalCount = deliverCount + collectCount;
            data.put(i + "月", totalCount);
        }
        return data;
    }

}
