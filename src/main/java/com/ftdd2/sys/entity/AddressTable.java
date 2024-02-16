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
@TableName("address_table")
@ApiModel(value = "AddressTable对象", description = "")
public class AddressTable implements Serializable {

    private static final long serialVersionUID = 1L;

    private String address;

    private Integer addrId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAddrId() {
        return addrId;
    }

    public void setAddrId(Integer addrId) {
        this.addrId = addrId;
    }

    @Override
    public String toString() {
        return "AddressTable{" +
            "address = " + address +
            ", addrId = " + addrId +
        "}";
    }
}
