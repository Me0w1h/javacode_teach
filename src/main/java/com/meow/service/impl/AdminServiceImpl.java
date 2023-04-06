package com.meow.service.impl;

import com.meow.dao.AdminDao;
import com.meow.dao.ClassesDao;
import com.meow.domain.Admin;
import com.meow.service.AdminService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    @Override
    public Admin login(String id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AdminDao mapper = sqlSession.getMapper(AdminDao.class);

        Admin login = mapper.login(id);
        return login;

    }
}
