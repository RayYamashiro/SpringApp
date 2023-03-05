package ru.yamashiro.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")  // все адреса в контроллере будут иметь префикс /first
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name" , required = false) String name , @RequestParam(value = "surname", required = false) String surname) // required = false - если в запросе не будет наших параметров, то в них будет лежать null
    {
        System.out.println("Hello " + name + " " + surname);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage()
    {
        return "first/goodbye";
    }
}
