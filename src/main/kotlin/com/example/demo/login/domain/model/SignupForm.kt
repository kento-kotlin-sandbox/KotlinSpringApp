package com.example.demo.login.domain.model

import java.util.Date

import javax.validation.constraints.AssertFalse
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length

import org.springframework.format.annotation.DateTimeFormat


// null許容にしないとダメなのか?
// ここら辺よくわからん
class SignupForm {

    // ユーザーID
    // 必須入力、メールアドレス形式
    @NotBlank
    @Email
    var userId: String? = ""

    // 必須入力、長さ４から１００桁まで、半角英数字のみ
    // パスワード
    @NotBlank
    @Length(min = 4, max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    var password: String? = ""

    // ユーザー名
    // 必須入力
    @NotBlank
    var userName: String? = ""

    // 誕生日
    // 必須入力
    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    var birthday: Date? = Date()

    // 年齢
    // 値は20 ~ 100 まで
    @Min(20)
    @Max(100)
    var age: Int? = 0

    // 結婚ステータス
    // falseのみ可能
    @AssertFalse
    var marriage: Boolean? = false
}
