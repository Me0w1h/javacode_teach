package com.meow.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.meow.domain.exams;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamsService extends IService<exams> {
    List<exams> all ();

    //boolean add(exams exams);

    void getId(String examName);




}
