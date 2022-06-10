package StarkManagement.Model;

import java.io.*;
import java.util.ArrayList;

public class Employee implements Serializable {
        private int identifiant;
        private String  surnameEmployee;
        private String  nameEmployee;
        private double stockHoure;
        private Department department;

        public static ArrayList <Employee> listEmployee = new ArrayList<Employee>();;
        public ArrayList <Score> historique;

        public Employee() {
                identifiant = 0;
                surnameEmployee ="";
                nameEmployee = "";
                stockHoure = 0d;
                department = null;
                new ArrayList<Score>();
                listEmployee.add(this);

        }

        public Employee(int identifiant, String surnameEmployee, String nameEmployee, double stockHoure, Department department) {
                this.identifiant = identifiant;
                this.surnameEmployee = surnameEmployee;
                this.nameEmployee = nameEmployee;
                this.stockHoure = stockHoure;
                this.department  = department;
                new ArrayList<Score>();
                listEmployee.add(this);
        }

        public int getIdentifiant() {
                return identifiant;
        }

        public void setIdentifiant(int identifiant) {
                this.identifiant = identifiant;
        }

        public String getSurnameEmployee() {
                return surnameEmployee;
        }

        public void setSurnameEmployee(String surnameEmployee) {
                this.surnameEmployee = surnameEmployee;
        }

        public String getNameEmployee() {
                return nameEmployee;
        }

        public void setNameEmployee(String nameEmployee) {
                this.nameEmployee = nameEmployee;
        }

        public double getStockHoure() {
                return stockHoure;
        }

        public void setStockHoure(double stockHoure) {
                this.stockHoure = stockHoure;
        }

        public Department getDepartment() {
                return department;
        }

        public void setDepartment(Department department) {
                this.department = department;
        }

        public void addStockHoure(double houreParam){
                this.stockHoure +=houreParam;
        }

        public void subStockHoure(double houreParam){
                this.stockHoure -=houreParam;
        }

        public ArrayList<Score> getHistorique() {
                return historique;
        }

        public void setHistorique(ArrayList<Score> historique) {
                this.historique = historique;
        }

        public static Employee getEmplyeeParId(int id){

                for (Employee e: listEmployee ){
                        if (e.getIdentifiant() == id){
                                return e;
                        }
                }
                return null;
        }

        public Score getLastScore(){
                if (historique.isEmpty()) return null;
                else return historique.get(historique.size()-1);
        }

        public void addScore(Score score){
                historique.add(score);
        }

        public static void setEmployeeList(Company c){

                for (Department d : c.getListDepartment()){
                        listEmployee.addAll(d.getListEmployee());
                }
        }

        public static void deleteEmployeeParId(int id){
                for (Employee e: listEmployee ){
                        if (e.getIdentifiant() == id){
                               listEmployee.remove(e);
                        }
                }
        }

        public static void addEmployee(Employee e){
                listEmployee.add(e);
        }



        @Override
        public String toString() {
                return "Employee{" +
                        "identifiant=" + identifiant +
                        ", surnameEmployee='" + surnameEmployee + '\'' +
                        ", nameEmployee='" + nameEmployee + '\'' +
                        ", stockHoure=" + stockHoure +
                        ", department=" + department.getNameDepartment() +
                        '}';
        }


}
