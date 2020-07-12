package com.ydm.springboot.config.Security;

import com.ydm.springboot.entity.SysPosition;
import com.ydm.springboot.entity.SysPower;
import com.ydm.springboot.entity.SysUser;
import com.ydm.springboot.service.SysPositionService;
import com.ydm.springboot.service.SysPowerService;
import com.ydm.springboot.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class MyUserDetailService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private MyPasswordEncoder passwordEncoder;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysPowerService sysPowerService;

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

            SysPower byId = sysPowerService.getById("33fd06c66d8a11e8b54a00ff9dc4ba58");
            //下面为本地数据，我们需要的是从数据库操作
            Collection<GrantedAuthority> roleList = new ArrayList<>();
            roleList.add(new SimpleGrantedAuthority(byId.getActionUrl()));//"USER" 从数据库取出来
            //假设返回的用户信息如下;
            UserInfo userInfo=new UserInfo(sysUser.getUserName(), sysUser.getPassword(), roleList, null,true,true,true, true);
            return userInfo;

        }
        return null;
    }
}




