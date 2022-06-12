package StarkManagement.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class to represent a department inside the compagny
 */
public class Department implements Serializable {

    private String nameDepartment;
    private ArrayList<Employee> listEmployee;

    /**
     * defaut constructor of the departement
     */
    public Department() {
        this.listEmployee = new ArrayList<Employee>();
    }

    /**
     * Constructor of a departement by his name
     * @param nameDepartment
     */
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
