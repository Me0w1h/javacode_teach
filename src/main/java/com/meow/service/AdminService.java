package com.meow.service;

import com.meow.domain.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    public Admin login(String id);
}
