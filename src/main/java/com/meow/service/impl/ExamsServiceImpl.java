package com.meow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meow.dao.ExamsDao;
import com.meow.dao.UserDao;
import com.meow.domain.User;
import com.meow.domain.exams;
import com.meow.service.ExamsService;
import com.meow.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamsServiceImpl  extends ServiceImpl<ExamsDao, exams> implements ExamsService {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;



    @Override
    public List<exams> all() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ExamsDao mapper = sqlSession.getMapper(ExamsDao.class);

        List<exams> all = mapper.all();

        sqlSession.close();
        return all;
    }

//    public boolean add(exams exams){
//
//
//        System.out.println("=============-------------------======================");
//        int add = mapper.add(exams);
//        System.out.println("-------------------======================");
//        if (add>0){
//            System.out.println("-----------------");
//            String id = mapper.getId(exams.getExamName());
//            System.out.println(id);
//            mapper.creat(id);
//        }else {
//            System.out.println("==============");
//        }
//
//        sqlSession.close();
//
//        return (add>0)?true:false;
//
//
//    }

    @Override
    public void getId(String examName) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ExamsDao mapper = sqlSession.getMapper(ExamsDao.class);
        String id = mapper.getId(examName);
        mapper.creat("tb_"+id);


        sqlSession.close();
    }

}
