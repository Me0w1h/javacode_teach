package com.meow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meow.domain.Classes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassesDao extends BaseMapper<Classes> {

    @ResultMap("name2")
    @Select("select * from classes;")
    List<Classes> all();


    @ResultMap("name2")
    @Select("select * from classes where class_id =#{id};")
    Classes selectById(String id);


    @Insert("insert into classes (class_id,class_name,school,pk,sk) values (#{classes.classId},#{classes.className},#{classes.school},#{classes.pk},#{classes.sk});")
    int add(@Param("classes") Classes classes);
}
