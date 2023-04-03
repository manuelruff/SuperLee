import java.time.LocalDate;
import java.util.*;

public class Shift {
    //date of the shift
    private LocalDate date;
    //tells if the shift is evening or morning, we will use only 1 \ 2 from the enum
    private CanWork ShiftTime;
    //all the workers in the shift
    private Map<Jobs,List<Worker>> WorkerList;

    public Shift(LocalDate date,CanWork ShiftTime){
        this.date=date;
        this.ShiftTime=ShiftTime;
        WorkerList=new HashMap<>();
    }
    public void AddWorker(Jobs job, Worker worker){
        List<Worker> curr=this.WorkerList.get(job);
        if (curr==null){
            this.WorkerList.put(job,new ArrayList<>());
        }
        curr.add(worker);
    }

    public void RemoveWorker(String ID){
        //we nned to implement it in the new way -- map something
    }

    public boolean IsWorkerAtShift(String ID){
        //for now
        return false;
    }
    public LocalDate GetDate(){
        return this.date;
    }
    public CanWork GetShiftTime(){
        return this.ShiftTime;
    }
    public List<String> GetWorkers(){

        List<String> ret=new ArrayList<>();
        /*
        for (String ID:this.WorkerList.values()){
            ret.add(ID);
        }

         */
        return ret;
    }

    public void PrintMe(){
        /*
        System.out.println(this.date + " " + this.ShiftTime);
        System.out.println("the manager is "+this.managerName +" with ID: "+this.managerID);
        for (String ID:this.WorkerList.keySet()){
            System.out.println(ID + " - "+ this.WorkerList.get(ID) );
        }

         */
    }
}
