package com.example.demo.login.domain.model

import java.util.Date
import org.springframework.format.annotation.DateTimeFormat


/**
class SignupForm(
        // ユーザーID
        var userId: String = "",
        // パスワード
        var password: String = "",
        // ユーザー名
        var userName: String = "",
        // 誕生日
        @DateTimeFormat(pattern="yyyy/MM/dd")  コンストラクタでアノテーションを使えないみたいだ
        var birthday: Date = Date(),
        // 年齢
        var age: Int = 0,
        // 結婚ステータス
        var marriage: Boolean = false
)
*/

class SignupForm {

    // ユーザーID
    var userId: String = ""

    // パスワード
    var password: String = ""

    // ユーザー名
    var userName: String = ""

    // 誕生日
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    var birthday: Date = Date()

    // 年齢
    var age: Int = 0

    // 結婚ステータス
    var marriage: Boolean = false
}
