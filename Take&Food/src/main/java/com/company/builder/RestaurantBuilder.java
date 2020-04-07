package com.company.builder;

import com.company.collections.Roles;
import com.company.collections.SocialStatus;
import com.company.dao.AdministratorDAO;
import com.company.dao.UserDAO;
import com.company.models.Administrator;
import com.company.models.Restaurant;
import com.company.models.User;

import java.util.Objects;
import java.util.Scanner;

public class RestaurantBuilder {
    public static Restaurant restaurant() {
        Restaurant entity = new Restaurant();
        UserDAO dao = new UserDAO();
        Scanner scan = new Scanner(System.in);
        System.out.print("Choose administrator from existing");
        dao.load();

        while(true) {
            for (User user: dao.getAll()) {
                System.out.print(user.toString() + "\n");
            }
            System.out.print("Select one's id: ");

            String id = scan.next();
            User tmp = dao.getByKey(id);

            if(Objects.isNull(tmp)) {
                System.out.print("Invalid input data");
                continue;
            }

            tmp.setRole(Roles.ADMINISTRATOR);
            tmp.setStatus(SocialStatus.WORKER);
            entity.addAdmin(tmp);
            dao.updateOrCreate(tmp);
            break;
        }

        return entity;
    }

}
