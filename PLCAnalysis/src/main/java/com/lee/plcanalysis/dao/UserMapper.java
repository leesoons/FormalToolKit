package com.lee.plcanalysis.dao;

import com.lee.plcanalysis.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findUserByUsername(String username);

    @Select("select * from user where id = #{id}")
    User findUserById(Long id);

    @Insert("Insert into user (username, password) values (#{username},#{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(User user);
}
