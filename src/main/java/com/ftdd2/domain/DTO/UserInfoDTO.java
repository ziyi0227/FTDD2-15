package com.ftdd2.domain.DTO;


import lombok.Data;


// TODO 后续根据个人信息界面进行扩展
@Data
public class UserInfoDTO {
    private String name;
    private String liveCity;
    private Integer sex;
    private String avatar;
}
