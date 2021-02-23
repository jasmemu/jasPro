package com.zyg.jas.common.tool.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class JasExceptResolver {

    Logger logger = LoggerFactory.getLogger(JasExceptResolver.class);

    @ExceptionHandler(java.lang.NullPointerException.class)
    public void test1(Exception e){
        logger.error("空指针异常");
        logger.error(e.getMessage());
    }

    @ExceptionHandler(java.lang.ArrayIndexOutOfBoundsException.class)
    public  void test2(Exception e){
        logger.error("下标越界异常");
        logger.error(e.getMessage());
    }

    @ExceptionHandler(java.lang.ArithmeticException.class)
    public  void test3(Exception e){
        logger.error("数学异常");
        logger.error(e.getMessage());
    }

}
