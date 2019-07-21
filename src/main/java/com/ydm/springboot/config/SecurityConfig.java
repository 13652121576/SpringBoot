package com.ydm.springboot.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        //super.configure(http);
        http
                .formLogin().loginPage("/login").loginProcessingUrl("/login/form").permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .and()
                .authorizeRequests().antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**","/plugins/**","/js/**","/html/**","/images/**");
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

