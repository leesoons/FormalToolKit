package com.lee.plcanalysis.dao;

import com.lee.plcanalysis.pojo.Requirement;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RequirementMapper {
    @Select("select * from requirement where project = #{project} order by id")
    @Results(id = "requirementMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "text", property = "text"),
            @Result(column = "description", property = "description"),
            @Result(column = "state", property = "state"),
            @Result(column = "project", property = "project"),
            @Result(column = "counter_example", property = "counterExample")
    })
    List<Requirement> findByProjectOrderById(Long project);

    @Select("select * from requirement where id = #{id}")
    @ResultMap("requirementMapper")
    Requirement findById(Long id);

    @Insert("insert into requirement (text, description, state, project, counter_example)" +
    "values (#{text}, #{description},#{state},#{project},#{counterExample})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(Requirement requirement);

    @Update("update requirement set text = #{text}, description = #{description}, state = #{state}, project = #{project}, counter_example = #{counterExample} where id = #{id}")
    int updateRequirement(Requirement requirement);

    @Delete("delete from requirement where id = #{id}")
    int deleteById(Long id);
}
