package com.meow.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meow.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {
    //加个空格

    List<User> selectByPageAndCondition(@Param("index") int index, @Param("size")int size, @Param("user") User user);



    int selectTotalCountAndCondition(@Param("user")User user);

    @Select("select count(*) from tb_user where class_id =#{classId};")
    int selectByclassId(String classId);

    @Update("update tb_user set exam = concat(exam,#{newexam}) where id=#{id};")
    int addexam(@Param("newexam") String newexam ,@Param("id") String id);

}
