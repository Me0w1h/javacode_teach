package com.meow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.meow.domain.PageBean;
import com.meow.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public interface UserService extends IService<User> {
    PageBean<User> selectByPageAndCondition(int currentPage, int pageSize, User user);

    int selectByclassId(String classId);

    boolean addexam(String newexam , String id);
}
