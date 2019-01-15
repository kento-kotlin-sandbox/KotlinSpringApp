package com.example.demo.trySpring

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestParam

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping


@Controller
public class HelloController {

    @Autowired
    private lateinit var helloService: HelloService

    @GetMapping("/hello")
    fun getHello() : String {

        // hello.htmlに画面遷移
        return "hello"
    }

    @PostMapping("/hello")
    fun postRequest(@RequestParam("text1") str: String?, model: Model): String {
        // 画面から受け取った文字列をModelに登録
        model.addAttribute("sample", str)

        // helloResponse.htmlに画面遷移
        return "helloResponse"
    }

    @PostMapping("/hello/db")
    fun postDbRequest(@RequestParam("text2") str: String, model: Model): String {

        // ほんとはエラーハンドリングが必要だけど割愛
        // StringからInt型へ変換
        val id: Int = str as Int

        // 1件検索
        val employee: Employee = helloService.findOne(id)

        // 検索結果をModelに登録
        model.addAttribute("id", employee.employeeId)
        model.addAttribute("name", employee.employeeName)
        model.addAttribute("age", employee.age)

        // helloResponseDb.htmlに画面遷移
        return "helloResponseDB"
    }
}