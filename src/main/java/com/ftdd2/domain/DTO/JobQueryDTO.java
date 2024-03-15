package com.ftdd2.domain.DTO;


import lombok.Data;

@Data
public class JobQueryDTO {
    private String jdTitle;
    private String company;
    private String jdSubType;
    private Integer minSalary;
    private Integer maxSalary;

    private Long pageNo;
    private Long pageSize;
}
