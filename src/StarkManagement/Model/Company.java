package StarkManagement.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Company implements Serializable {
    private String nameCompany;
    private ArrayList<Department> listDepartment;


    public Company() {
        this.nameCompany = null;
        this.listDepartment = new ArrayList<>();
    }

    public Company(String nameCompany, ArrayList<Department> listDepartment) {
        this.nameCompany = nameCompany;
        this.listDepartment = listDepartment;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public ArrayList<Department> getListDepartment() {
        return listDepartment;
    }

    public void setListDepartment(ArrayList<Department> listDepartment) {
        this.listDepartment = listDepartment;
    }
}
