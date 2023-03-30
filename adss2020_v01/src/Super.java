import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Super {
    private String Name;
    private List<String> WorkerList;
    //this week working sheet
    private Weekly WeekShifts;
    //history of all the shifts
    private List<Weekly> WeeklyHist;
    private CashRegister cash_register;

    //times of the shifts, ill put default values that the manager can change later
    //need to change them to map <days,double>
    //private Map<Days,Double> start_morning;
    private double start_morning;
    private double end_morning;
    private double start_evening;
    private double end_evening;


    //builder for super
    public Super(String Name){
        this.Name=Name;
        WorkerList=new ArrayList<>();
        WeeklyHist=new ArrayList<>();
        cash_register=new CashRegister();

        start_morning=7;
        end_morning=14;
        start_evening=14;
        end_evening=23;

    }
    //a function that adds a worker to the workers list
    public void AddWorker(String WorkerID){
        WorkerList.add(WorkerID);
    }

    public boolean RemoveWorker(String ID){
        return WorkerList.remove(ID);
    }

    public String GetName(){
        return this.Name;
    }

    public List<String> GetWorkers(){
        return WorkerList;
    }

//add a weekly to the super
    public void AddWeekly(Weekly week){
        this.WeekShifts=week;
    }

    public Weekly GetWeekShifts(){return this.WeekShifts;}
    public void SendIlutsToHistory(){
        //add the current one to history
        this.WeeklyHist.add(this.WeekShifts);
        //sets the weekly shift to null so he will need to create new one
        this.WeekShifts=null;
    }

    public void setEnd_evening(double end_evening) {
        this.end_evening = end_evening;
    }
    public double getEnd_evening() {
        return end_evening;
    }

    public void setEnd_morning(double end_morning) {
        this.end_morning = end_morning;
    }
    public double getEnd_morning() {
        return end_morning;
    }

    public void setStart_evening(double start_evening) {
        this.start_evening = start_evening;
    }
    public double getStart_evening() {
        return start_evening;
    }

    public void setStart_morning(double start_morning) {
        this.start_morning = start_morning;
    }
    public double getStart_morning() {
        return start_morning;
    }


    public CashRegister get_cash_register(){
        return this.cash_register;
    }
}
