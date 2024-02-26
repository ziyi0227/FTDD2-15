package com.ftdd2.sys.Pojo;


import lombok.Data;

@Data
public class JobCategoryQuery {


    //页码
    private int page;

    //每页记录数
    private int pageSize;
    private String title;
    private Integer Tid;

}
