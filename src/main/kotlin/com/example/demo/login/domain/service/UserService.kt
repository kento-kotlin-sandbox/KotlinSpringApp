package com.example.demo.login.domain.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.example.demo.login.domain.model.User
import com.example.demo.login.domain.repository.UserDao


@Service
class UserService {

    @Autowired
    internal var dao: UserDao? = null

    // insert用メソッド
    fun insert(user: User): Boolean {

        // insert実行
        val rowNumber: Int = dao!!.insertOne(user)

        var result = false

        if(rowNumber > 0) {
            result = true
        }

        return result
    }
}