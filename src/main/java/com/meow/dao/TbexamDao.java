package com.meow.dao;

import com.meow.domain.Tbexam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TbexamDao {


    @ResultMap("name2")
    @Select("select * from tb_${id};")
    List<Tbexam> Select(String id);


    int add(@Param("exam") String exam, @Param("tbexam") Tbexam tbexam);

    @Update("update tb_${exam} set score = #{tbexam.score},count= #{tbexam.count} where class_id = #{tbexam.classId};")
    int commit(@Param("exam") String exam, @Param("tbexam") Tbexam tbexam);
}
