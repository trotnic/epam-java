package com.company.view;

import com.company.dao.*;
import com.company.models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MainView {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose one to do:\n1)Create entity\n2)Check entities for existence\n3)Exit\n");
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                System.out.println("1)Administrator;\n2)Announcement;\n3)Common User;\n4)Order;\n5)Position;\n6)Restaurant;\n");
                int entity = sc.nextInt();
                switch (entity){
                    case 1:
                        Administrator administrator = new Administrator();

                        System.out.println("Administrator created:"+administrator.toString());

                        AdministratorDAO administratorDAO = new AdministratorDAO();

                        administratorDAO.load();
                        administratorDAO.create(administrator);
                        administratorDAO.save();

                        System.out.println("Administrator saved");
                        break;
                    case 2:
                        Announcement announcement = new Announcement();

                        System.out.println("Announcement created:" + announcement.toString());

                        AnnouncementDAO announcementDAO = new AnnouncementDAO();

                        announcementDAO.load();
                        announcementDAO.create(announcement);
                        announcementDAO.save();
                        break;
                    case 3:
                        CommonUser commonUser = new CommonUser();

                        System.out.println("Common User created:"+commonUser.toString());

                        CommonUserDAO commonUserDAO = new CommonUserDAO();

                        commonUserDAO.load();
                        commonUserDAO.create(commonUser);
                        commonUserDAO.save();
                        break;
                    case 4:
                        Order order = new Order();

                        System.out.println("Order created:"+order.toString());

                        OrderDAO orderDAO = new OrderDAO();

                        orderDAO.load();
                        orderDAO.create(order);
                        orderDAO.save();
                        break;
                    case 5:
                        Position position = new Position();

                        System.out.println("Position created:"+position.toString());

                        PositionDAO positionDAO = new PositionDAO();

                        positionDAO.load();
                        positionDAO.create(position);
                        positionDAO.save();
                        break;
                    case 6:
                        Restaurant restaurant = new Restaurant();

                        System.out.println("Restaurant created:"+restaurant.toString());

                        RestaurantDAO restaurantDAO = new RestaurantDAO();

                        restaurantDAO.load();
                        restaurantDAO.create(restaurant);
                        restaurantDAO.save();
                        break;
                    default:
                        System.out.println("Invalid input data");
                        break;
                }
                break;
            case 2:
                System.out.println("1)Administrator;\n2)Announcement;\n3)Common User;\n4)Order;\n5)Position;\n6)Restaurant;\n");
                entity = sc.nextInt();
                switch (entity){
                    case 1:
                        AdministratorDAO administratorDAO = new AdministratorDAO();

                        administratorDAO.load();

                        ArrayList<Administrator> administrators = administratorDAO.getAll();

                        System.out.println("Administrator loaded");
                        for (Administrator element:administrators) {
                            System.out.println(element.toString());
                        }
                        break;
                    case 2:
                        AnnouncementDAO announcementDAO = new AnnouncementDAO();

                        announcementDAO.load();
                        System.out.println("Announcement loaded");

                        ArrayList<Announcement> announcements = announcementDAO.getAll();

                        for (Announcement element:announcements) {
                            System.out.println(element.toString());
                        }
                        break;
                    case 3:
                        CommonUserDAO commonUserDAO = new CommonUserDAO();

                        commonUserDAO.load();
                        System.out.println("Common User loaded");

                        ArrayList<CommonUser>commonUsers = commonUserDAO.getAll();

                        for (CommonUser element:commonUsers) {
                            System.out.println(element.toString());
                        }
                        break;
                    case 4:
                        OrderDAO orderDAO = new OrderDAO();

                        orderDAO.load();
                        System.out.println("Order loaded");

                        ArrayList<Order>orders = orderDAO.getAll();

                        for (Order element:orders) {
                            System.out.println(element.toString());
                        }
                        break;
                    case 5:
                        PositionDAO positionDAO = new PositionDAO();

                        positionDAO.load();
                        System.out.println("Position loaded");

                        ArrayList<Position>positions = positionDAO.getAll();

                        for (Position element:positions) {
                            System.out.println(element.toString());
                        }
                        break;
                    case 6:
                        RestaurantDAO restaurantDAO = new RestaurantDAO();

                        restaurantDAO.load();
                        System.out.println("Restaurant loaded");

                        ArrayList<Restaurant>restaurants = restaurantDAO.getAll();

                        for (Restaurant element:restaurants) {
                            System.out.println(element.toString());
                        }
                        break;
                    default:
                        System.out.println("Invalid input data");
                        break;
                }
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Invalid input data");
                break;
        }
        main(args);
    }
}
