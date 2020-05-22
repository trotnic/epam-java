package com.takeandfood.takeandfood.aspect;/*
 * @project takeandfood
 * @author vladislav on 4/20/20
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAspect {
    private Logger logger = LogManager.getLogger(LogAspect.class);


    @Before("execution(* com.takeandfood.takeandfood.service.*.*(..))")
    public void beforeCall(JoinPoint joinPoint) {
        logger.info("Execute " + joinPoint.getSignature());
    }

    @AfterThrowing(value = "execution(* com.takeandfood.takeandfood.service.*.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint jp, Throwable ex) {
        logger.error(
                "Exception happened in method " + jp.getSignature()
                + "with arguments: " + Arrays.toString(jp.getArgs())
                + "and exception: " + ex.getMessage()
        );
    }

//TODO
//    @After("execution(* com.takeandfood.takeandfood.service.*.*(..))")
//    public void afterCall(JoinPoint joinPoint) {
//        logger.info();
//    }

}
