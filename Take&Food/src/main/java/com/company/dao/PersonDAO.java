package com.company.dao;

import com.company.models.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonDAO extends DAO<Person, String> {
    public PersonDAO() throws ClassNotFoundException {
    }
//    private static String path = "data/user.dat";

//    @Override
//    public String path() {
//        return path;
//    }

    @Override
    public void create(Person item) throws SQLException {
//        String sql = "INSERT INTO PERSON (id, name, login, password, email) VALUES (SQ_PERSON.nextval, ?, ?, ?, ?)";

        String sql = "INSERT INTO PERSON VALUES (SQ_PERSON.nextval, 'Chary', 'Mess', '112 Yellow Hill', 'LOLKEK')";
        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1, "bill");
//        statement.setString(2, "Bill Gates");
//        statement.setString(3, "secretpass");
//        statement.setString(4, "bill.gates@microsoft.com");

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new user was inserted successfully!");
        }
    }

    @Override
    public Person get(String key) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM person");
            while(result.next())
            {
                System.out.println("works");
                System.out.println(result.getString(1));
            }
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public void update(Person item, String... values) {

    }

    @Override
    public void delete(Person item) {

    }
}
