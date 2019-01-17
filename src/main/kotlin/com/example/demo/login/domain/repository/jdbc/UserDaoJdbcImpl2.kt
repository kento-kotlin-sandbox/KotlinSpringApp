package com.example.demo.login.domain.repository.jdbc

import org.springframework.stereotype.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper

import com.example.demo.login.domain.model.User
import org.springframework.jdbc.core.BeanPropertyRowMapper


@Repository("UserDaoJdbcImpl2")
class UserDaoJdbcImpl2: UserDaoJdbcImpl() {

    override internal var jdbc: JdbcTemplate? = null

    // ユーザー1件取得
    override fun selectOne(userId: String): User {
        // 1件取得用SQL
        val sql: String = "SELECT * FROM m_user WHERE user_id = ?"

        // RowMapperの作成
        val rowMapper: RowMapper<User> = BeanPropertyRowMapper<User>(User::class.java)

        // SQL実行
        return jdbc!!.queryForObject(sql, rowMapper, userId)!!

    }

    override fun selectMany(): MutableList<User> {

        // M_USERテーブルのデータを全件取得するSQL
        val sql: String = "SELECT * FROM m_user"

        // RowMapperの作成
        val rowMapper: RowMapper<User> = BeanPropertyRowMapper<User>(User::class.java)

        return jdbc!!.query(sql, rowMapper)
    }

}