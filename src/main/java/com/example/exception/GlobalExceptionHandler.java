package com.example.exception;
import com.example.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
//全局异常处理器 类上要有@RestControllerAdvice 方法上要有 @ExceptionHandler(Exception.class)//（）中是要处理的异常
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)//（）中是要处理的异常
    public Result handleException(Exception e) {
        e.printStackTrace();//输入控制台
        return Result.failure(StringUtils.hasLength(e.getMessage())?e.getMessage():"操作失败 干啥呢");
        //StringUtils.hasLength(e.getMessage())判断字符串是否为空
        //返回 e.getMessage()      Result.failure(e.getMessage())
        //返回 "操作失败"            Result.failure("操作失败")
    }
}
