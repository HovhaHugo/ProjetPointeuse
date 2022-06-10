package StarkManagement.Controller;

import StarkManagement.Model.Company;
import StarkManagement.Model.Employee;
import StarkManagement.Model.FileManipulator;

import java.io.*;

public class CompanyController {
     static Company company;

    public CompanyController(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public static void exportCompany(Company company){
        FileManipulator.exportCompany(company);
    }
    public static void importCompany(){
        company = FileManipulator.importCompany();
    }






}
