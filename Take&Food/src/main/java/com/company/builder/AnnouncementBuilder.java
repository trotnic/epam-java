package com.company.builder;

import com.company.dao.RestaurantDAO;
import com.company.models.Announcement;
import com.company.models.Restaurant;

import java.util.Objects;
import java.util.Scanner;

public class AnnouncementBuilder {
    public static Announcement consoleAnnouncement() {
        Scanner scan = new Scanner(System.in);
        Announcement entity = new Announcement();

        System.out.print("Select restaurant-announcer from existing;");
        while(true) {

            RestaurantDAO dao = new RestaurantDAO();
            dao.load();
            for (Restaurant item: dao.getAll()) {
                System.out.println(item.toString());
            }
            String id = scan.next();
            Restaurant restaurant = dao.getByKey(id);
            if(Objects.isNull(restaurant)) {
                System.out.print("Invalid input data;");
                continue;
            }
            break;
        }

        int choice = 0;

        while(choice != 2) {
            System.out.print("\n1)Add new position;\n2)End;");
            choice = scan.nextInt();
            switch(choice) {
                case 1:
                    entity.addPosition(PositionBuilder.consolePosition());
                    break;
                case 2:
                    break;
                default:
                    System.out.print("Invalid input data;\n");
            }
        }

        return entity;

    }
}
