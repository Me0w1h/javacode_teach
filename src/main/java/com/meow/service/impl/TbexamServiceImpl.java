package com.meow.service.impl;

import com.meow.dao.ExamsDao;
import com.meow.dao.TbexamDao;
import com.meow.domain.Tbexam;
import com.meow.service.TbexamService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TbexamServiceImpl implements TbexamService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public List<Tbexam> select(String id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TbexamDao mapper = sqlSession.getMapper(TbexamDao.class);

        List<Tbexam> select = mapper.Select(id);

        sqlSession.close();
        return select;

    }

    @Override
    public boolean add(String exam, Tbexam tbexam) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TbexamDao mapper = sqlSession.getMapper(TbexamDao.class);

        int add = mapper.add(exam, tbexam);
        sqlSession.close();
        return  add>0?true:false;
    }

    @Override
    public boolean commit(String exam, Tbexam tbexam) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TbexamDao mapper = sqlSession.getMapper(TbexamDao.class);

        int add = mapper.commit(exam,tbexam);
        sqlSession.close();
        return  add>0?true:false;
    }
}
