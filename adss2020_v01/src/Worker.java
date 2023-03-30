import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Worker {
    private String Name;
    private String  ID;
    private int Bank;
    //might be a txt file in the future
    private String Contract;
    private LocalDate StartDate;
    //so we know how much to pay him
    private int Wage;
    private String Password;
    //the shifts he can work at
    private Iluts ShiftsCanWork;
    //a list of jobes he can do
    private List<Jobs> Roles;//need to be size 7 for all the roles in the end

    //saves the number of days that he worked so we can pay him
    private int ShiftWorked;
    //saves the days of the week that he is already working
    private List<Days> WeeklyWorkingDays;

    //builder for worker
    public Worker(String Name, String ID, int Bank,String Contract, int Wage , Jobs FirstJob, String Password){
        this.Name=Name;
        this.ID=ID;
        this.Bank=Bank;
        this.Contract=Contract;
        this.Wage=Wage;
        this.Roles=new ArrayList<>();
        this.Roles.add(FirstJob);
        this.Password=Password;
        //puts the date if starting the job
        this.StartDate=LocalDate.now();

        this.ShiftWorked=0;

        //sets the iluts to everyday until he updates it
        this.ShiftsCanWork= new Iluts();

        this.WeeklyWorkingDays=new ArrayList<>();
    }

    //a function to add a job to the worker
    public void AddJob(Jobs job){
        this.Roles.add(job);
    }

    //at the end of the week we need to put 0 here
    //probably when we start a new scheduel
    public void ReSetDaysOfWork(){
        this.WeeklyWorkingDays.clear();
    }

    //called when we create a new iluts
    public void AddCantWork(Days day,double s,double e){
        ShiftsCanWork.AddCantWork(day,s,e);
    }
    //called when we delete an iluts
    public void RemoveCantWork(Days day,double s,double e){
        ShiftsCanWork.RemoveCantWork(day,s,e);
    }

    //check if entered right password
    public boolean CheckPassword(String Password){
        return this.Password.equals(Password);
    }
    //used to change password
    public void SetPassword(String Password){
        this.Password=Password;
    }

    //gets a jobs and return a boolean that tells if he can do it or not
    public boolean CanDoJob(Jobs job){
        return this.Roles.contains(job);
    }

    public boolean IsFree(Days day,double s,double e){
        if(this.WeeklyWorkingDays.size()==6 || this.WeeklyWorkingDays.contains(day)){
            return false;
        }
        //return if he can work there or not
        return ShiftsCanWork.CanWork(day, s, e);
    }

    public String GetName(){
        return this.Name;
    }

    //update a worker that he got a shift
    public void AddShift(Days day){
        this.ShiftWorked++;
        this.WeeklyWorkingDays.add(day);
    }

    // used to change worker name
    public void SetName(String newName){
        this.Name=newName;
    }
    // used to change worker bank details
    public void SetBank(int newBank){
        this.Bank=newBank;
    }
    // used to add new Iluts

    public void ShowIluts(){
        this.ShiftsCanWork.PrintMe();
    }

    public void setWage(int wage){this.Wage = wage;}

    public void setContract(String contract){this.Contract = contract;}

}
