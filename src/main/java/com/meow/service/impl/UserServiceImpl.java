package com.meow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meow.dao.UserDao;
import com.meow.domain.PageBean;
import com.meow.domain.User;
import com.meow.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


import java.nio.charset.StandardCharsets;
import java.util.List;
@Service
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    @Override
    public PageBean<User> selectByPageAndCondition(int currentPage, int pageSize, User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        int index =(currentPage-1)*pageSize;
        String name = user.getName();
        String className = user.getClassName();
        String school = user.getSchool();
        String exam = user.getExam();

        if (name!=null &&name.length()>0){
            user.setName("%"+name+"%");
        }
        if (className!=null &&className.length()>0){
            user.setClassName("%"+className+"%");
        }
        if (school!=null &&school.length()>0){
            user.setSchool("%"+school+"%");
        }
        if (exam!=null &&exam.length()>0){
            user.setExam("%"+exam+"%");
        }


        List<User> brands = mapper.selectByPageAndCondition(index, pageSize, user);
        int totalCount = mapper.selectTotalCountAndCondition(user);
        sqlSession.close();

        PageBean<User> userPageBean = new PageBean<>();
        userPageBean.setRows(brands);
        userPageBean.setTotalCount(totalCount);

        return userPageBean;
    }

    @Override
    public int selectByclassId(String classId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        int i = mapper.selectByclassId(classId);
        sqlSession.close();
        return i;


    }

    @Override
    public boolean addexam(String newexam, String id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        int addexam = mapper.addexam(newexam, id);
        sqlSession.close();
        return addexam>0?true:false;
    }
}
