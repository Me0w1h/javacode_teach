package com.meow.service.impl;

import com.meow.dao.ClassesDao;
import com.meow.dao.ExamsDao;
import com.meow.domain.Classes;
import com.meow.service.ClassesService;
import com.meow.utils.Paillier;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public List<Classes> all() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassesDao mapper = sqlSession.getMapper(ClassesDao.class);

        List<Classes> all = mapper.all();
        for (Classes classes : all) {
            classes.setSk("sdnu");
        }
        sqlSession.close();
        return all;

    }

    @Override
    public Classes selectById(String id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassesDao mapper = sqlSession.getMapper(ClassesDao.class);

        Classes aclass = mapper.selectById(id);
        sqlSession.close();
        return aclass;
    }

    @Override
    public boolean add(Classes classes) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassesDao mapper = sqlSession.getMapper(ClassesDao.class);

        Paillier paillier = new Paillier();

        BigInteger pk =paillier.n;
        BigInteger sk = paillier.getLambda();
        classes.setSk(sk.toString());
        classes.setPk(pk.toString());

        int add = mapper.add(classes);
        return add>0?true:false;
    }
}
