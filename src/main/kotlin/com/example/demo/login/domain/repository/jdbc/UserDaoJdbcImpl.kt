package com.example.demo.login.domain.repository.jdbc

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

import com.example.demo.login.domain.model.User
import com.example.demo.login.domain.repository.UserDao



@Repository
class UserDaoJdbcImpl: UserDao {

    @Autowired
    internal var jdbc: JdbcTemplate? = null

    // Userテーブルの件数を取得
    @Throws(DataAccessException::class)
    override fun count(): Int {
        return 0
    }

    // Userテーブルにデータを１件insert
    @Throws(DataAccessException::class)
    override fun insertOne(user: User): Int {

        // 1件登録
        val rowNumber: Int = jdbc!!.update("INSERT INTO m_user(user_id,"
                            + "password,"
                            + "user_name,"
                            + "birthday,"
                            + "age,"
                            + "marriage,"
                            + "role)"
                            + " VALUES(?,?,?,?,?,?,?)"
                            , user.userId
                            , user.password
                            , user.userName
                            , user.birthday
                            , user.age
                            , user.marriage
                            , user.role
        )

        return rowNumber
    }

    // Userテーブルのデータを1件取得
    @Throws(DataAccessException::class)
    override fun selectOne(userId: String): User? {
        return null
    }

    // Userテーブルの全データを取得
    @Throws(DataAccessException::class)
    override fun selectMany(): MutableList<User>? {
        return null
    }

    // Userテーブルを1件更新
    @Throws(DataAccessException::class)
    override fun updateOne(user: User): Int {
        return 0
    }

    // Userテーブルを1件削除
    @Throws(DataAccessException::class)
    override fun deleteOne(userId: String): Int {
        return 0
    }

    // Userテーブルの全データをCSVに出力
    @Throws(DataAccessException::class)
    override fun userCsvOut() {

    }
}