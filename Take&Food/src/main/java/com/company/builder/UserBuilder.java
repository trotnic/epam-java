//package com.company.builder;
//
//import com.company.collections.Roles;
//import com.company.collections.SocialStatus;
//import com.company.models.Person;
//
//import java.util.Scanner;
//
//public class UserBuilder {
//
//    public static Person userConsole() {
//        Person entity = new Person();
//
//        Scanner scan = new Scanner(System.in);
//
//        System.out.print("Type login:\n");
//        entity.setLogin(scan.nextLine());
//        System.out.print("Type email:\n");
//        entity.setEmail(scan.nextLine());
//        System.out.print("Type name:\n");
//        entity.setName(scan.nextLine());
//        System.out.print("Choose role:\n1)Administrator;\n2)Common user;");
//        int choice = scan.nextInt();
//        anchor: while(true) {
//            switch(choice) {
//                case 1:
//                    entity.setRole(Roles.ADMINISTRATOR);
//                    entity.setStatus(SocialStatus.WORKER);
//                    break anchor;
//                case 2:
//                    while(true) {
//                        System.out.print("Select user status:\n1)student;\n2)worker;\n3)jobless;\n");
//                        choice = scan.nextInt();
//                        switch (choice) {
//                            case 1:
//                                entity.setStatus(SocialStatus.STUDENT);
//                                break anchor;
//                            case 2:
//                                entity.setStatus(SocialStatus.WORKER);
//                                break anchor;
//                            case 3:
//                                entity.setStatus(SocialStatus.JOBLESS);
//                                break anchor;
//                            default:
//                                System.out.print("Invalid input data");
//                        }
//                    }
//            }
//        }
//
//        return entity;
//    }
//}
