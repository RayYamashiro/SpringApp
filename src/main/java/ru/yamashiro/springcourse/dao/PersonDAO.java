package ru.yamashiro.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.yamashiro.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    //замена бд
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(1, "Tom"));
        people.add(new Person(2, "Bob"));
        people.add(new Person(3, "Mike"));
        people.add(new Person(4, "Katy"));
    }

    public List<Person> index()
    {
        return people;
    }

    public Person show(int id)
    {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
}
