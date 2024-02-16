package com.ftdd2.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 *
 * </p>
 *
 * @author ftdd2
 * @since 2024-02-16
 */
@TableName("salary_table")
@ApiModel(value = "SalaryTable对象", description = "")
public class SalaryTable implements Serializable {

    private static final long serialVersionUID = 1L;

    private String category;

    private Integer salaryId;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    @Override
    public String toString() {
        return "SalaryTable{" +
            "category = " + category +
            ", salaryId = " + salaryId +
        "}";
    }
}
