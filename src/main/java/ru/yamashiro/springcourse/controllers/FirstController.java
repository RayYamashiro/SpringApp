package ru.yamashiro.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/first")  // все адреса в контроллере будут иметь префикс /first
public class FirstController {

    @GetMapping("/hello")
    public String helloPage()
    {
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage()
    {
        return "first/goodbye";
    }
}
