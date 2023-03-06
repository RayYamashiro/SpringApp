package ru.yamashiro.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yamashiro.springcourse.dao.PersonDAO;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model)
    {
        // Получим всех людей из DAO и передадим на отображение
        model.addAttribute("people" , personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")  // в id можно поместить любое число и оно поместится в аргументы метода
    public String show(@PathVariable("id") int id, Model model)
    {
        // Получим человека по id
        model.addAttribute("person" , personDAO.show(id));
        return "people/show";
    }
}
