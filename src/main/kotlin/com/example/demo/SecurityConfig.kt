package com.example.demo

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@EnableWebSecurity
@Configuration
class SecurityConfig: WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        // 静的リソースへのアクセスには、セキュリティを適用しない設定
        web.ignoring().antMatchers("/webjars/**", "/css/**")
    }

    override fun configure(http: HttpSecurity) {
        // ログイン不要ページの設定
        http.authorizeRequests()
                .antMatchers("/webjars/**").permitAll()  // webjarsへのアクセス許可
                .antMatchers("/css/**").permitAll()      // cssへのアクセス許可
                .antMatchers("/login").permitAll()       // ログイン画面へのアクセス許可
                .antMatchers("/signup").permitAll()      // ユーザー登録画面へのアクセス許可
                .anyRequest().authenticated()                        // それ以外は直リンク禁止

        // ログイン処理
        http.formLogin()
                .loginProcessingUrl("/login")      // ログイン処理のパス
                .loginPage("/login")                      // ログイン画面の指定
                .failureUrl("/login")           // ログイン失敗時の遷移先
                .usernameParameter("userId")       // ログイン画面のユーザーID
                .passwordParameter("password")     // ログイン画面のパスワード
                .defaultSuccessUrl("/home", true)   // ログイン成功後の遷移先

        // CSRF対策を無効に設定（後で消す）
        http.csrf().disable()
    }
}