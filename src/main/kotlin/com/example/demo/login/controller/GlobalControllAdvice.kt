package com.example.demo.login.controller

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


/**
 * 共通例外クラス（コントローラ）
 */
@ControllerAdvice
@Component
class GlobalControllAdvice {

    @ExceptionHandler(DataAccessException::class)
    fun dataAccessExceptionHandler(e: DataAccessException, model: Model): String {
        // 例外クラスのメッセージをModelに登録
        model.addAttribute("error", "内部サーバーエラー（DB）：GlobalControllAdvice")

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("message", "DataAccessExceptionが発生しました")

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR)

        return "error"
    }

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(e: Exception, model: Model): String {
        // 例外クラスのメッセージをModelに登録
        model.addAttribute("error", "内部サーバーエラー：GlobalControllAdvice")

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("message", "Exceptionが発生しました")

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR)

        return "error"
    }
}
