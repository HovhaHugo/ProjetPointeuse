package StarkManagement.Model;

public class Employee {
        private int identifiant;
        private String  surnameEmployee;
        private String  nameEmployee;
        private double stockHoure;
        private Department department;

        public Employee() {
                this.identifiant = 0;
                this.surnameEmployee ="";
                this.nameEmployee = "";
                this.stockHoure = 0d;
                this.department = null;
        }

        public Employee(int identifiant, String surnameEmployee, String nameEmployee, double stockHoure, Department department) {
                this.identifiant = identifiant;
                this.surnameEmployee = surnameEmployee;
                this.nameEmployee = nameEmployee;
                this.stockHoure = stockHoure;
                this.department  = department;
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

}
