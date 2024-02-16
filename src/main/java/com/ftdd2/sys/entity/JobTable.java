package com.ftdd2.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 招聘信息表
 * </p>
 *
 * @author ftdd2
 * @since 2024-02-16
 */
@TableName("job_table")
@ApiModel(value = "JobTable对象", description = "招聘信息表")
public class JobTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String company;

    private String salary;

    private String description;

    private String title;

    @ApiModelProperty("发布者id")
    private Integer hrId;

    private LocalDate createTime;

    private LocalDate updateTime;

    private Integer salaryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getHrId() {
        return hrId;
    }

    public void setHrId(Integer hrId) {
        this.hrId = hrId;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    @Override
    public String toString() {
        return "JobTable{" +
            "id = " + id +
            ", company = " + company +
            ", salary = " + salary +
            ", description = " + description +
            ", title = " + title +
            ", hrId = " + hrId +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", salaryId = " + salaryId +
        "}";
    }
}
