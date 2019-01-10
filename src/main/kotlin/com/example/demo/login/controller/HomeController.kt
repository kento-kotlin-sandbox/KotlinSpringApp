package com.example.demo.login.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

import com.example.demo.login.domain.service.UserService
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.web.bind.annotation.PostMapping


@Controller
class HomeController {

    @Autowired
    lateinit var userService: UserService

    // ユーザー一覧画面のGET用メソッド
    fun getHome(model: Model): String {
        // コンテンツ部分にユーザー一覧を表示するための文字列を登録
        model.addAttribute("contents", "login/home::home_contents")

        return "login/homeLayout"
    }

    // ログアウト用メソッド
    @PostMapping("/logout")
    fun postLogout(): String {

        // ログイン画面にリダイレクト
        return "redirect:/login"
    }
}