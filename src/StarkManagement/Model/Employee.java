package StarkManagement.Model;

import java.io.*;
import java.util.ArrayList;

/**
 * Class to represent an Employee and stock his imformations inside the compagny ,like extra hours ,planning ....
 * It also have a List of all the employees
 */
public class Employee implements Serializable {
        private int identifiant;
        private String  surnameEmployee;
        private String  nameEmployee;
        /**Number of extra hours worked */
        private double stockHoure;
        private Department department;

        public static ArrayList <Employee> listEmployee = new ArrayList<>();;
        public ArrayList <Score> historique;
        private Planning planning ;

        /**
         * Constructor of Employee (usually used for a new employee inside the compagny)
         * @param surnameEmployee
         * @param nameEmployee
         * @param department
         */
        public Employee(String surnameEmployee, String nameEmployee, Department department) {
                if(listEmployee.isEmpty())
                        this.identifiant = 0;
                else
                        this.identifiant = listEmployee.get(listEmployee.size()-1).identifiant+1;

                this.surnameEmployee = surnameEmployee;
                this.nameEmployee = nameEmployee;
                this.stockHoure = 0;
                this.department  = department;
                historique = new ArrayList<>();
                planning = new Planning();
                listEmployee.add(this);
        }

        /**
         *Comfort constructor of Employee
         * @param identifiant
         * @param surnameEmployee
         * @param nameEmployee
         * @param stockHoure
         * @param department
         */
        public Employee(int identifiant, String surnameEmployee, String nameEmployee, double stockHoure, Department department) {
                this.identifiant = identifiant;
                this.surnameEmployee = surnameEmployee;
                this.nameEmployee = nameEmployee;
                this.stockHoure = stockHoure;
                this.department  = department;
                historique = new ArrayList<>();
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

        /**
         * Method to add a certain number of hours worked
         * @param houreParam
         */
        public void addStockHoure(double houreParam){
                this.stockHoure +=houreParam;
        }

        /**
         * Method to sustitute a certain number of hours worked
         * @param houreParam
         */
        public void subStockHoure(double houreParam){
                this.stockHoure -=houreParam;
        }

        public ArrayList<Score> getHistorique() {
                return historique;
        }

        public void setHistorique(ArrayList<Score> historique) {
                this.historique = historique;
        }

        public Planning getPlanning() {
                return planning;
        }

        public void setPlanning(Planning planning) {
                this.planning = planning;
        }

        /**
         * Method to get an Employee form the list by his id
         * @param id
         * @return
         */
        public static Employee getEmplyeeParId(int id){

                for (Employee e: listEmployee ){
                        if (e.getIdentifiant() == id){
                                return e;
                        }
                }
                return null;
        }

        /**
         * Method to get the Last Check in the history
         * @return
         */
        public Score getLastScore(){
                if (historique.isEmpty()) return null;
                else return historique.get(historique.size()-1);
        }

        /**
         * Method to add a check to the history of an employee
         * @param score
         */
        public void addScore(Score score){
                historique.add(score);
        }

        public static void setEmployeeList(Company c){

                for (Department d : c.getListDepartment()){
                        listEmployee.addAll(d.getListEmployee());
                }
        }

        /**
         * Method to delete an employee from the list/compgny using his id
         * @param id
         */
        public static void deleteEmployeeParId(int id){
                for (Employee e: listEmployee ){
                        if (e.getIdentifiant() == id){
                               listEmployee.remove(e);
                        }
                }
        }

        /**
         * Method to add an Employee in the List
         * @param e the employee
         */
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
