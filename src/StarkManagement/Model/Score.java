package StarkManagement.Model;

import Common.Hours;
import Common.ScoreShort;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

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
                if(s.employee == employee){  //from the same person
                    if(s.type == Type.IN)
                        type = Type.OUT;
                    if(s.type == Type.OUT)
                        type = Type.ANORMAL;
                    if(s.type == Type.ANORMAL)
                        //If it's another check, we still don't count it
                        type = Type.ANORMAL;
                }
            }
        }

        //StockHours modification
        //We get the day of today with
        String day = this.getHeure().getDate().getDayOfWeek().toString();
        day = day.toLowerCase();
        day = day.replace(day.charAt(0), Character.toUpperCase(day.charAt(0)));

        Days days = Days.valueOf(day);

        Hours hourEnd = employee.getPlanning().getEndForDays(days);
        hourEnd.roundNextQuarter();
        Hours hoursScore= this.getHeure();

        Hours hourStart = employee.getPlanning().getStartForDays(days);
        hourStart.roundNextQuarter();
        if(type == Type.IN){
            //If the hourStart > hourScore
            if(hoursScore.minutesBetween(hourStart) > 0){
                employee.setStockHoure(employee.getStockHoure() + hoursScore.minutesBetween(hourStart)/60);
            }
            else{ //If the hourScore > hourStart
                employee.setStockHoure(employee.getStockHoure() - hourStart.minutesBetween(hoursScore)/60);
            }
        }
        else{
            if(type == Type.OUT){
                //If the employee go out before is planning, we remove some hours
                if(hoursScore.minutesBetween(hourEnd) > 0){
                    employee.setStockHoure(employee.getStockHoure() - hoursScore.minutesBetween(hourEnd)/60);
                }
                //If the employee go out after is planning, we add some hours
                else{
                    employee.setStockHoure(employee.getStockHoure() + hourEnd.minutesBetween(hoursScore)/60);
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
