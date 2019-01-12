package com.example.demo.login.domain.model

import java.util.Date


class User {

    // ユーザーID
    private var userId: String? = null

    // パスワード
    private var password: String? = null

    // ユーザー名
    private var userName: String? = null

    // 誕生日
    private var birthday: Date? = null

    // 年齢
    private var age: Int = 0

    // 結婚ステータス
    private var marriage: Boolean = false

    // ロール
    private var role: String? = null
}
