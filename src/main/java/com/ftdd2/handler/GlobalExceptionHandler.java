package com.ftdd2.handler;

import com.ftdd2.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GlobalExceptionHandler{

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.fail(ex.getMessage());
    }
}