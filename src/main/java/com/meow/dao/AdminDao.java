package com.meow.dao;

import com.meow.domain.Admin;
import com.meow.domain.Classes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminDao {


    @Select("select * from admin where id =#{id};")
    Admin login(@Param("id") String id);
}
