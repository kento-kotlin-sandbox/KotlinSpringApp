package com.example.demo.login.domain.repository

import org.springframework.dao.DataAccessException
import com.example.demo.login.domain.model.User


interface UserDao {

    // Userテーブルの件数を取得
    @Throws(DataAccessException::class)
    fun count(): Int

    // Userテーブルにデータを１件Insert
    @Throws(DataAccessException::class)
    fun insertOne(user: User): Int

    @Throws(DataAccessException::class)
    fun selectOne(userId: String): User?

    @Throws(DataAccessException::class)
    fun selectMany(): MutableList<User>?

    @Throws(DataAccessException::class)
    fun updateOne(user: User): Int

    @Throws(DataAccessException::class)
    fun deleteOne(userId: String): Int

    @Throws(DataAccessException::class)
    fun userCsvOut()
}
