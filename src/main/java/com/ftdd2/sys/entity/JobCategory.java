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
@TableName("job_category")
@ApiModel(value = "JobCategory对象", description = "")
public class JobCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private Integer tid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "JobCategory{" +
            "title = " + title +
            ", tid = " + tid +
        "}";
    }
}
