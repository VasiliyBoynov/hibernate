package ru.geekbrains.MyHibernate.Lesson5;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.MyHibernate.PrepareDataApp;

import java.util.List;

public class CrudApp {
    private static SessionFactory factory;

    public static void init() {
        PrepareDataApp.forcePrepareData();
        factory = new Configuration()
                .configure("configs/Lesson5/hibernate.cfg.xml")
                .buildSessionFactory();
    }


    public static void shutdown() {
        factory.close();
    }

    public static void createExample() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products item1 = new Products(1L,"Book","100.123");
            Products item2 = new Products("Tea","0.99");

            System.out.println(item1 +"\n"+ item2);
            session.save(item1);
            session.save(item2);
            System.out.println(item1 +"\n"+ item2);
            session.getTransaction().commit();

        }
    }

    public static void readAndPrintExample() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products item = session.get(Products.class, 1L);
            Products item1 = session.get(Products.class, 100L);
            System.out.println(item);
            System.out.println(item1);
            List<Products> allProducts = session.createQuery("from Products p where p.id>3").getResultList();
            System.out.println(allProducts);
            session.getTransaction().commit();
        }

    }
    public static void readAll(){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            List<Products> allProducts = session.createQuery("from Products p").getResultList();
            System.out.println(allProducts);
            /*for (Products product : allProducts) {
                product.setTitle(product.getTitle()+"_1");
            }
            System.out.println(allProducts);*/
            session.getTransaction().commit();
        }

    }

    public static void updateExample() {
        System.out.println("\n------------------------\nизменение строки с ID = 1" );
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products item = session.get(Products.class, 1L);
            System.out.println(item);
            item.setCost("10000");
            session.getTransaction().commit();
            System.out.println(item);

        }
    }

    public static void deleteExample() {

        System.out.println("\n------------------------\nудаление строки с ID = 1" );
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products item = session.get(Products.class, 1L);
            session.delete(item);
            session.getTransaction().commit();
        }
    }

    public static void main(String[] args) {
        try {
            init();
            createExample();
             readAndPrintExample();
             readAll();
             updateExample();
             readAll();
             deleteExample();
             readAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }
}
