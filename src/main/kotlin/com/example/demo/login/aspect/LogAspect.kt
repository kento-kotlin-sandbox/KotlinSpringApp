package com.example.demo.login.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component


@Aspect
@Component
class LogAspect {

    @Before("execution(* *..*.*Controller.*(..))")
    fun startLog(jp: JoinPoint) {
        System.out.println("メソッド開始：" + jp.getSignature())
    }

    @After("execution(* *..*.*Controller.*(..))")
    fun endLog(jp: JoinPoint) {
        System.out.println("メソッド終了：" + jp.getSignature())
    }
}