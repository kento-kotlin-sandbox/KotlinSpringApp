package com.example.demo.login.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Around
import org.springframework.stereotype.Component


@Aspect
@Component
class LogAspect {

    @Throws(Exception::class)
    @Around("execution(* *..*.*Controller.*(..))")
    fun startLog(jp: ProceedingJoinPoint): Any {
        System.out.println("メソッド開始：" + jp.getSignature())

        try {
            val result = jp.proceed()

            System.out.println("メソッド終了：" + jp.getSignature())

            return result

        } catch(e: Exception) {
            System.out.println("メソッド異常終了：" + jp.getSignature())
            e.printStackTrace()
            throw e
        }
    }

    // UserDaoクラスのログ出力
    @Throws(Exception::class)
    @Around("execution(* *..*.*UserDao*.*(..))")
    fun daoLog(jp: ProceedingJoinPoint): Any {
        System.out.println("メソッド開始：" + jp.getSignature())

        try {
            val result = jp.proceed()

            System.out.println("メソッド終了：" + jp.getSignature())

            return result

        } catch(e: Exception) {
            System.out.println("メソッド異常終了：" + jp.getSignature())
            e.printStackTrace()
            throw e
        }
    }
}