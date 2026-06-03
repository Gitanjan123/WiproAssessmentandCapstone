package com.wipro.EmployeeSystemUsingORMDay20;
//
//import java.util.List;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//@Repository
//@Transactional
//public class EmployeeDaoImpl implements EmployeeDao {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    // INSERT
//    public void insertEmployee(Employee obj) {
//        Session session = sessionFactory.getCurrentSession();
//        String sql = "INSERT INTO employee (empId, empName, empDepartment, empSalary, empEmail) "
//                   + "VALUES (:id, :name, :dept, :sal, :email)";
//        session.createNativeQuery(sql)
//               .setParameter("id", obj.getEmpId())
//               .setParameter("name", obj.getEmpName())
//               .setParameter("dept", obj.getEmpDepartment())
//               .setParameter("sal", obj.getEmpSalary())
//               .setParameter("email", obj.getEmpEmail())
//               .executeUpdate();
//        System.out.println("Inserted successfully!");
//    }
//
//    // DISPLAY ALL
//    public List<Employee> displayAllEmployees() {
//        Session session = sessionFactory.getCurrentSession();
//        String sql = "SELECT * FROM employee";
//        return session.createNativeQuery(sql, Employee.class).list();
//    }
//
//    // SEARCH BY ID
//    public Employee searchEmployee(int empId) {
//        Session session = sessionFactory.getCurrentSession();
//        String sql = "SELECT * FROM employee WHERE empId = :eid";
//        return (Employee) session.createNativeQuery(sql, Employee.class)
//                .setParameter("eid", empId)
//                .uniqueResult();
//    }
//
//    // UPDATE SALARY
//    public void updateSalary(int empId, double newSalary) {
//        Session session = sessionFactory.getCurrentSession();
//        String sql = "UPDATE employee SET empSalary = :sal WHERE empId = :eid";
//        session.createNativeQuery(sql)
//               .setParameter("sal", newSalary)
//               .setParameter("eid", empId)
//               .executeUpdate();
//        System.out.println("Updated successfully!");
//    }
//
//    // DELETE
//    public void deleteEmployee(int empId) {
//        Session session = sessionFactory.getCurrentSession();
//        String sql = "DELETE FROM employee WHERE empId = :eid";
//        session.createNativeQuery(sql)
//               .setParameter("eid", empId)
//               .executeUpdate();
//        System.out.println("Deleted successfully!");
//    }
//}

// These method is Transactional method
// now which we are doing it manual transactional method


import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    // INSERT
    public void insertEmployee(Employee obj) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String sql = "INSERT INTO employee (empId, empName, empDepartment, empSalary, empEmail) "
                   + "VALUES (:id, :name, :dept, :sal, :email)";
        session.createNativeQuery(sql)
               .setParameter("id",    obj.getEmpId())
               .setParameter("name",  obj.getEmpName())
               .setParameter("dept",  obj.getEmpDepartment())
               .setParameter("sal",   obj.getEmpSalary())
               .setParameter("email", obj.getEmpEmail())
               .executeUpdate();

        tx.commit();
        session.close();
        System.out.println("Inserted Successfully!");
    }

    // DISPLAY ALL
    public List<Employee> displayAllEmployees() {
        Session session = sessionFactory.openSession();

        String sql = "SELECT * FROM employee";
        List<Employee> list = session.createNativeQuery(sql, Employee.class).list();

        session.close();
        return list;
    }

    // SEARCH BY ID
    public Employee searchEmployee(int empId) {
        Session session = sessionFactory.openSession();

        String sql = "SELECT * FROM employee WHERE empId = :eid";
        Employee e = (Employee) session.createNativeQuery(sql, Employee.class)
                        .setParameter("eid", empId)
                        .uniqueResult();

        session.close();
        return e;
    }

    // UPDATE SALARY
    public void updateSalary(int empId, double newSalary) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String sql = "UPDATE employee SET empSalary = :salary WHERE empId = :empId";
        NativeQuery query = session.createNativeQuery(sql);
        query.setParameter("salary", newSalary);
        query.setParameter("empId", empId);
        int rows = query.executeUpdate();

        tx.commit();
        session.close();

        if (rows > 0) {
            System.out.println("Employee Updated Successfully");
        } else {
            System.out.println("Employee Not Found");
        }
    }

    // DELETE
    public void deleteEmployee(int empId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String sql = "DELETE FROM employee WHERE empId = :id";
        NativeQuery query = session.createNativeQuery(sql);
        query.setParameter("id", empId);
        int rows = query.executeUpdate();

        tx.commit();
        session.close();

        System.out.println("Deleted " + rows + " row");
    }
}
