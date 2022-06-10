package StarkManagement;

import Pointeuse.Controller.Hours;
import StarkManagement.Model.Employee;

public class Score
{
    private Employee employee;
    private Hours heure;



    public Score(Employee employee, Hours heure) {
        this.employee = employee;
        this.heure = heure;
    }

    public Hours getHeure() {
        return heure;
    }

    public void setHeure(Hours heure) {
        this.heure = heure;
    }





    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
