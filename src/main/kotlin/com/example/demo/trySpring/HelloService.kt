package com.example.demo.trySpring

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class HelloService {

    @Autowired
    private lateinit var helloRepository: HelloRepository

    fun findOne(id: Int): Employee {

        // 1件検索実行
        val map: Map<String, Any> = helloRepository.findOne(id)

        // Mapから値を取得
        val employeeId: Int = map.get("employee_id") as Int
        val employeeName: String = map.get("employee_name") as String
        val age: Int = map.get("age") as Int

        // Employeeクラスに値をセット
        val employee: Employee = Employee(employeeId, employeeName, age)

        return employee
    }

}