package com.ydm.springboot.controller;

import com.ydm.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/cityList")
public class CityController {

    @Autowired
    private CityService cityService;



    @RequestMapping("/findAll")
    public List findAll(){
        List list = cityService.findAll();
       return list;
    }
}
