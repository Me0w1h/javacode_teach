package com.meow.controller;

import com.meow.common.Code;
import com.meow.common.Result;
import com.meow.domain.Classes;
import com.meow.domain.Tbexam;
import com.meow.domain.User;
import com.meow.service.ClassesService;
import com.meow.service.TbexamService;
import com.meow.service.UserService;
import com.meow.utils.Paillier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/commit")


public class CommitController {

    @Autowired
    private TbexamService tbexamService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClassesService classesService;

    @PostMapping
    public Result commit(@RequestBody String[] info){
        //info[0] user's id  info[1]= examId info[2]=user's score*pk info[3] = pk

        BigInteger n = new BigInteger(info[3]);
        boolean commit =false;
        boolean commit1 =false;
        BigInteger score = new BigInteger(info[2]);
        BigInteger sk;
        int count;
        int total;

        User user = userService.getById(info[0]);
        String exam = user.getExam();


        List<String> commited = new ArrayList<String>();
        if(exam!=null) {
            for (int i = 0; i < user.getExam().length(); i += 19) {
                commited.add(exam.substring(i, i + 19));
            }
        }
        if(commited!=null) {
            for (int i = 0; i < commited.size(); i++) {
                if(info[1].equals(commited.get(i))){
                    commit=true;  //该学生已提交
                    break;
                }
            }
        }


        List<Tbexam> tbexams = tbexamService.select(info[1]);
        if(!commit) {
            if (tbexams != null) {
                for (Tbexam tbexam : tbexams) {
                    if (tbexam.getClassId().equals(user.getClassId())) {

                        //提交EMn,进行(em1*em2*em3...）*EMn操作
                        score = score.multiply(new BigInteger(tbexam.getScore())).mod(n.multiply(n));

                        tbexam.setScore(score.toString());
                        count = tbexam.getCount() + 1;
                        tbexam.setCount(count);
                        total = tbexam.getTotal();
                        commit1 = tbexamService.commit(info[1], tbexam); //该学生提交完成
                        userService.addexam(info[1], info[0]);


                        if (!(count < total)) { //当该班级所有学生提交成绩后（count=total),开始对平均分进行计算
                            //处理成绩
                            Classes classes = classesService.selectById(user.getClassId());
                            sk = new BigInteger(classes.getSk()); //从数据库获得解密的私钥
                            Paillier depaillier = new Paillier(n, sk); //创建该公私钥对的解密paillier对象
                            score = depaillier.De(score);//对em1*em2*em3....进行解密，获得总分
                            int fnl = Integer.valueOf(score.toString());
                            float every =fnl/total;//获得平均分
                            tbexam.setScore(String.valueOf(every));
                            boolean commit2 = tbexamService.commit(info[1], tbexam); //平均分填入数据库

                        }


                    }
                }
            }
        }

        if(commit){
            return new Result(Code.SAVE_ERR,"您已经提交过了！");

        }else {
            if(commit1){
                return new Result(Code.SAVE_OK,"提交成绩成功！");
            }else {
                return new Result(Code.SAVE_ERR,"提交成绩失败！");
            }
        }



    }



}
