package ru.yamashiro.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")  // все адреса в контроллере будут иметь префикс /first
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name" , required = false) String name , @RequestParam(value = "surname", required = false) String surname, Model model) // required = false - если в запросе не будет наших параметров, то в них будет лежать null
    {
        //System.out.println("Hello " + name + " " + surname);

        model.addAttribute("message", "Hello " + name + " " + surname);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage()
    {
        return "first/goodbye";
    }
    @GetMapping("/calculator")
    public String calculator(@RequestParam(value = "a" , required = false) Integer a , @RequestParam(value = "b" , required = false) Integer b , @RequestParam(value = "command" , required = false) String command, Model model)
    {
        if(command != null) {
            switch (command) {
                case "multiplication":
                    model.addAttribute("answer", "Answer is: " + a * b);
                    break;
                case "addition":
                    model.addAttribute("answer", "Answer is: " + (a + b));
                    break;
                case "subtraction":
                    model.addAttribute("answer", "Answer is: " + (a - b));
                    break;
                case "division":
                    model.addAttribute("answer", "Answer is: " + ((float)a / b));
                    break;
            }
            return "first/calculator";
        }else
            return "first/calculator";
    }

}
