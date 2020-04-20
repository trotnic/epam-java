package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import com.takeandfood.takeandfood.DAO.PersonDAO;
import com.takeandfood.takeandfood.beans.Person;
import com.takeandfood.takeandfood.collections.Roles;
import com.takeandfood.takeandfood.collections.SocialStatuses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PersonDAO personDAO;

    @RequestMapping("/greeting")
//    @Transactional
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        try {
            System.out.println("GREETING");

            personDAO.create(
                    new Person.Builder()
                            .withEmail("email@email.com")
                            .withId((long) 13)
                            .withLogin("logAADSn")
                            .withName("name")
                            .withPassword("password")
                            .withRole(1)
                            .withStatus(1)
                            .build()
            );

//            jdbcTemplate.execute("INSERT INTO PERSON (name, login, password, email, STATUS, ROLE) VALUES (\"Vladislav\", \"tro\", \"qwerty\", \"sobaka@gmail.com\", 1, 1);");
            jdbcTemplate.query("SELECT * FROM PERSON",
                    (rs, rowNum) -> new Person.Builder()
                            .withEmail(rs.getString("email"))
                            .withId(rs.getLong("id"))
                            .withLogin(rs.getString("login"))
                            .withName(rs.getString("name"))
                            .withPassword(rs.getString("password"))
                            .withRole(rs.getInt("role"))
                            .withStatus(rs.getInt("status"))
                            .build())
                    .forEach(e -> System.out.println(e.toString()));
        } catch(Exception e) {

        }
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }


}
