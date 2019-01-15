package com.example.demo.login.controller

import com.example.demo.login.domain.model.SignupForm
import com.example.demo.login.domain.service.UserService
import com.example.demo.login.domain.model.User

import java.util.LinkedHashMap

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping


@Controller
class SignupController {

    @Autowired
    internal var userService: UserService? = null

    // ラジオボタンの実装
    internal var radioMarriage: Map<String, String>? = null

    // ラジオボタンの初期化メソッド
    private fun initRadioMarriage(): Map<String, String> {
        val radio: MutableMap<String, String> = LinkedHashMap()

        // 既婚、未婚をMapに格納
        radio["既婚"] = "true"
        radio["未婚"] = "false"

        return radio
    }

    // ユーザー登録画面のGET用コントローラー
    @GetMapping("/signup")
    fun getSignUp(@ModelAttribute form: SignupForm, model: Model): String {

        // ラジオボタンの初期化メソッド呼び出し
        radioMarriage = initRadioMarriage()

        // ラジオボタン用のMapをModelに登録
        model.addAttribute("radioMarriage", radioMarriage)

        // signup.htmlに画面遷移
        return "login/signup"
    }

    // ユーザー登録画面のPOST用コントローラー
    @PostMapping("/signup")
    fun postSignUp(@ModelAttribute @Validated form: SignupForm, bindingResult: BindingResult, model: Model): String {

        // 入力チェックに引っかかった場合、ユーザー登録画面に戻る
        if(bindingResult.hasErrors()) {
            return getSignUp(form, model)
        }

        // DEBUG: formの中身をコンソールに出力
        System.out.println("ユーザーID：" + form.userId)
        System.out.println("パスワード：" + form.password)
        System.out.println("ユーザー名：" + form.userName)
        System.out.println("誕生日：" + form.birthday)
        System.out.println("年齢：" + form.age)
        System.out.println("結婚ステータス：" + form.marriage)


        // insert用変数
        val user: User? = User()

        // ユーザーID
        user?.userId = form.userId
        // パスワード
        user?.password = form.password
        // ユーザー名
        user?.userName = form.userName
        // 誕生日
        user?.birthday = form.birthday
        // 年齢
        user?.age = form.age
        // 結婚ステータス
        user?.marriage = form.marriage!!

        // ユーザー登録処理
        val result: Boolean = userService?.insert(user!!)!!

        // ユーザー登録結果の判定
        if(result) {
            System.out.println("insert成功")
        } else {
            System.out.println("insert失敗")
        }

        // login.htmlにリダイレクト
        return "redirect:/login"
    }
}
