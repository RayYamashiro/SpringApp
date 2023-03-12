package ru.yamashiro.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.yamashiro.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    //замена бд
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Tom", 21, "dsadas@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 38 , "dsadads@google.com"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 25 , "ewqwq@yandex.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Katy" , 43, "dsads@doodle.com"));
    }

    public List<Person> index()
    {
        return people;
    }

    public Person show(int id)
    {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person)
    {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id , Person updatedPerson)
    {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
        personToBeUpdated.setAge(updatedPerson.getAge());
    }

    public void delete(int id)
    {
        people.removeIf(p -> p.getId() == id);
    }
}
