package com.ydm.springboot.config.Security;


import org.springframework.security.core.AuthenticationException;

/**
 * @author: ydm
 * @create: 2019-07-30 17:20
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
