package com.ftdd2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.Year;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 简历表
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("resume")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resume implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

    private String major;

    private String sex;

    private String name;

    private String phone;

    private String age;

    private String liveCity;

    private String degree;

    private String desireJdType;

    private String desireJdSalaryId;

    private String desireJdIndustry;

    private String desireCity;

    private String experience;

    private String startWorkDate;

    private String currentSalaryId;

    private String curIndustry;

    private String curJdType;


}
