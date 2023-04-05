import java.time.LocalDate;
import java.util.*;

public class Shift {
    private LocalDate date;
    //tells if the shift is evening or morning, we will use only 1 \ 2 from the enum
    private ShiftTime shift_time;
    //all the workers in the shift
    private Map<String,String> WorkerList;
    //manager of the shift
    private String managerID;
    private String managerName;

    public Shift(LocalDate date,ShiftTime ShiftTime,String managerID,String managerName){
        this.date=date;
        this.shift_time=ShiftTime;
        this.managerID=managerID;
        this.managerName=managerName;
        WorkerList=new HashMap<>();
    }
    public void AddWorker(String ID, String Name){
        this.WorkerList.put(ID,Name);
    }

    public void RemoveWorker(String ID){
        this.WorkerList.remove(ID);
    }

    public boolean IsWorkerAtShift(String ID){
        return WorkerList.get(ID) != null ;
    }

    public LocalDate GetDate(){
        return this.date;
    }
    public ShiftTime GetShiftTime(){
        return this.shift_time;
    }
    public List<String> GetWorkers(){
        List<String> ret=new ArrayList<>();
        for (String ID:this.WorkerList.values()){
            ret.add(ID);
        }
        return ret;
    }

    public void PrintMe(){
        System.out.println(this.date + " " + this.shift_time);
        System.out.println("the manager is "+this.managerName +" with ID: "+this.managerID);
        for (String ID:this.WorkerList.keySet()){
            System.out.println(ID + " - "+ this.WorkerList.get(ID) );
        }
    }
}
