package com.meow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meow.domain.exams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ExamsDao  extends BaseMapper<exams> {

    @ResultMap("name2")
    @Select("select * from exams;")
    List<exams> all ();


    @ResultMap("name2")
    int add(exams exams);


    @Select("select id from exams where exam_name =#{examName};")
    String getId(String examName);

    void creat(String id);
}
