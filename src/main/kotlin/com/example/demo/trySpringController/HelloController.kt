package com.example.demo.trySpringController

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
public class HelloController {

    @GetMapping("/hello")
    fun getHello() : String {

        // hello.htmlに画面遷移
        return "hello"
    }
}