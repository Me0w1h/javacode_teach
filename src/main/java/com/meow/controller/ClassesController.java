package com.meow.controller;

import com.meow.common.Code;
import com.meow.common.Result;
import com.meow.domain.Classes;
import com.meow.service.ClassesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/class")
public class ClassesController {
    @Autowired
    private ClassesService classesService;

    @GetMapping
    public Result all(){
        List<Classes> all = classesService.all();
        return new Result(all!=null? Code.GET_OK:Code.GET_ERR,all);
    }
    @PostMapping
    public Result add(@RequestBody Classes classes){

        boolean add = classesService.add(classes);

        return new Result(add?Code.SAVE_OK:Code.SAVE_ERR,null);

    }

}
