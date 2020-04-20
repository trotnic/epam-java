package com.takeandfood.takeandfood;

import com.takeandfood.takeandfood.DAO.PersonDAO;
import com.takeandfood.takeandfood.beans.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class TakeandfoodApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(TakeandfoodApplication.class, args);
	}


	@Override
	public void run(String... args) {
		System.out.println("WORKS");
		runJDBC();
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	PersonDAO personDAO;

	void runJDBC() {
		List<Person> data = Collections.singletonList(
				new Person.Builder()
						.withEmail("email@email.com")
						.withId((long) 12)
						.withLogin("log9799n")
						.withName("name")
						.withPassword("password")
						.withRole(1)
						.withStatus(1)
						.build()
		);
		try {
			for (Person pers : data
			) {
				personDAO.create(pers);
			}
		} catch (Exception e) {

		}

	}

}
