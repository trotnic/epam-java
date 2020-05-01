//package com.takeandfood.takeandfood.dao;/*
// * @project takeandfood
// * @author vladislav on 5/1/20
// */
//
//import com.takeandfood.takeandfood.beans.Test;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class TestDAO {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    public List<Test> getAll() {
//        Session session = this.sessionFactory.getCurrentSession();
//        List<Test> result = session.createQuery("from Test").list();
//        for (Test pers: result
//             ) {
//            System.out.println(pers.toString());
//        }
//        return result;
//    }
//}
