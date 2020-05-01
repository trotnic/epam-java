//package com.takeandfood.takeandfood.beans;/*
// * @project takeandfood
// * @author vladislav on 5/1/20
// */
//
//import javax.persistence.*;
//import java.util.StringJoiner;
//
//@Entity
//@Table(name = "TEST_PERSON")
//public class Test {
//
//    @Id
//    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return new StringJoiner(", ", Test.class.getSimpleName() + "[", "]")
//                .add("id=" + id)
//                .add("name='" + name + "'")
//                .toString();
//    }
//}
