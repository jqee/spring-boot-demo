package com.primeton.web.test.demo1;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author weibing@primeton.com
 * @date 2019/10/31 14:06
 */
@ControllerAdvice
@ResponseBody
public class A {
    @ExceptionHandler(Exception.class)
    public String exception(Exception exception) {
        return "ok";
    }
}
