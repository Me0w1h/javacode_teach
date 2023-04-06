package com.meow.controller;

import com.meow.common.Code;
import com.meow.common.Result;
import com.meow.domain.exams;
import com.meow.service.ExamsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/exams")
public class ExamsController {

    @Autowired
    private ExamsService examsService;

    @GetMapping
    public Result all (){
        List<exams> all = examsService.all();

    return new Result(Code.GET_OK,all);
    }

    @PostMapping
    public Result add(@RequestBody exams exams){
        exams.setDate(exams.getDate().substring(0,10));
        System.out.println(exams);
        boolean b = examsService.saveOrUpdate(exams);
        if(b){
            examsService.getId(exams.getExamName());
        }
        return new Result(b?Code.SAVE_OK:Code.SAVE_ERR,null);

    }
}
