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
public class TakeandfoodApplication {
	public static void main(String[] args) {
		SpringApplication.run(TakeandfoodApplication.class, args);
	}
}
