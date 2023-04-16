package Domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AWorker {
    private String Name;
    private String  ID;
    private int Bank;
    //might be a txt file in the future
    private String Contract;
    private LocalDate StartDate;
    //so we know how much to pay him
    private double Wage;
    private double Bonus;
    private Map<String ,String > info;
    private String Password;
    //the shifts he can work at
    private Constraints ShiftsCantWork;
    //a list of jobes he can do
    private List<Jobs> Roles;//need to be size 7 for all the roles in the end

    //saves the number of days that he worked so we can pay him
    private int ShiftWorked;
    //saves the days of the week that he is already working
    private List<Days> WeeklyWorkingDays;
    public AWorker(String Name, String ID, int Bank,String Contract, int Wage , Jobs FirstJob, String Password){
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
        this.info = new HashMap<>();
        this.ShiftWorked=0;
        this.Bonus = 0;
        //sets the Domain.Constraints to everyday until he updates it
        this.ShiftsCantWork= new Constraints();
        this.WeeklyWorkingDays=new ArrayList<>();
    }
    //a function to add a job to the worker
    public void AddJob(Jobs job){
        this.Roles.add(job);
    }

    //at the end of the week we need to put 0 here
    //probably when we start a new scheduel
    public void ResetDaysOfWork(){
        this.WeeklyWorkingDays.clear();
    }
    // reset the number of shifts the worker works in a month
    public void ResetShiftsAmount(){
        this.ShiftWorked = 0;
    }

    //called when we create a new Domain.Constraints
    public boolean AddCantWork(Days day,double s,double e,String r){
        return ShiftsCantWork.AddCantWork(day,s,e,r);
    }
    //called when we delete an Domain.Constraints
    public boolean RemoveCantWork(Days day,double s,double e){
        return ShiftsCantWork.RemoveCantWork(day,s,e);
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
        return ShiftsCantWork.CanWork(day, s, e);
    }

    public String GetName(){
        return this.Name;
    }

    public String GetID(){
        return this.ID;
    }

    //update a worker that he got a shift
    public void AddShift(Days day){
        this.ShiftWorked++;
        this.WeeklyWorkingDays.add(day);
    }
    //update a worker that he removes a shift - added 31.3
    public void RemoveShift(Days day){
        this.ShiftWorked--;
        this.WeeklyWorkingDays.remove(day);
    }

    // used to change worker name
    public void SetName(String newName){
        this.Name=newName;
    }
    // used to change worker bank details
    public void SetBank(int newBank){
        this.Bank=newBank;
    }
    // used to add new Domain.Constraints

    public void ShowConstraints(){
        this.ShiftsCantWork.PrintMe();
    }

    public void setWage(double wage){this.Wage = wage;}

    public void addBonus(double bonus){this.Bonus+=bonus;}

    public void removeBonus(double bonus){
        if(this.Bonus<bonus){
            resetBonus();
        }
        else {
            this.Bonus-=bonus;
        }
    }
    public void resetBonus(){this.Bonus=0;}

    public double CalculateSalary(){return this.Wage*this.ShiftWorked+this.Bonus;}

    public void setContract(String contract){this.Contract = contract;}

    // function to set the number of shifts worker works
    public int getShiftWorked(){
        return this.ShiftWorked;
    }

    public double getWage(){return this.Wage;}

    public void addInfo(String reason, String info){this.info.put(reason,info);}

    public void removeInfo(String reason){this.info.remove(reason);}

    public void Printme(){
        System.out.println("Name: "+ this.GetName()+" with ID: "+this.GetID());
    }
}
