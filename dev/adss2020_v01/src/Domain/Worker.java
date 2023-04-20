package Domain;

import java.util.ArrayList;
import java.util.List;

public class Worker extends AWorker {
    //a list of jobes he can do
    private List<Jobs> Roles;//need to be size 7 for all the roles in the end

    public Worker(String Name, String ID, int Bank, String Contract, int Wage, Jobs FirstJob, String Password) {
        super(Name, ID, Bank, Contract, Wage, Password);
        Roles=new ArrayList<>();
        this.Roles.add(FirstJob);
    }
    //a function to add a job to the worker
    public void AddJob(Jobs job){
        this.Roles.add(job);
    }

    //gets a jobs and return a boolean that tells if he can do it or not
    public boolean CanDoJob(Jobs job){
        return this.Roles.contains(job);
    }

}
