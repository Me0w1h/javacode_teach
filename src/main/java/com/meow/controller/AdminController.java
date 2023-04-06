package com.meow.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.meow.common.Code;
import com.meow.common.Result;
import com.meow.domain.*;
import com.meow.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public Result login(@RequestBody Admin admin, HttpServletRequest request, HttpSession session){
        System.out.println(admin);
        String username =admin.getId();

        String password = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes(StandardCharsets.UTF_8));//输入md5


        Admin rsuser = adminService.login(username);



        if (null==rsuser){
            return new Result(Code.LOGIN_ERR,"用户名错误");
        }else if(!rsuser.getPassword().equals(password)){
            return new Result(Code.LOGIN_ERR,"密码错误");
        }else {
            rsuser.setPassword("XXXX");

            session.setAttribute("admin",rsuser);

            return new Result(Code.LOGIN_OK,"登录成功");
        }

    }

    @GetMapping
    public Result testlogin(HttpServletRequest request, HttpSession session){

        boolean login =false;
        Admin username = (Admin) session.getAttribute("admin");
        if(username!=null&"root".equals(username.getId())){
            login =true;
        }


        return new Result(login?Code.LOGIN_OK:Code.LOGIN_ERR,login?"logined":"请先登录！");

    }


}
