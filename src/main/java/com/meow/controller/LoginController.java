package com.meow.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.meow.common.Code;
import com.meow.common.Result;

import com.meow.domain.Classes;
import com.meow.domain.Tbexam;
import com.meow.domain.User;
import com.meow.domain.exams;
import com.meow.service.ClassesService;
import com.meow.service.ExamsService;
import com.meow.service.TbexamService;
import com.meow.service.UserService;
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
@Slf4j
@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private ExamsService examsService;


    @Autowired
    private UserService userService;

    @Autowired
    private TbexamService tbexamService;

    @Autowired
    private ClassesService classesService;

    @PostMapping
    public Result loginuser(@RequestBody User user, HttpServletRequest request, HttpSession session){
        String username =user.getUsername();
        LambdaQueryWrapper<User> qw =new LambdaQueryWrapper<User>();
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
        qw.eq(User::getUsername,user.getUsername());

        User rsuser= userService.getOne(qw);


        if (null==rsuser){
            return new Result(Code.LOGIN_ERR,"用户名错误");
        }else if(!DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8))
                 .equals(rsuser.getPassword())){
            return new Result(Code.LOGIN_ERR,"密码错误");
        }else {
            rsuser.setPassword("xxxx");

            session.setAttribute("username",rsuser);

            return new Result(Code.LOGIN_OK,"登录成功");
        }


    }
    @GetMapping
    public Result testlogin(HttpServletRequest request, HttpSession session){

        User username = (User)session.getAttribute("username");


        List<exams> userexams = new ArrayList<exams>();

        List<Object> data = new ArrayList();

        List<exams> all = examsService.all();
        String pk;

        if(username!=null) {
            Classes classes = classesService.selectById(username.getClassId());
             pk = classes.getPk();
            for (exams exams : all) {
                String id = exams.getId();
                List<Tbexam> select = tbexamService.select(id);
                if (select != null) {
                    for (Tbexam tbexam : select) {
                        exams userexam = new exams();
                        if (username.getClassId().equals(tbexam.getClassId())) {
                            userexam.setId(exams.getId());
                            userexam.setExamName(exams.getExamName());
                            userexam.setDate(exams.getDate());
                            userexam.setDescription(exams.getDescription());
                            userexam.setStatus(exams.getStatus());
                            userexams.add(userexam);
                        }

                    }
                }

            }

            data.add(username);
            data.add(userexams);
            data.add(pk);

        }




        return new Result(username!=null?Code.LOGIN_OK:Code.LOGIN_ERR,username!=null?data:null);

    }

}
