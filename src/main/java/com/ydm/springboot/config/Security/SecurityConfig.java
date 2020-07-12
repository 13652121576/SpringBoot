package com.ydm.springboot.config.Security;


import com.ydm.springboot.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //全局
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SysAuthenctiationFailureHandler sysAuthenctiationFailureHandler;
    @Autowired
    private SysAuthenticationSuccessHandler sysAuthenticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter(sysAuthenctiationFailureHandler);
        // TODO Auto-generated method stub
        //super.configure(http);
            http
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin().loginPage("/login").loginProcessingUrl("/login/form")
                .failureHandler(sysAuthenctiationFailureHandler)
                .successHandler(sysAuthenticationSuccessHandler)
                .and()
                .authorizeRequests().antMatchers("/login","/getVerifyCode").permitAll() //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .anyRequest().authenticated()
                .and()
                .logout()   //退出登录相关配置
                .logoutUrl("/logout")//自定义退出的地址
                .logoutSuccessUrl("/login")//退出之后跳转到注册页面
                .logoutSuccessHandler(new MyLogoutSuccessHandler())//退出成功后的操作
                .deleteCookies("JSESSIONID")//删除当前的JSESSIONID
                .and().csrf().disable();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/druid/**","/css/**","/plugins/**","/js/**","/html/**","/images/**");
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        //可以设置内存指定的登录的账号密码,指定角色
//        //不加.passwordEncoder(new MyPasswordEncoder())
//        //就不是以明文的方式进行匹配，会报错
//        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
//        //.passwordEncoder(new MyPasswordEncoder())。
//        //这样，页面提交时候，密码以明文的方式进行匹配。
//        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
//                .withUser("admin").password("123").roles("ADMIN");
//    }
//    @Autowired
//    private AuthenticationProvider provider;  //注入我们自己的AuthenticationProvider
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // TODO Auto-generated method stub
//        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("admin");
//
//
//    }
}

