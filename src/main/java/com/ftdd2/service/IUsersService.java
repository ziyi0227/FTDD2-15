package com.ftdd2.service;

import com.ftdd2.domain.DTO.UserDTO;
import com.ftdd2.domain.DTO.UserInfoDTO;
import com.ftdd2.domain.entity.JobTable;
import com.ftdd2.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
public interface IUsersService extends IService<User> {

    Map<String,Object> login(User user);

    User register(UserDTO userDTO);


    Map<String, Object> getFavorList(int pageNo, int pageSize);

    Map<String, Object> getUserInfo(String token);

    void logout(String token);

    void updateInfo(UserInfoDTO userInfoDTO);

    void updateAvatar(String filePath);
}
