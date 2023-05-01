package Bussiness.HRBussiness;
import Bussiness.Days;
import Bussiness.ShipBussiness.Site;
import Bussiness.Zone;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Super extends Site {
    //map<id, worker>
    private Map<String,Worker> WorkerList;
    //this week working sheet
    private Weekly WeekShifts;
    //history of all the shifts
    private List<Weekly> WeeklyHist;
    private CashRegister cash_register;

    //times of the shifts, ill put default values that the manager can change later
    //need to change them to map <days,double>
    //private Map<Domain.Ship.Days,Double> start_morning;
    private Map<Days,Double> start_morning;
    private Map<Days,Double> end_morning;
    private Map<Days,Double> start_evening;
    private Map<Days,Double> end_evening;


    private Zone zone;

    //builder for super
    public Super(String Name, String address, String phoneNumber, String contactName, Zone zone){
        super(Name,address,phoneNumber,contactName);
        this.zone=zone;
        WorkerList=new HashMap<>();
        WeeklyHist=new ArrayList<>();
        cash_register=new CashRegister();
        start_morning=new HashMap<>();
        end_morning=new HashMap<>();
        start_evening=new HashMap<>();
        end_evening=new HashMap<>();
        //sets the basic hours of each day to work from 7 to 14 and then from 14 to 23
        this.setHours();
    }

    public void setHours(){
        for (Days day:Days.values()){
            start_morning.put(day,7.00);
            end_morning.put(day,14.00);
            start_evening.put(day,14.00);
            end_evening.put(day,23.00);
        }
    }
    //a function that adds a worker to the Workers list
    public void AddWorker(Worker worker){WorkerList.put(worker.getID(),worker);
    }
    public boolean RemoveWorker(String ID){
        if(WorkerList.containsKey(ID)){
            WorkerList.remove(ID);
            return true;
        }
        return false;
    }
    //from hovalot
    public Zone getZone() {
        return zone;
    }
    @Override
    public void printSite() {
        System.out.println("Site Type: Branch");
        System.out.println("Name: " + getName());
        System.out.println("Address: " + getAddress());
        System.out.println("Contact Name: " + getContactName());
        System.out.println("Phone Number: " + getPhoneNumber());
        System.out.println("Zone: " + zone + "\n");
    }



    public List<String> GetWorkersIDS(){
        List<String> Workers=new ArrayList<>();
        Workers.addAll(WorkerList.keySet());
        return Workers;
    }
//add a weekly to the super
    public void AddWeekly(Weekly week){
        this.WeekShifts=week;
    }
    public boolean HasWeekly(){
        return this.WeekShifts!=null;
    }
    public Weekly GetWeekShifts(){return this.WeekShifts;}
    public void PrintWeekFromHistByDate(int year,int month,int day) {
        boolean is_printed=false;
        for (Weekly week : this.WeeklyHist) {
            LocalDate date = week.getStartDate();
            if (date.getYear() == year && date.getMonthValue()== month && date.getDayOfMonth()==day){
                week.PrintMe();
                is_printed=true;
            }
        }
        if (!is_printed){
            System.out.println("there is no weekly in that date");
        }
    }
    public void SendConstraintsToHistory(){
        //add the current one to history
        this.WeeklyHist.add(this.WeekShifts);
        //sets the weekly shift to null so he will need to create new one
        this.WeekShifts=null;
    }
    public double getEnd_evening(Days day) {
        return end_evening.get(day);
    }
    public double getEnd_morning(Days day) {return end_morning.get(day);}
    public double getStart_evening(Days day) {
        return start_evening.get(day);
    }
    public double getStart_morning(Days day) {
        return start_morning.get(day);
    }
    public void setStart_morning(Days day,Double t) {
        //remove if already has something
        this.start_morning.remove(day);
        //add new one
        this.start_morning.put(day,t);
    }
    public void setEnd_morning(Days day,Double t) {
        //remove if already has something
        this.end_morning.remove(day);
        //add new one
        this.end_morning.put(day,t);
    }
    public void setStart_evening(Days day,Double t) {
        //remove if already has something
        this.start_evening.remove(day);
        //add new one
        this.start_evening.put(day,t);
    }
    public void setEnd_evening(Days day,Double t) {
        //remove if already has something
        this.end_evening.remove(day);
        //add new one
        this.end_evening.put(day,t);
    }
    public CashRegister get_cash_register(){
        return this.cash_register;
    }
}
