package com.ydm.springboot.config.Security;

import com.ydm.springboot.entity.SysUser;
import com.ydm.springboot.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private MyPasswordEncoder passwordEncoder;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        //这里可以可以通过username（登录时输入的用户名）然后到数据库中找到对应的用户信息，并构建成我们自己的UserInfo来返回。
        //这里可以通过数据库来查找到实际的用户信息，这里我们先模拟下,后续我们用数据库来实现
        if(username.equals("admin"))
        {
            logger.info("用户名"+username);
            logger.info("用户密码"+passwordEncoder.encode("112233"));
            SysUser sysUser = sysUserService.getByUserName(username);
            //假设返回的用户信息如下;
            UserInfo userInfo=new UserInfo(sysUser.getUserName(), sysUser.getPassword(), "ROLE_ADMIN", true,true,true, true);
            return userInfo;

        }
        return null;
    }
}




