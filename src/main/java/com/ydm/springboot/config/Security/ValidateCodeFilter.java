package com.ydm.springboot.config.Security;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.IOException;

/**
 * 验证码过滤器
 * @author: ydm
 * @create: 2019-07-30 17:02
 */
public class ValidateCodeFilter extends OncePerRequestFilter{

    public ValidateCodeFilter(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    private AuthenticationFailureHandler authenticationFailureHandler;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if(StringUtils.equals("/login/form",httpServletRequest.getRequestURI())
                &&StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(),"post")){
            try {
                validate(httpServletRequest);
            }catch (ValidateCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void validate(HttpServletRequest servletRequest) throws ServletRequestBindingException {
        String code = ServletRequestUtils.getStringParameter(servletRequest, "code");
        String verifyCode = (String)servletRequest.getSession().getAttribute("verifyCode");
        if(StringUtils.isEmpty(verifyCode)){
            throw new ValidateCodeException("验证码失效或为空！");
        }
        if(!StringUtils.equalsIgnoreCase(verifyCode,code)){
            throw new ValidateCodeException("验证码不正确！");
        }
        servletRequest.getSession().removeAttribute("verifyCode");
    }
}
