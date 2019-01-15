package com.example.demo.login.controller

import com.example.demo.login.domain.model.SignupForm
import com.example.demo.login.domain.model.User
import com.example.demo.login.domain.service.UserService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

import java.util.LinkedHashMap

@Controller
class HomeController {

    @Autowired
    internal var userService: UserService? = null

    // 結婚ステータスのラジオボタン用変数
    internal var radioMarriage: Map<String, String>? = null

    // ラジオボタンの初期化メソッド
    internal fun initRadioMarriage(): Map<String, String> {
        val radio: MutableMap<String, String> = LinkedHashMap()

        // 既婚、未婚をMapに格納
        radio["既婚"] = "true"
        radio["未婚"] = "false"

        return radio
    }


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

    // ユーザー詳細画面のGET用メソッド
    @GetMapping("/userDetail/{id:.+}")
    fun getUserDetail(@ModelAttribute form: SignupForm, model: Model, @PathVariable("id") userId: String): String {

        // DEBUG:ユーザーID確認
        System.out.println("userId = " + userId)

        // コンテンツ部分にユーザー詳細を表示するための文字列を登録
        model.addAttribute("contents", "login/userDetail::userDetail_contents")

        // 結婚ステータス用ラジオボタンの初期化
        this.radioMarriage = initRadioMarriage()

        // ラジオボタン用のMapをModelに登録
        model.addAttribute("radioMarriage", radioMarriage)

        // ユーザーIDのチェック
        if(userId.length > 0) {
            // ユーザー情報を取得
            val user: User = userService!!.selectOne(userId)

            // Userクラスをフォームクラスに変換
            // ユーザーID
            form.userId = user.userId
            // ユーザー名
            form.userName = user.userName
            // 誕生日
            form.birthday = user.birthday
            // 年齢
            form.age = user.age
            // 結婚ステータス
            form.marriage = user.marriage

            // Modelに登録
            model.addAttribute("signupForm", form)
        }

        return "login/homeLayout"
    }

    // ユーザー更新用処理
    @PostMapping(value="/userDetail", params=["update"])
    fun postUserDetailUpdate(@ModelAttribute form: SignupForm, model: Model): String {
        System.out.println("更新ボタンの処理")

        // Userインスタンスの生成
        val user: User = User()

        // フォームクラスをUserクラスに変換
        user.userId = form.userId
        user.password = form.password
        user.userName = form.userName
        user.birthday = form.birthday
        user.age = form.age
        user.marriage = form.marriage as Boolean

        // 更新実行
        val result: Boolean = userService!!.updateOne(user)

        if(result) {
            model.addAttribute("result", "更新成功")
        } else {
            model.addAttribute("result", "更新失敗")
        }

        // ユーザー一覧画面を表示
        return getUserList(model)
    }

    // ユーザー削除用処理
    @PostMapping(value="/userDetail", params=["delete"])
    fun postUserDetailDelete(@ModelAttribute form: SignupForm, model: Model): String {
        System.out.println("削除ボタンの処理")

        // 削除実行
        val result: Boolean = userService!!.deleteOne(form.userId!!)

        if(result) {
            model.addAttribute("result", "削除成功")
        } else {
            model.addAttribute("result", "削除失敗")
        }

        // ユーザー一覧画面を表示
        return getUserList(model)
    }

    // ユーザー一覧のCSV出力用メソッド
    fun getUserListCsv(model: Model): String {
        // TODO:
        return getUserList(model)
    }
}