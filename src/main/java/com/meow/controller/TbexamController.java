package com.meow.controller;


import com.meow.common.Code;
import com.meow.common.Result;
import com.meow.domain.Classes;
import com.meow.domain.Tbexam;
import com.meow.service.ClassesService;
import com.meow.service.TbexamService;
import com.meow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/texam")
public class TbexamController {

    @Autowired
    private TbexamService tbexamService;
    @Autowired
    private ClassesService classesService;
    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public Result select(@PathVariable String id){

        List<Tbexam> select = tbexamService.select(id);
        return new Result(select!=null? Code.GET_OK:Code.GET_ERR,select);
    }
    @PostMapping
    public Result add(@RequestBody String[] classes){

        int count = 0;
        for (int i = 1; i < classes.length; i++) {
            Classes classesById = classesService.selectById(classes[i]);
            int classCount = userService.selectByclassId(classes[i]);
            Tbexam tbexam = new Tbexam();
            tbexam.setClassId(classesById.getClassId());
            tbexam.setClassName(classesById.getClassName());
            tbexam.setTotal(classCount);

            boolean add = tbexamService.add(classes[0], tbexam);
            if (add){
                count++;
            }
        }
        return new Result(count>0?Code.SAVE_OK:Code.SAVE_ERR,"添加成功"+count+"/"+(classes.length-1));
    }
}
