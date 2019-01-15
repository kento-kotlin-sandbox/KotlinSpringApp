package com.example.demo.login.domain.model

import java.util.Date


class User {

    // ユーザーID
    var userId: String? = null

    // パスワード
    var password: String? = null

    // ユーザー名
    var userName: String? = null

    // 誕生日
    var birthday: Date? = null

    // 年齢
    var age: Int = 0

    // 結婚ステータス
    var marriage: Boolean = false

    // ロール
    var role: String? = null
}
