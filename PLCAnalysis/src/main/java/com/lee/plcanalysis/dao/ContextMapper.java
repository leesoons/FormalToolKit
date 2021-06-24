package com.lee.plcanalysis.dao;

import com.lee.plcanalysis.pojo.Context;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ContextMapper {
    @Select("select * from context where project_id = #{projectId}")
    @Results(id = "contextMapper", value = {
            @Result(id = true, column = "project_id", property = "projectId"),
            @Result(column = "code", property = "code"),
            @Result(column = "priority_array", property = "priorityArray"),
            @Result(column = "description", property = "description")
    })
    Context findByProjectId(Long projectId);

    @Insert("insert into context (project_id, code, priority_array, description)" +
            "values (#{projectId}, #{code}, #{priorityArray}, #{description})")
    @ResultMap("contextMapper")
    int save(Context context);

    @Update("update context set code = #{code}, priority_array = #{priorityArray}, description = #{description} where project_id = #{projectId}")
    @ResultMap("contextMapper")
    int updateContext(Context context);
}
