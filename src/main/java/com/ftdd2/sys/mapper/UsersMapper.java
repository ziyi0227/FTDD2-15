package com.ftdd2.sys.mapper;

import com.ftdd2.sys.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ftdd2
 * @since 2024-02-16
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

    @Select(
            "select * from ft_demo.users where username=#{username}"
    )

    Users getByUsername(String username);

    @Insert("INSERT INTO users (username,password,login_time) " +
            "values (#{username},#{md5String},now())")
    void add(String username, String md5String);
}
