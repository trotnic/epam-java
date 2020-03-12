package com.company.view;

import com.company.dao.*;
import com.company.models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MainView {

    public static void main(String[] args) {
        /*Методы для создания сущностей и просмотра существующих*/
        Scanner sc = new Scanner(System.in);
        System.out.println("Выберите то, что хотите сделать : 1)Создать сущность;2)Просмотреть существующие сущности в файлах;3)Завершить работу;");
        int choose = sc.nextInt();
        switch (choose){
            case 1:
                System.out.println("1)Administrator;\n2)Announcement;\n3)Common User;\n4)Order;\n5)Position;\n6)Restaurant;\n7)User;");
                int entity = sc.nextInt();
                switch (entity){
                    case 1:
                        Administrator administrator = new Administrator();
                        System.out.println("Administrator создан:"+administrator.toString());
                        AdministratorDAO administratorDAO = new AdministratorDAO();
                        administratorDAO.load();
                        administratorDAO.create(administrator);
                        administratorDAO.save();
                        break;
                    case 2:
                        Announcement announcement = new Announcement();
                        System.out.println("Announcement создан:" + announcement.toString());
                        break;
                    case 3:
                        CommonUser commonUser = new CommonUser();
                        System.out.println("Common User создан:"+commonUser.toString());
                        break;
                    case 4:
                        Order order = new Order();
                        System.out.println("Order создан:"+order.toString());
                        break;
                    case 5:
                        Position position = new Position();
                        System.out.println("Position создан:"+position.toString());
                        break;
                    case 6:
                        Restaurant restaurant = new Restaurant();
                        System.out.println("Restaurant создан:"+restaurant.toString());
                        break;
                    case 7:
                        User user = new User();
                        System.out.println("User создан:"+user.toString());
                        break;
                    default:
                        System.out.println("Некорректные данные");
                        break;
                }
                break;
            case 2:
                System.out.println("1)Administrator;\n2)Announcement;\n3)Common User;\n4)Order;\n5)Position;\n6)Restaurant;");
                entity = sc.nextInt();
                switch (entity){
                    case 1:
                        AdministratorDAO administratorDAO = new AdministratorDAO();
                        administratorDAO.load();
                        ArrayList<Administrator> administrators = administratorDAO.getAll();
                        System.out.println("Administrator загружены");
                        for (Administrator element:administrators) {
                            System.out.println(element.toString());
                        }
                        break;
                    case 2:
                        AnnouncementDAO announcementDAO = new AnnouncementDAO();
                        announcementDAO.load();
                        System.out.println("Announcement загружены");
                        ArrayList<Announcement> announcements = announcementDAO.getAll();
                        for (Announcement element:announcements) {
                            System.out.println(element.toString());
                        }
                        break;
                    case 3:
                        CommonUserDAO commonUserDAO = new CommonUserDAO();
                        commonUserDAO.load();
                        System.out.println("Common User загружены");
                        ArrayList<CommonUser>commonUsers = commonUserDAO.getAll();
                        for (CommonUser element:commonUsers) {
                            System.out.println(element.toString());
                        }
                        break;
                    case 4:
                        OrderDAO orderDAO = new OrderDAO();
                        orderDAO.load();
                        System.out.println("Order загружены");
                        ArrayList<Order>orders = orderDAO.getAll();
                        for (Order element:orders) {
                            System.out.println(element.toString());
                        }
                        break;
                    case 5:
                        PositionDAO positionDAO = new PositionDAO();
                        positionDAO.load();
                        System.out.println("Position загружены");
                        ArrayList<Position>positions = positionDAO.getAll();
                        for (Position element:positions) {
                            System.out.println(element.toString());
                        }
                        break;
                    case 6:
                        RestaurantDAO restaurantDAO = new RestaurantDAO();
                        restaurantDAO.load();
                        System.out.println("Restaurant загружены");
                        ArrayList<Restaurant>restaurants = restaurantDAO.getAll();
                        for (Restaurant element:restaurants) {
                            System.out.println(element.toString());
                        }
                        break;
                    default:
                        System.out.println("Некорректные данные");
                        break;
                }
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Некорректные данные");
                break;
        }
        main(args);
    }
}
