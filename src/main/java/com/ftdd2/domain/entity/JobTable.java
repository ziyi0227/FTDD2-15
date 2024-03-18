package com.ftdd2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 招聘信息表
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("job_table")
public class JobTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 职位代码
     */
    private String jdNo;

    /**
     * 职位名称
     */
    private String jdTitle;

    /**
     * 公司名称
     */
    private String company;

    /**
     * 工作城市
     */
    private String city;

    /**
     * 职位类型
     */
    private String jdSubType;

    /**
     * 招聘人数
     */
    private Long requireNums;

    /**
     * 最低薪资
     */
    private Integer minSalary;

    /**
     * 最高薪资
     */
    private Integer maxSalary;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate startDate;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate endDate;

    /**
     * 是否出差,0不出差，1出差
     */
    private Integer isTravel;

    /**
     * 最低工作年限
     */
    private String minYears;

    /**
     * 最低学历
     */
    private String minEducation;

    /**
     * 技能要求
     */
    private String titleSkill;

    /**
     * 知识要求
     */
    private String knowledge;

    /**
     * 素质要求
     */
    private String quality;


}
