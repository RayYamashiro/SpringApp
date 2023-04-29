package ru.yamashiro.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.SpringTemplateEngine;
import ru.yamashiro.springcourse.models.Person;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {


    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index()
    {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class)); // можно указать такой rowmapper и не писать свой при условии, что наши поля свопада.т названиями и количествами с колонками таблтицы
    }

    public Person show(int id)
    {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?" , new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Optional<Person> show(String email)
    {
        return jdbcTemplate.query("SELECT * FROM Person WHERE email = ?" , new Object[]{email} , new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public void save(Person person)
    {
        jdbcTemplate.update("INSERT INTO Person(name, age ,email, address) VALUES(?,?,?,?)" , person.getName(), person.getAge(), person.getEmail(),  person.getAddress());
    }

    public void update(int id , Person updatedPerson)
    {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=?, address=? WHERE id=?" , updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), updatedPerson.getAddress(),  id);
    }

    public void delete(int id)
    {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?" , id);
    }


    //Тестируем производительность пакетной вставки

    public void testMultipleUpdate()
    {
        List<Person> people = create1000People();

        long before = System.currentTimeMillis();

        for (Person person : people)
        {
            jdbcTemplate.update("INSERT INTO Person VALUES(1,?,?,?)" ,person.getId(),  person.getName(), person.getAge(), person.getEmail() );
        }


        long after = System.currentTimeMillis();

        System.out.println("Time " +  (after - before));
    }

    public void testBatchUpdate()
    {
        List<Person> people = create1000People();
        long before = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO Person VALUES (?, ? , ? ,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, people.get(i).getId());
                        ps.setString(2, people.get(i).getName());
                        ps.setInt(3, people.get(i).getAge());
                        ps.setString(4, people.get(i).getEmail());
                    }

                    @Override
                    public int getBatchSize() {
                        return people.size();
                    }
                });

        long after = System.currentTimeMillis();
        System.out.println("Time" + (after-before));
    }
    private List<Person> create1000People()
    {
        List<Person> people = new ArrayList<>();

        for(int i =0; i < 1000; i++)
        {
            people.add(new Person(i , "Name" + i, 30 , "test" + i + "@mail.ru", "some address"));
        }

        return people;
    }



}
