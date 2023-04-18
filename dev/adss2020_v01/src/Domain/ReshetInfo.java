package Domain;
import DataAccess.StartData;
import java.util.*;

public class ReshetInfo {
    //all the branches of the company
    private static Map<String, Super> Superim;
    //all the Workers in the company
    private static Map<String, Worker> Workers;

    //this wil be a singleton
    private static ReshetInfo reshetinfo= new ReshetInfo();
    //builder for reshetinfo
    private ReshetInfo() {
        Superim = new HashMap<>();
        Workers = new HashMap<>();
        //create supers and Workers and insert them to where i need
        StartData.GetWorkers(Workers,Superim);
    }
    public static ReshetInfo getInstance(){
        return reshetinfo;
    }

    /**
     * this function will create database for beginning
     */

    //checks if the name of a super exists
    public boolean CheckSuperName(String Name) {
        return Superim.get(Name) != null;
    }

    public void addSuper(Super new_super) {
        Superim.put(new_super.GetName(), new_super);
    }

    //creates weeklysheeft for a branch
    public void CreateWeekly(String Name) {
        //we get the object of the super we wanna add a weekly to
        Super curr = Superim.get(Name);
        List<String> CanWorkList;
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        //create the weekly
        Weekly week = new Weekly();
        for (Days day : Days.values()) {
            ShiftTime time = ShiftTime.Morning;
            for (int i = 0; i < 2; i++) {
                //get the time of the shift
                double start;
                double end;
                if (time == ShiftTime.Morning) {
                    start = Superim.get(Name).getStart_morning(day);
                    end = Superim.get(Name).getEnd_morning(day);
                } else {
                    start = Superim.get(Name).getStart_evening(day);
                    end = Superim.get(Name).getEnd_evening(day);
                }
                System.out.println("we are working on: " + day + " at the " + time + " shift");
                //first we need to choose the manager to start the shift
                System.out.println("first choose the shift manager: ");
                //ill get the list of available managers
                CanWorkList = GetAvailableEmployee(day, Jobs.ShiftManager, time, curr.GetWorkersIDS(), curr.GetName());
                if (CanWorkList.size() == 0) {
                    System.out.println("it looks like you are out of managers or you dont have enough, go assing some new ones so you can make the shifts.");
                    return;
                }
                //prints the list of Workers available - name and id
                for (int j = 0; j < CanWorkList.size(); j++) {
                    System.out.println(j + 1 + ". " + Workers.get(CanWorkList.get(j)).GetName() + " with ID: " + CanWorkList.get(j));
                }
                System.out.println("if you dont want this shift to happen enter 0.");
                int num =AskForNumber(0,CanWorkList.size());
                //if he dont want anyone in the shift we create an empty shift
                if (num==0){
                    Shift CurrShift = new Shift(week.getStartDate().plusDays(day.ordinal()), time, start, end);
                    week.AddShift(CurrShift);
                    //we go to evening shift
                    time = ShiftTime.Evening;
                    //go to next iteration
                    continue;
                }
                //when we get here we have a good choice for the shift manager
                //cWorkerse the shift and now we need to add to it
                Shift CurrShift = new Shift(week.getStartDate().plusDays(day.ordinal()), time, start, end, Workers.get(CanWorkList.get(num - 1)));
                //update the menager shift
                Workers.get(CanWorkList.get(num - 1)).AddShift(day);
                //then we need to let him see the rest without shift manager
                //then we send it to the curr shift

                //start the list from the beginnning we dont need the menager list
                CanWorkList.clear();

                for (Jobs job : Jobs.values()) {
                    //we dont need to choose shift manager again or more then one
                    if (job == Jobs.ShiftManager) {
                        continue;
                    }
                    //we will get the available Workers
                    CanWorkList = GetAvailableEmployee(day, job, time, curr.GetWorkersIDS(), curr.GetName());
                    System.out.print(" how many Workers as " + job + " you want? ");
                    num =AskForNumber(0,CanWorkList.size());

                    //if i got here i have a good number
                    int k = 0;
                    while (k < num) {
                        System.out.println("the Workers that can be in this shift are: ");
                        for (int j = 0; j < CanWorkList.size(); j++) {
                            System.out.println((j + 1) + ". " + Workers.get(CanWorkList.get(j)).GetName() + " with ID: " + CanWorkList.get(j));
                        }
                        int choice = AskForNumber(1,CanWorkList.size());
                        //if i got here i have a good index for a worker
                        // we will decrese one so it will be the index from the list
                        choice = choice - 1;
                        //add to the shift
                        CurrShift.AddWorker(job, Workers.get(CanWorkList.get(choice)));
                        //now i need to update the worker propertyly
                        Workers.get(CanWorkList.get(choice)).AddShift(day);
                        //remove from the available Workers
                        CanWorkList.remove(choice);
                        k++;
                    }
                }
                //send the shift i created to the weekly
                week.AddShift(CurrShift);
                //we go to evening shift
                time = ShiftTime.Evening;
            }
        }
        //add the weekly to the super
        curr.AddWeekly(week);
    }

    //checks if there is a weekly right now
    public boolean HasWeekly(String Name) {
        return Superim.get(Name).HasWeekly();
    }

    //prints the shifts of one day for a branch (morning and evening)
    public void PrintDay(String Name, int day) {
        //we get the object of the super
        Super curr = Superim.get(Name);
        Weekly week = curr.GetWeekShifts();
        if (week == null) {
            System.out.println("nothing in this week yet");
        } else {
            //each day has 2 shifts so we show him both of them
            day = day * 2;
            week.GetShift(day).PrintMe();
            week.GetShift(day + 1).PrintMe();
        }
    }

    // remove worker from a shift of a branch
    public void RemoveFromDay(String ID, String branch, int day) {
        if (!IsWorkAtDay(branch, ID, day)) {
            System.out.println("this worker doesn't works at this shift");
            return;
        }
        int shiftnum = day * 2;
        //whem were here we have a good number for employee so we remove him
        Superim.get(branch).GetWeekShifts().GetShift(shiftnum).RemoveWorker(ID);
        Superim.get(branch).GetWeekShifts().GetShift(shiftnum + 1).RemoveWorker(ID);
        Workers.get(ID).RemoveShift(Days.values()[day]);
    }

    //checks if worker works in specific shift in a branch
    public static boolean IsWorkAtDay(String branch, String ID, int day) {
        day = day * 2;
        return (Superim.get(branch).GetWeekShifts().GetShift(day).IsWorkerAtShift(ID) || Superim.get(branch).GetWeekShifts().GetShift(day + 1).IsWorkerAtShift(ID));
    }

    // add worker to a shift int a branch
    public void AddToDay(String ID, String branch, int shift_op, int day) {
        double s = 0;
        double e = 0;
        //savres it to use in day
        int days_day=day;
        day = day * 2;
        if (shift_op == 1) {
            s = Superim.get(branch).getStart_morning(Days.values()[days_day]);
            e = Superim.get(branch).getEnd_morning(Days.values()[days_day]);
        } else {
            s = Superim.get(branch).getStart_evening(Days.values()[days_day]);
            e = Superim.get(branch).getEnd_evening(Days.values()[days_day]);
            day = day + 1;
        }
        if (!Workers.get(ID).IsFree(Days.values()[days_day], s, e)) {
            System.out.println("this worker can't work at this shift");
            return;
        } else {
            //check if the shift  is empty
            if (Superim.get(branch).GetWeekShifts().GetShift(day).IsEmptyShift()){
                if(!Workers.get(ID).CanDoJob(Jobs.ShiftManager)){
                    System.out.println("this shift is empty you need to add a shift manager first");
                }
                else{
                    //if we are here he added a manager to an empty shift
                    //whem were here we have a good number for employee so we add him
                    Superim.get(branch).GetWeekShifts().GetShift(day).AddWorker(Jobs.ShiftManager, Workers.get(ID));
                    // add the shift to the Workers shifts
                    Workers.get(ID).AddShift(Days.values()[days_day]);
                }
            }
            else {
                //print what role this worker can do and he decides what he wants him to do
                //whem were here we have a good number for employee so we add him

                //add all the jobs he can do
                List<Jobs> job_list=new ArrayList<>();
                for(Jobs job:Jobs.values()){
                    if(Workers.get(ID).CanDoJob(job)){
                        job_list.add(job);
                    }
                }
                //show the manager what he can do so he will choose
                System.out.println("please choose the role you want to put him in");
                int i=1;
                for(Jobs job:job_list){
                    System.out.println(i+". "+job);
                    i++;
                }
                int num=AskForNumber(1,job_list.size());
                Superim.get(branch).GetWeekShifts().GetShift(day).AddWorker(job_list.get(num-1), Workers.get(ID));
                // add the shift to the Workers shifts
                Workers.get(ID).AddShift(Days.values()[days_day]);
            }
        }
    }

    //prints current weekly shifts of a branch
    public void PrintWeekly(String Name) {
        //we get the object of the weekly
        Weekly week = Superim.get(Name).GetWeekShifts();
        if (week == null) {
            System.out.println("no weekly shift yet");
        } else {
            week.PrintMe();
        }
    }

    //print a shift from history of a branch by its date if exists
    public void PrintWeeklyFromHist(String Name, int year, int month, int day) {
        Superim.get(Name).PrintWeekFromHistByDate(year, month, day);
    }

    // check if worker is exists by id
    public boolean isExistWorker(String ID) {
        return Workers.get(ID) != null;
    }

    //remove a worker from a branch by id
    public void RemoveWorker(String ID, String Name) {
        //we get the object of the super we wanna add a weekly to
        Super curr = Superim.get(Name);
        curr.RemoveWorker(ID);
    }

    //get day job and a list of Workers and checks if the worker can do the work in the shift and these conditions
    public List<String> GetAvailableEmployee(Days day, Jobs job, ShiftTime time, List<String> WorkersID, String SuperName) {
        List<String> ret = new ArrayList<>();
        //we will save the start and end of the shift
        double start = 0;
        double end = 0;
        //like that i get the correct hours
        if (time == ShiftTime.Morning) {
            start = Superim.get(SuperName).getStart_morning(day);
            end = Superim.get(SuperName).getEnd_morning(day);
        } else {
            start = Superim.get(SuperName).getStart_evening(day);
            end = Superim.get(SuperName).getEnd_evening(day);
        }
        //checking each employee
        for (String id : WorkersID) {
            Worker curr = Workers.get(id);
            //check if he is qualified for the job
            if (curr.CanDoJob(job)) {
                //check if he is free by his Domain.Constraints
                if (curr.IsFree(day, start, end)) {
                    ret.add(id);
                }
            }
        }
        return ret;
    }

    //check if the password is correct by given ID
    public boolean IsTruePassword(String ID, String password) {
        if (!isExistWorker(ID)) {
            return false;
        }
        return Workers.get(ID).CheckPassword(password);
    }

    // add Domain.Constraints to worker by given Id
    public boolean AddConstraints(String ID, int day, double s_hour, double e_hour, String r) {
        return Workers.get(ID).AddCantWork(Days.values()[day - 1], s_hour, e_hour, r);
    }

    // remove Constraintss for worker by id
    public boolean RemoveConstraints(String ID, int day, double s_hour, double e_hour) {
        return Workers.get(ID).RemoveCantWork(Days.values()[day - 1], s_hour, e_hour);
    }

    //changes a worker password
    public void ChangeWorkerPassword(String ID, String newPassword) {
        Workers.get(ID).SetPassword(newPassword);
    }

    //changes a worker name
    public void ChangeWorkerName(String ID, String newName) {
        Workers.get(ID).SetName(newName);
    }

    //changes a worker bank info
    public void ChangeWorkerBank(String ID, int newBank) {
        Workers.get(ID).SetBank(newBank);
    }

    //prints constraints of worker
    public void ShowConstraints(String ID) {
        Workers.get(ID).ShowConstraints();
    }

    //sends weekly of all branches to their history
    public void SendConstraintsToHistory() {
        //send all the weekly to history
        for (Super sup : Superim.values()) {
            sup.SendConstraintsToHistory();
        }
        //prepare the Workers state for next weekly
        for (Worker worker : Workers.values()) {
            worker.ResetDaysOfWork();
        }
    }

    //check if all branches has the weekly
    public boolean CheckAllHaveWeekly() {
        for (Super sup : Superim.values()) {
            if (!sup.HasWeekly()) {
                return false;
            }
        }
        return true;
    }

    //update times of shift for one day in a branch
    public void UpdateSuperTimes(String Name, Days day, double m_s, double m_e, double e_s, double e_e) {
        Super curr = Superim.get(Name);
        curr.setStart_morning(day, m_s);
        curr.setEnd_morning(day, m_e);
        curr.setStart_evening(day, e_s);
        curr.setEnd_evening(day, e_e);
    }

    //add a cash cancellations
    public void AddCancellations(String Name, String item, double amount, String ID) {
        //create the cancallation
        Cancellations cancel = new Cancellations(amount, item, ID, Workers.get(ID).GetName());
        //add the cancellation to the super
        Superim.get(Name).get_cash_register().AddCancalation(cancel);
    }

    //print Domain.Cancellations of a specific date in a branch
    public void PrintCancellation(String Name, int year, int month, int day) {
        Superim.get(Name).get_cash_register().PrintCancellation(year, month, day);
    }

    //add a new worker to the company
    public void AddNewWorker(Worker newEmployee, String branchName) {
        // create the worker using all the data the manger entered
        //Domain.Worker new_worker = new Domain.Worker(ID, name, bank, contract, wage, job, password);
        for (Map.Entry<String, Super> entry : Superim.entrySet()) {
            if (entry.getKey().equals(branchName)) {
                Superim.get(entry.getKey()).AddWorker(newEmployee);
                Workers.put(newEmployee.GetID(), newEmployee);
                System.out.println(newEmployee.GetID() + " added successfully to: " + branchName);
            }
        }
    }

    //add worker by id to branch
    public void AddWorkerToBranch(String ID, String branchName) {
        if (Superim.get(branchName).GetWorkersIDS().contains(ID)) {
            System.out.println("the worker is already in this branch");
            return;
        }
        // add the worker to the Workers map
        Superim.get(branchName).AddWorker(Workers.get(ID));
        System.out.println(ID + " added successfully to: " + branchName);
    }

    //removes a worker from the company
    public void RemoveWorkerAllBranches(String ID) {
        // check if the branch name is existed in the superim list
        for (Map.Entry<String, Super> entry : Superim.entrySet()) {
            // remove if from every branch he works at
            if (Superim.get(entry.getKey()).RemoveWorker(ID)) {
                System.out.println("worker removed successfully from " + Superim.get(entry.getKey()).GetName());
            }
        }
        // remove it from the map of all Workers
        Workers.remove(ID);
    }

    //add a job for a worker (role) by id
    public void AddJobToWorker(String ID, int job_index) {
        Workers.get(ID).AddJob(Jobs.values()[job_index - 1]);
    }

    //changes the wage of a worker by id
    public void ChangeWage(String ID, int wage) {
        Workers.get(ID).setWage(wage);
    }

    //changes the contract of a worker by id
    public void ChangeContract(String ID, String input_Contract) {
        Workers.get(ID).setContract(input_Contract);
    }

    //check that a given number is in a time structure (hours and minuts)
    public boolean CheckTimeValidate(double start, double end) {
        double start_dec = start - Math.floor(start);
        double end_dec = end - Math.floor(end);
        return (!(start < 0 || start > end || end > 24 || start_dec >= 0.591 || end_dec >= 0.591));
    }

    // send payment to the Workers
    public void Payment() {
        for (Worker worker : Workers.values()) {
            worker.CalculateSalary();
            worker.resetBonus();
            ResetWorkDaysWorkers();
        }
        System.out.println("Payment done!");
    }

    // add bonus to worker by given ID

    public void addBonusToWorker(String ID, double bonus) {
        Workers.get(ID).addBonus(bonus);
        System.out.println("bonus added successfully to: " + Workers.get(ID).GetName());
    }

    public void addNewInfo(String ID, String reason, String info) {
        Workers.get(ID).addInfo(reason, info);
    }

    public void removeInfo(String ID, String reason) {
        Workers.get(ID).removeInfo(reason);
    }

    public void removeBonusToWorker(String ID, double bonus) {
        Workers.get(ID).removeBonus(bonus);
        System.out.println("bonus removed from: " + Workers.get(ID).GetName());
    }

    // reset the number of shifts to all Workers
    public void ResetWorkDaysWorkers() {
        for (String ID : Workers.keySet()) {
            Workers.get(ID).ResetShiftsAmount();
        }
    }

    //returns worker by id
    public Worker GetWorkerByID(String ID) {
        return Workers.get(ID);
    }

    //check by id if worker works in specific super
    public boolean IsWorksInSuper(String ID, String SuperName) {
        return Superim.get(SuperName).GetWorkersIDS().contains(ID);
    }

    //check if a worker can to a job\role
    public boolean CanDoJob(String ID, Jobs job) {
        return Workers.get(ID).CanDoJob(job);
    }

    /**
     * a function that asks for a number until its int the given range
     *
     * @param s start of range
     * @param e end of range
     * @return a number in the range
     */
    // function to ask the user for a number in given range input
    public static int AskForNumber(int s, int e) {
        int num = 0;
        while (true) {
            Scanner myObj_input = new Scanner(System.in);  // Create a Scanner object
            String input = myObj_input.nextLine();
            try {
                num = Integer.parseInt(input);
                if (num <= e && num >= s) {
                    return num;
                } else {
                    System.out.println("your input is not in range of: " + s + " - " + e);
                }
            }
            //if he entered something not suitable we will repeat
            catch (Exception e1) {
                System.out.println("invalid input, please try again");
            }
        }
    }


}