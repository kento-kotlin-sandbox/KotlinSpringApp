package com.example.demo.login.aspect

import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Component


@Aspect
@Component
class ErrorAspect {

    @AfterThrowing(value="execution(* *..*..*(..))" + " && (bean(*Controller) || bean(*Service) || bean(*Repository))", throwing="ex")
    fun throwwingNull(ex: DataAccessException) {
        // 例外処理の内容（ログ出力）
        System.out.println("=================================")
        System.out.println("""DataAccessExceptionが発生しました。：$ex""")
        System.out.println("=================================")
    }
}