package com.my.app;

/**
 * Created by tianzy on 6/8/14.
 */

import java.util.*;

import com.my.common.one_to_many.Employee;
import com.my.common.one_to_many.Certificate;
import com.my.persistence.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToMany {
    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            factory = HibernateUtil.getSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        OneToMany ME = new OneToMany();
      /* Let us have a set of certificates for the first employee  */
        HashSet set1 = new HashSet();
        set1.add(new Certificate("MCA"));
        set1.add(new Certificate("MBA"));
        set1.add(new Certificate("PMP"));

      /* Add employee records in the database */
        Integer empID1 = ME.addEmployee("Manoj", "Kumar", 4000, set1);

      /* Another set of certificates for the second employee  */
        HashSet set2 = new HashSet();
        set2.add(new Certificate("BCA"));
        set2.add(new Certificate("BA"));

      /* Add another employee record in the database */
        Integer empID2 = ME.addEmployee("Dilip", "Kumar", 3000, set2);

      /* List down all the employees */
        ME.listEmployees();

      /* Update employee's salary records */
        ME.updateEmployee(empID1, 5000);

      /* Delete an employee from the database */
        ME.deleteEmployee(empID2);

      /* List down all the employees */
        ME.listEmployees();
        HibernateUtil.shutdown();

    }

    /* Method to add an employee record in the database */
    public Integer addEmployee(String fname, String lname,
                               int salary, Set cert) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;
        try {
            tx = session.beginTransaction();
            Employee employee = new Employee(fname, lname, salary);
            employee.setCertificates(cert);
            employeeID = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }

    /* Method to list all the employees detail */
    public void listEmployees() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Employee").list();
            for (Iterator iterator1 =
                         employees.iterator(); iterator1.hasNext(); ) {
                Employee employee = (Employee) iterator1.next();
                System.out.print("First Name: " + employee.getFirstName());
                System.out.print("  Last Name: " + employee.getLastName());
                System.out.println("  Salary: " + employee.getSalary());
                Set certificates = employee.getCertificates();
                for (Iterator iterator2 =
                             certificates.iterator(); iterator2.hasNext(); ) {
                    Certificate certName = (Certificate) iterator2.next();
                    System.out.println("Certificate: " + certName.getName());
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to update salary for an employee */
    public void updateEmployee(Integer EmployeeID, int salary) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee =
                    (Employee) session.get(Employee.class, EmployeeID);
            employee.setSalary(salary);
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to delete an employee from the records */
    public void deleteEmployee(Integer EmployeeID) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee =
                    (Employee) session.get(Employee.class, EmployeeID);
            session.delete(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}