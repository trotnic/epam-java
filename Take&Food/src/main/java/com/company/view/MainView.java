package com.company.view;

import com.company.dao.PersonDAO;
import com.company.models.Person;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class MainView {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
//            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver ());
            String url = "jdbc:oracle:thin:vladislav/oracle@localhost:1521:takeandfood";
            Connection connection = DriverManager.getConnection(url);
//            Statement statement = connection.prepareStatement("INSERT INTO PERSON VALUES(SQ_PERSON.nextval, 'BILL', 'GAITS', '112 Yellow Hill', 'LOLKEK');");
//            statement.execute
            Statement stmnt = connection.createStatement();
            stmnt.executeUpdate("INSERT INTO PERSON(ID, NAME, LOGIN, PASSWORD, EMAIL) VALUES(SQ_PERSON.nextval, 'URAURA', 'GAITS', '112 Yellow Hill', 'LOLKEK');");
//            connection.prepareStatement().executeUpdate();
//            System.out.println(rs.getString("dummy"));
//            ResultSet rs = connection.prepareStatement("SELECT * FROM PERSON").executeQuery();
//            while(rs.next()) {
//                System.out.println(rs.getString("NAME") + " " + rs.getString("EMAIL"));
//            }
            connection.commit();
            connection.close();
//            connection.close();
        } catch(Exception e) {

        }



//        Person pers = new Person();
//        PersonDAO dao = new PersonDAO();
//        dao.get("LOLKEK");
//        dao.create(pers);
    }
}
//
//import com.company.builder.*;
//import com.company.dao.*;
//import com.company.models.*;
//
//import java.util.Scanner;
//
//public class MainView {
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        System.out.println("1)Create entity\n2)Manage entities\n3)Exit\n");
//        int choice = scan.nextInt();
//        switch (choice){
//            case 1:
//                System.out.println("1)User;\n2)Restaurant;\n3)Announcement;\n4)Position;\n5)Order;\n");
//                int entity = scan.nextInt();
//                switch (entity){
//                    case 1:
//                        Person person = UserBuilder.userConsole();
//                        PersonDAO personDAO = new PersonDAO();
//                        personDAO.load();
//                        personDAO.create(person);
//                        personDAO.save();
//                        System.out.println("User created:" + person.toString());
//                        break;
//                    case 2:
//                        Restaurant restaurant = RestaurantBuilder.restaurant();
//                        RestaurantDAO restaurantDAO = new RestaurantDAO();
//                        restaurantDAO.load();
//                        restaurantDAO.create(restaurant);
//                        restaurantDAO.save();
//                        System.out.println("Restaurant created:" + restaurant.toString());
//                        break;
//                    case 3:
//                        Announcement announcement = AnnouncementBuilder.consoleAnnouncement();
//                        AnnouncementDAO announcementDAO = new AnnouncementDAO();
//                        announcementDAO.load();
//                        announcementDAO.create(announcement);
//                        announcementDAO.save();
//                        System.out.println("Announcement created:" + announcement.toString());
//                        break;
//                    case 4:
//                        Dish dish = PositionBuilder.consolePosition();
//                        PositionDAO positionDAO = new PositionDAO();
//                        positionDAO.load();
//                        positionDAO.create(dish);
//                        positionDAO.save();
//                        System.out.println("Order created:" + dish.toString());
//                        break;
//                    case 5:
//                        Order order = OrderBuilder.order();
//                        OrderDAO orderDAO = new OrderDAO();
//                        orderDAO.load();
//                        orderDAO.create(order);
//                        orderDAO.save();
//                        System.out.println("Order created:" + order.toString());
//                        break;
//                    default:
//                        System.out.println("Invalid input data");
//                        break;
//                }
//                break;
//            case 2:
//                System.out.println("1)User;\n2)Restaurant;\n3)Announcement;\n4)Position;\n5)Order;\n");
//                entity = scan.nextInt();
//                switch (entity){
//                    case 1:
//                        PersonDAO personDAO = new PersonDAO();
//                        personDAO.load();
//                        System.out.println("Users loaded");
//                        for (Person element: personDAO.getAll()) {
//                            System.out.println(element.toString());
//                        }
//                        break;
//                    case 2:
//                        RestaurantDAO restaurantDAO = new RestaurantDAO();
//                        restaurantDAO.load();
//                        System.out.println("Restaurants loaded");
//                        for (Restaurant element: restaurantDAO.getAll()) {
//                            System.out.println(element.toString());
//                        }
//                        break;
//                    case 3:
//                        AnnouncementDAO announcementDAO = new AnnouncementDAO();
//                        announcementDAO.load();
//                        System.out.println("Announcements loaded");
//                        for (Announcement element: announcementDAO.getAll()) {
//                            System.out.println(element.toString());
//                        }
//                        break;
//                    case 4:
//                        PositionDAO positionDAO = new PositionDAO();
//                        positionDAO.load();
//                        System.out.println("Positions loaded");
//                        for (Dish element: positionDAO.getAll()) {
//                            System.out.println(element.toString());
//                        }
//                        break;
//                    case 5:
//                        OrderDAO orderDAO = new OrderDAO();
//                        orderDAO.load();
//                        System.out.println("Orders loaded");
//                        for (Order element: orderDAO.getAll()) {
//                            System.out.println(element.toString());
//                        }
//                        break;
//                    default:
//                        System.out.println("Invalid input data");
//                        break;
//                }
//                break;
//            case 3:
//                System.exit(0);
//            default:
//                System.out.println("Invalid input data");
//                break;
//        }
//        main(args);
//    }
//}
