package StarkManagement.Model;

import Common.Hours;
import Common.ScoreShort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import static StarkManagement.Model.FileManipulator.exportPointeuseScore;
import static StarkManagement.Model.FileManipulator.importPointeuseScore;

public class Score implements Serializable
{
    private Employee employee;
    private Hours heure;

    public static ArrayList<Score> historique ;
    public static ArrayList<Score> historiqueTemp ;

    public Score(Employee employee, Hours heure) {
        this.employee = employee;
        this.heure = heure;
        historiqueTemp.add(this);
    }

    public Score(ScoreShort scoreShort){
        this.employee = Employee.getEmplyeeParId(scoreShort.getEmployeeId());
        this.heure= scoreShort.getHours();
        employee.addScore(this);
        if (historique==null)
        {
            historique = new ArrayList<>();
        }
        if (historiqueTemp==null)
        {
            historiqueTemp = new ArrayList<>();
        }
        historiqueTemp.add(this);
    }

    public static void SaveListScore() {
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
