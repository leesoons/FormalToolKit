package com.lee.plcanalysis.dao;

import com.lee.plcanalysis.pojo.Project;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectMapper {
    @Select("select * from project where userId = #{userId}")
    List<Project> findByUseId(Long id);

    @Select("select * from project where id = #{id}")
    Project findById(Long id);

    @Insert("insert into project(description, name, userId) values (#{description}, #{name}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(Project project);

    @Delete("delete from project where id = #{id}")
    int deleteById(Long id);
}
