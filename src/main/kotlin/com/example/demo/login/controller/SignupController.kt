package com.example.demo.login.controller

import com.example.demo.login.domain.model.SignupForm

import java.util.LinkedHashMap

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping


@Controller
class SignupController {

    // ラジオボタンの実装
    internal var radioMarriage: Map<String, String>? = null

    // ラジオボタンの初期化メソッド
    private fun initRadioMarriage(): Map<String, String> {
        val radio: MutableMap<String, String> = LinkedHashMap()

        // 既婚、未婚をMapに格納
        radio.put("既婚", "true")
        radio.put("未婚", "false")

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

        // login.htmlにリダイレクト
        return "redirect:/login"
    }
}
