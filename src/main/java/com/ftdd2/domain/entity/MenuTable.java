package com.ftdd2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("menu_table")
public class MenuTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    private String component;

    private String path;

    private String redirect;

    private String name;

    private String title;

    private String icon;

    private Integer parentId;

    private String isLeaf;

    private Boolean hidden;

    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MenuTable> children;

    @TableField(exist = false)
    private Map<String,Object> meta;
    public Map<String,Object> getMeta(){
        meta = new HashMap<>();
        meta.put("title",title);
        meta.put("icon",icon);
        return meta;
    }


}
