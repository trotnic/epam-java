package com.company.builder;

import com.company.models.Dish;

import java.util.Scanner;

public class PositionBuilder {
    public static Dish consolePosition() {
        Dish entity = new Dish();
        Scanner scan = new Scanner(System.in);

        System.out.print("Set position name");
        entity.setName(scan.nextLine());
        System.out.print("Set position amount");
        entity.setAmount(scan.nextLine());

        return entity;
    }
}
