package com.company.builder;

import com.company.models.Position;

import java.util.Scanner;

public class PositionBuilder {
    public static Position consolePosition() {
        Position entity = new Position();
        Scanner scan = new Scanner(System.in);

        System.out.print("Set position name");
        entity.setName(scan.nextLine());
        System.out.print("Set position amount");
        entity.setAmount(scan.nextLine());

        return entity;
    }
}
