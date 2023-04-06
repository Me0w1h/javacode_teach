package com.meow.controller;
import com.meow.common.Code;
import com.meow.common.Result;
import com.meow.domain.PageBean;
import com.meow.domain.User;
import com.meow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/{currentPage}")
//    public Result all(@PathVariable  Integer currentPage){
//
//
//        IPage page = new Page(currentPage,10);
//        userService.page(page);
//        int total = (int) page.getTotal();
//        String msg = ""+total;
//        return new Result(20001,msg,new ArrayList<User>(page.getRecords()));
//
//
//    }

    @PostMapping
    public Result all(@RequestParam int currentPage,int size,  @RequestBody User user){

        PageBean<User> userPageBean = userService.selectByPageAndCondition(currentPage, size, user);

        return new Result(Code.GET_OK,userPageBean);

    }
    @PostMapping("/{id}")
    public Result add(@RequestBody User user){
        boolean addorup = userService.getById(user.getId())!=null?true:false;
        boolean res = userService.saveOrUpdate(user);

        return new Result(res
                ?(addorup?Code.UPDATE_OK:Code.SAVE_OK)
                :(addorup?Code.UPDATE_ERR:Code.SAVE_ERR),null);

    }
    
    @DeleteMapping
    public Result deleteById(@RequestBody long[] ids){
        List idsList =new ArrayList();
        for (int i = 0; i < ids.length; i++) {
            idsList.add(ids[i]);
        }
        boolean res = userService.removeByIds(idsList);
        return new Result(res?Code.DELETE_OK:Code.DELETE_ERR,null);
    }



}
