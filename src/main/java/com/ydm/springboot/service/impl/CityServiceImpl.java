package com.ydm.springboot.service.impl;

import com.ydm.springboot.mapper.CityDao;
import com.ydm.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityServiceImpl implements CityService {


    @Autowired
    private  CityDao cityDao;
    @Override
    public List findAll() {
        return cityDao.findAll();
    }
}
