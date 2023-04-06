package com.meow;

import com.meow.dao.UserDao;
import com.meow.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Teach01ApplicationTests {
    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {
        User user = userDao.selectById("11111111111");
        System.out.println(user);
    }

}
