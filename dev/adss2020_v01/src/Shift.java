import java.time.LocalDate;
import java.util.*;

public class Shift {
    private LocalDate date;
    //tells if the shift is evening or morning, we will use only 1 \ 2 from the enum
    private ShiftTime shift_time;
    private double start;
    private double end;
    //all the workers in the shift
    private Map<String,String> WorkerList;
    //manager of the shift
    private String managerID;
    private String managerName;

    public Shift(LocalDate date,ShiftTime ShiftTime,double start,double end,String managerID,String managerName){
        this.date=date;
        this.shift_time=ShiftTime;
        this.managerID=managerID;
        this.managerName=managerName;
        WorkerList=new HashMap<>();
        this.start=start;
        this.end=end;
        //we also add the manager to the list
        this.AddWorker(managerID,managerName);
    }
    //builder for empty shift
    public Shift(LocalDate date,ShiftTime ShiftTime){
        this.date=date;
        this.shift_time=ShiftTime;
        this.managerID="-1";
        this.managerName="-1";
        WorkerList=new HashMap<>();
        this.start=0;
        this.end=0;
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

    public void PrintMe(){
        //if its an empty shift we just print that its empty
        if(this.managerID=="-1"){
            System.out.println("at "+this.date + " " + this.shift_time  +" there is no shift ");
        }
        else {
            System.out.println(this.date + " " + this.shift_time + "\n" +
                    "from: " + this.start + " until: " + this.end);
            System.out.println("the manager is " + this.managerName + " with ID: " + this.managerID);

            for (String ID : this.WorkerList.keySet()) {
                //we dont need to print the manager twice
                if (ID != this.managerID) {
                    System.out.println(ID + " - " + this.WorkerList.get(ID));
                }
            }
        }
    }
}
