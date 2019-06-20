/***********************************************
 * File Name: CalcAspect
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 05 06 2019 15:27
 ***********************************************/

package com.duochuang.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class CalcAspect {

    @Pointcut(value = "@annotation(com.duochuang.common.anno.CalcAnno)")
    public void calcPointCut(){
    }

    @Around("calcPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));
        System.out.println("开始执行方法");
        Object proceed = joinPoint.proceed();
        System.out.println(proceed);
        System.out.println("结束执行方法");
        return proceed;
    }

}
