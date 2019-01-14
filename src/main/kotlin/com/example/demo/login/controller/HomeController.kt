package com.example.demo.login.controller

import com.example.demo.login.domain.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

import com.example.demo.login.domain.service.UserService
import org.springframework.web.bind.annotation.PostMapping


@Controller
class HomeController {

    @Autowired
    internal var userService: UserService? = null

    // ユーザー一覧画面のGET用メソッド
    @GetMapping("/home")
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

    // ユーザー一覧画面のGET用メソッド
    @GetMapping("/userList")
    fun getUserList(model: Model): String {

        // コンテンツ部分にユーザー一覧を表示するための文字列を登録
        model.addAttribute("contents", "login/userList::userList_contents")

        // ユーザー一覧の生成
        val userList: MutableList<User> = userService!!.selectMany()

        // Modelにユーザーリストを登録
        model.addAttribute("userList", userList)

        // データ件数を取得
        val count: Int = userService!!.count()

        model.addAttribute("userListCount", count)

        return "login/homeLayout"
    }

    // ユーザー一覧のCSV出力用メソッド
    fun getUserListCsv(model: Model): String {
        // TODO:
        return getUserList(model)
    }
}