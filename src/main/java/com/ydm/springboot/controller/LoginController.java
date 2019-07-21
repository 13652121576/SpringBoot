package com.ydm.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping("/login")
    public String userLogin(){
        logger.info("登录页面跳转。。。。 ");
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(){
        return "login-error";
    }
    @RestController
    public class HelloWorldController {
        @RequestMapping("/hello")
        public String helloWorld(){
            return "spring security hello world";
        }
    }

    @RequestMapping("/whoim")
    @ResponseBody
    public Object whoIm(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
