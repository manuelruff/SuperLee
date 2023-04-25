package Domain;

import DataAccess.Connect;
import DataAccess.DataController;
import DataAccess.StartData;
import DataAccess.WorkerMapper;
import junit.framework.Test;

import java.util.*;

//this will be singleton
public class GeneralController {
    private static GeneralController instance=new GeneralController();
    // until the DB
    //all the branches of the company
    public static Map<String, Super> Superim;
    //all the Workers in the company
    public static Map<String, Worker> Workers;

    private GeneralController() {
        Superim = StartData.getSuperim();
//        Workers = StartData.getWorkers();
        Workers=WorkerMapper.getWorkerMap();
     }

    public static GeneralController getInstance() {
        return instance;
    }
    //check that a given number is in a time structure (hours and minuts)
    public static boolean CheckTimeValidate(double start, double end) {
        double start_dec = start - Math.floor(start);
        double end_dec = end - Math.floor(end);
        return (!(start < 0 || start > end || end > 24 || start_dec >= 0.591 || end_dec >= 0.591));
    }
    /**
     * a function that asks for a number until its int the given range
     *
     * @param s start of range
     * @param e end of range
     * @return a number in the range
     */
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
    // checks if branch is exist
    public static boolean CheckSuperName(String Name) {
        return Superim.get(Name) != null;
    }
    //get day job and a list of Workers and checks if the worker can do the work in the shift and these conditions
    public static List<String> GetAvailableEmployee(Days day, Jobs job, ShiftTime time, List<String> WorkersID, String SuperName) {
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
    // reset the number of shifts to all Workers - im not sure if this one should be here
    public static void ResetWorkDaysWorkers() {
        for (String ID : Workers.keySet()) {
            Workers.get(ID).ResetShiftsAmount();
        }
    }
    // check if worker is exists by id
    public static boolean isExistWorker(String ID) {
        //we tell the database to load that id if exists before we check him
        DataController.getWorker(ID);
        return Workers.get(ID) != null;
    }
    //returns worker by id
    public static Worker GetWorkerByID(String ID) {
        return Workers.get(ID);
    }
    //check by id if worker works in specific super
    public static boolean IsWorksInSuper(String ID, String SuperName) {
        return Superim.get(SuperName).GetWorkersIDS().contains(ID);
    }
    //check if a worker can to a job\role - im not sure if it should be here or in worker controller
    public static boolean CanDoJob(String ID, Jobs job) {
        return Workers.get(ID).CanDoJob(job);
    }

    /**
     * it will save the data in the database and close the connection
     */
    public static void closeDB(){
        DataController.saveData();
    }


}

