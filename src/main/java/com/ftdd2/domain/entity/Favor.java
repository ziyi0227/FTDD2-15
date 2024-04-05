package com.ftdd2.domain.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 收藏表
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("favor")
public class Favor implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String jobId;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private DateTime updateTime;

}
