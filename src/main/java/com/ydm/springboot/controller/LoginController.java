package com.ydm.springboot.controller;

import com.ydm.springboot.comm.util.VerifyCodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Timer;
import java.util.TimerTask;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping("/login")
    public String userLogin(){
        logger.info("登录页面跳转。。。。 ");
        return "login";
    }

    /**
     * 获取验证码
     * @param
     * @param request
     * @param response
     * @return
     * @throws
     */
    @ResponseBody
    @RequestMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request,HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        session.removeAttribute("verifyCode");
        //设置不缓存图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0) ;
        //指定生成的相应图片
        response.setContentType("image/jpeg") ;
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        session.setAttribute("verifyCode", verifyCode);
        //定时5分钟删除验证码
        this.removeAttrbute(session, "verifyCode");
        VerifyCodeUtils.outputImage(200, 80, response.getOutputStream(), verifyCode);
    }
    /**
     * 设置5分钟后删除session中的验证码
     * @param session
     * @param attrName
     */
    private void removeAttrbute(final HttpSession session, final String attrName) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 删除session中存的验证码
                session.removeAttribute(attrName);
                timer.cancel();
            }
        }, 5 * 60 * 1000);
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
