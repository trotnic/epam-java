package com.company.builder;

import com.company.dao.AnnouncementDAO;
import com.company.models.Announcement;
import com.company.models.Order;

import java.util.Scanner;

public class OrderBuilder {
    public static Order order() {
        Order entity = new Order();
        Scanner scan = new Scanner(System.in);
        System.out.print("Select announcement from existing:\n");
        AnnouncementDAO dao = new AnnouncementDAO();
        dao.load();
        for (Announcement item: dao.getAll()) {
            System.out.print(item.toString() + "\n");
        }
//        int flag = 1;
//        while(flag != 0) {
            System.out.print("Select one's id: ");
            String id = scan.nextLine();
            entity.setOrderItem(dao.getByKey(id));
//            System.out.print("Type 0 to exit");
//        }

        return entity.getOrderItem() != null ? entity : null;
    }
}
