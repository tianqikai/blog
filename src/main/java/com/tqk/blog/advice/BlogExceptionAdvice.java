package com.tqk.blog.advice;

import com.tqk.blog.enums.ResultEnum;
import com.tqk.blog.execption.BlogException;
import com.tqk.blog.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 定义统一异常处理
 *
 * @Author: tianqikai
 * @Date: 2020/2/9 14:25
 * @Version 1.0
 */
@ControllerAdvice
@Slf4j
public class BlogExceptionAdvice {

    /**
     * 统一处理 BlogException
     * @param exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Object> exceptionHandler(Exception exception) {
        log.error("Exception 统一异常处理：", exception);
        if(exception instanceof BlogException){
            log.error("BlogException 统一异常处理：", exception);
            BlogException blogException=(BlogException) exception;
            return new Result<>(blogException.getErrorCode(), exception.getMessage());
        }else{
            return new Result<>(ResultEnum.ERROR.getCode(), exception.getMessage());
        }
    }
}
