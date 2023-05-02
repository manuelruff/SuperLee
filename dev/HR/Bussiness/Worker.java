package HR.Bussiness;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Worker extends AWorker {
    //a list of jobes he can do
    private List<Jobs> Roles;//need to be size 7 for all the roles in the end
    public Worker(String ID, String Name, int Bank,
                  String Contract, double Wage, Jobs FirstJob, String Password) {
        super(ID,Name, Bank, Contract, Wage, Password);
        Roles=new ArrayList<>();
        this.Roles.add(FirstJob);
    }
    //we use this when we read from the database, the job will be updated later
    public Worker(String ID, String Name, int Bank, String Contract, double Wage, String Password, LocalDate startDate,double bonus,int shiftworked) {
        super(ID,Name, Bank, Contract, Wage, Password,startDate,bonus,shiftworked);
        Roles=new ArrayList<>();
    }
    //a function to add a job to the worker
    public void AddJob(Jobs job){
        this.Roles.add(job);
    }
    public List<Jobs> getRoles() {
        return Roles;
    }
    //gets a jobs and return a boolean that tells if he can do it or not
    public boolean CanDoJob(Jobs job){
        return this.Roles.contains(job);
    }
}
