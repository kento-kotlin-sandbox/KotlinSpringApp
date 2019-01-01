package com.example.demo.trySpring

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.jdbc.core.JdbcTemplate

@Repository
class HelloRepository {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    fun findOne(id: Int): Map<String, Any>  {

        // SELECT文
        val query: String = """SELECT
                            | employee_id,
                            | employee_name,
                            | age
                            | FROM employee
                            | WHERE employee_id=?""".trimMargin()

        // 検索実行
        val employee: Map<String, Any> = jdbcTemplate.queryForMap(query, id)

        return employee
    }

}