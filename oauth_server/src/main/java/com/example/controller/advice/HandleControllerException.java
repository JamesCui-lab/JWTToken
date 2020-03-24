package com.example.controller.advice;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author john
 * @date 2020/1/11 - 20:40
 */
@ControllerAdvice
public class HandleControllerException {
    @ExceptionHandler(RuntimeException.class)
    public String exceptionHandler(RuntimeException e) {
        if (e instanceof AccessDeniedException) {
            //如果是权限不足异常，则跳转到权限不足页面！
            return "redirect:/403.jsp";
        }
        //其余的异常都到500页面！
        return "redirect:/500.jsp";
    }
}
