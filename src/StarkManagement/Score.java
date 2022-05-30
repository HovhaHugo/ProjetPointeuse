package StarkManagement;

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
import Pointeuse.Controller.Hours;
import StarkManagement.Model.Employee;

public class Score
{
    private Employee employee;
    private Hours heure;
    private Historique Historique;


    public Score(Employee employee, Hours heure, StarkManagement.Historique historique) {
        this.employee = employee;
        this.heure = heure;
        Historique = historique;
    }

    public Hours getHeure() {
        return heure;
    }

    public void setHeure(Hours heure) {
        this.heure = heure;
    }

    public StarkManagement.Historique getHistorique() {
        return Historique;
    }

    public void setHistorique(StarkManagement.Historique historique) {
        Historique = historique;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
