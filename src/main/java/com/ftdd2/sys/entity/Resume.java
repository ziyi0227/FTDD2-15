package com.ftdd2.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 简历表
 * </p>
 *
 * @author ftdd2
 * @since 2024-02-16
 */
@ApiModel(value = "Resume对象", description = "简历表")
public class Resume implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uid;

    private String name;

    private String address;

    private String education;

    private String exTitle;

    private String exSalary;

    private String description;

    private String photo;

    @ApiModelProperty("1发布，0草稿")
    private Integer status;

    @ApiModelProperty("选择模板id")
    private Integer did;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExTitle() {
        return exTitle;
    }

    public void setExTitle(String exTitle) {
        this.exTitle = exTitle;
    }

    public String getExSalary() {
        return exSalary;
    }

    public void setExSalary(String exSalary) {
        this.exSalary = exSalary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    @Override
    public String toString() {
        return "Resume{" +
            "id = " + id +
            ", uid = " + uid +
            ", name = " + name +
            ", address = " + address +
            ", education = " + education +
            ", exTitle = " + exTitle +
            ", exSalary = " + exSalary +
            ", description = " + description +
            ", photo = " + photo +
            ", status = " + status +
            ", did = " + did +
        "}";
    }
}
