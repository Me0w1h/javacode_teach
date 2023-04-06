package com.meow.service;

import com.meow.domain.Tbexam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface TbexamService {

    List<Tbexam> select(String id);

    boolean  add(String exam,Tbexam tbexam);

    boolean commit( String exam, Tbexam tbexam);
}
