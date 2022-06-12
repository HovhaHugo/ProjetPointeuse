package StarkManagement.Model;

import Common.Hours;
import Common.ScoreShort;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Score implements Serializable {

    private Employee employee;
    private Hours heure;

    public enum Type{
        IN,
        OUT,
        ANORMAL
    }

    Type type;

    public static int temporaryScoreCreate = 0;
    public final static int temporaryScoreLimit = 5;

    public static ArrayList<Score> historique = new ArrayList<>();

    public Score(Employee employee, Hours heure) {
        this.employee = employee;
        this.heure = heure;
    }

    public Score(ScoreShort scoreShort){
        this.employee = Employee.getEmplyeeParId(scoreShort.getEmployeeId());
        this.heure= scoreShort.getHours();
        employee.addScore(this);

        LocalDateTime now = LocalDateTime.now();
        type = Type.IN;
        for(Score s : Score.historique){
            if(s.getHeure().getDate().equals(now.toLocalDate())) {  //Other check in the same day
                if(s.employee == employee){                         //from the same person
                    if(s.type == Type.IN)
                        type = Type.OUT;
                    if(s.type == Type.OUT)
                        type = Type.ANORMAL;
                    if(s.type == Type.ANORMAL)
                        type = Type.ANORMAL;
                }
            }
        }

        historique.add(this);
        temporaryScoreCreate++;
    }

    public static void setScoreList(Company c){
        for(Department d : c.getListDepartment()){
            for(Employee e : d.getListEmployee()){
                historique.addAll(e.historique);
            }
        }
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
