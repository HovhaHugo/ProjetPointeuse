package StarkManagement.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Department implements Serializable {
    private String nameDepartment;
    private ArrayList<Employee> listEmployee;

    public Department() {
        this.listEmployee = new ArrayList<Employee>();
    }

    public Department(String nameDepartment) {
        this.nameDepartment = nameDepartment;
        this.listEmployee = new ArrayList<Employee>();
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public ArrayList<Employee> getListEmployee() {
        return listEmployee;
    }

    public void setListEmployee(ArrayList<Employee> listEmployee) {
        this.listEmployee = listEmployee;
    }
}
