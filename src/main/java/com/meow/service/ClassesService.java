package com.meow.service;

import com.meow.domain.Classes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassesService {

    List<Classes> all();

    Classes selectById(String id);
    boolean add(Classes classes);
}
