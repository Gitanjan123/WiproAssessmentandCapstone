//package Employee.DatabaseConnectivity;
//
//public class Employee {
//    private int eid;
//    private String ename;
//    private String dept;
//    private String salary;
//
//    public int getEid() {
//        return eid;
//    }
//
//    public void setEid(int eid) {
//        this.eid = eid;
//    }
//
//    public String getEname() {
//        return ename;
//    }
//
//    public void setEname(String ename) {
//        this.ename = ename;
//    }
//
//    public String getDept() {
//        return dept;
//    }
//
//    public void setDept(String dept) {
//        this.dept = dept;
//    }
//
//    public String getSalary() {
//        return salary;
//    }
//
//    public void setSalary(String salary) {
//        this.salary = salary;
//    }
//}

package Employee.DatabaseConnectivity;

public class Employee {
    private int eid;
    private String ename;
    private String dept;
    private String salary;

    public int getEid() { return eid; }
    public void setEid(int eid) { this.eid = eid; }

    public String getEname() { return ename; }
    public void setEname(String ename) { this.ename = ename; }

    public String getDept() { return dept; }
    public void setDept(String dept) { this.dept = dept; }

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }

    @Override
    public String toString() {
        return "Employee [eid=" + eid + ", name=" + ename + 
               ", dept=" + dept + ", salary=" + salary + "]";
    }
}