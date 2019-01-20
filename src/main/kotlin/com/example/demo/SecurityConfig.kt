package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import javax.sql.DataSource


@EnableWebSecurity
@Configuration
class SecurityConfig: WebSecurityConfigurerAdapter() {

    @Autowired
    internal var dataSource: DataSource? = null

    // ユーザーIDとパスワード取得用SQL
    companion object {
        private const val USER_SQL = "SELECT" +
                " user_id," +
                " password," +
                " true" +
                " FROM" +
                " m_user" +
                "  WHERE" +
                " user_id = ?"
        private const val ROLE_SQL = "SELECT" +
                " user_id," +
                " role" +
                " FROM" +
                " m_user" +
                " WHERE" +
                " user_id = ?"
    }

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        // 静的リソースへのアクセスには、セキュリティを適用しない設定
        web.ignoring().antMatchers("/webjars/**", "/css/**")
    }

    protected override fun configure(http: HttpSecurity) {
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

    @Throws(Exception::class)
    protected override fun configure(auth: AuthenticationManagerBuilder) {
        // ログイン処理時のユーザー情報を、DBから取得
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USER_SQL)
                .authoritiesByUsernameQuery(ROLE_SQL)
    }
}