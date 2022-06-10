package StarkManagement.Model;

import StarkManagement.Controller.Hours;
import StarkManagement.Controller.ScoreShort;

import java.io.Serializable;
import java.util.ArrayList;

import static StarkManagement.Model.FileManipulator.exportPointeuseScore;
import static StarkManagement.Model.FileManipulator.importPointeuseScore;

public class Score implements Serializable
{
    private Employee employee;
    private Hours heure;
    private static ArrayList<Score> historique;

    private static ArrayList<Score> historiqueTemp;



    public Score(Employee employee, Hours heure) {
        this.employee = employee;
        this.heure = heure;
        historiqueTemp.add(this);
    }
    public Score(ScoreShort scoreShort){
        this.employee =Employee.getEmplyeeParId(scoreShort.getEmployeeId());
        this.heure=scoreShort.getHeure();
        employee.addScore(this);
        historiqueTemp.add(this);
    }
    public static void SaveListScore()
    {
        ArrayList<Score> temp = new ArrayList<>();
        temp.addAll(historique);
        temp.addAll(historiqueTemp);
        exportPointeuseScore(temp);
        historique = importPointeuseScore();
        historiqueTemp.clear();
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