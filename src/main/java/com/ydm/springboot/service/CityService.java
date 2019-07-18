package com.ydm.springboot.service;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface CityService {
    List findAll();
}
