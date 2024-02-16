package com.ftdd2.sys.entity;

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
@ApiModel(value = "Favor对象", description = "")
public class Favor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer uid;

    private Integer tid;

    private Integer orders;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Favor{" +
            "uid = " + uid +
            ", tid = " + tid +
            ", orders = " + orders +
        "}";
    }
}
