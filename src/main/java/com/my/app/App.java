package com.my.app;

import com.my.common.Stock;
import com.my.common.StockNew;
import com.my.persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by tianzy on 6/5/14.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Maven + Hibernate + mysql ");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Stock stock = new Stock();
            stock.setStockCode("5717");
            stock.setStockName("STOC");
            session.save(stock);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            StockNew stockNew = new StockNew();
            stockNew.setStockCode("1234");
            stockNew.setStockName("GMDS");
            session.save(stockNew);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

        HibernateUtil.shutdown();
    }
}
