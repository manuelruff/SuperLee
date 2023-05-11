package HR.Bussiness;
import HR.DataAccess.DataController;
import HR.DataAccess.SuperMapper;
import HR.DataAccess.WorkerMapper;
import Shipment.Bussiness.Branch;
import Shipment.Service.HRService;

import java.util.*;
import java.util.ArrayList;
import java.util.HashSet;

//fast singleton
public class ServiceController {
    private static ServiceController instance;
    private Map<String, Super> Superim;
    //all the Workers in the company
    private Map<String, Worker> Workers;
    private Map<String, Driver> Drivers;
    private WorkerMapper workerMapper;
    private SuperMapper superMapper;

    private Set<String> changes;

    private ServiceController() {
        workerMapper=WorkerMapper.getInstance();
        superMapper=SuperMapper.getInstance();
        Superim = superMapper.getSuperMap();
        Workers= workerMapper.getWorkerMap();
        Drivers=workerMapper.getDriverMap();
        changes=new HashSet<>();
    }
    public static ServiceController getInstance() {
        if (instance == null) {
            instance = new ServiceController();
        }
        return instance;
    }
    //check if all the branches have a weekly if not we return false
    public boolean checkHasWeekly(List<String> branches){
        for(String branch:branches){
            if(!Superim.get(branch).HasWeekly()){
                return false;
            }
        }
        return true;
    }

    // check if all branches have store keeper in shifts
    private boolean checkAllBranchesStoreKeeper(List<String>branches,int shiftTime){
        for(String branch:branches){
            if(!Superim.get(branch).GetWeekShifts().GetShift(shiftTime).getWorkerList().containsKey(Jobs.StoreKeeper)){
                return false;
            }
        }
        return true;
    }
    private void removeStoreKeepersIfNeed(List<String>branches,int shiftTime,int day){
        for(String branch:branches){
            //todo check if the -- of the number of shifts decrease successfully
            Superim.get(branch).GetWeekShifts().GetShift(shiftTime).getWorkerList().remove(Jobs.StoreKeeper);
            // if it's not decrease auto - use this lines
            String StoreKeeperID =Superim.get(branch).GetWeekShifts().GetShift(shiftTime).getWorkerList().get(Jobs.StoreKeeper).get(0).ID;
            Workers.get(StoreKeeperID).RemoveShift(Days.values()[day]);
        }
    }
    public boolean checkStoreKeeper(List<String> branches,int day){
        int shifttime = day*2;
        // get all the branches that have can a store keeper-
        List<String>addedStoreKeeper1=CanAddStoreKeeper(branches,shifttime,0);
        List<String>addedStoreKeeper2=CanAddStoreKeeper(branches,shifttime+1,1);
        // flag to check if all the branches have a store keeper
        boolean checkadded1 = checkAllBranchesStoreKeeper(addedStoreKeeper1,shifttime);
        boolean checkadded2 = checkAllBranchesStoreKeeper(addedStoreKeeper2,shifttime+1);
        // check for each branch for each shift (morning and evening) if he has a store keeper
        if(checkadded1 && checkadded2) {
            // save a meesage to the HR manager that we had to add store keeper
            for (String b : branches) {
                changes.add(b);
            }
            return true;
        }
        // if we not succeed to add a store keeper to all branches - remove the one we added
        //todo check if the -- of the number of shifts decrease successfully
        removeStoreKeepersIfNeed(addedStoreKeeper1,shifttime,day);
        removeStoreKeepersIfNeed(addedStoreKeeper2,shifttime+1,day);
        return false;
    }

    // if branch doesnt have store keeper - try to add one
    // we will return the list of all the branches we succeed to add a store keeper
    public List<String> CanAddStoreKeeper(List<String> branches,int day,int shiftTime){
        List<String> added=new ArrayList<>();
        Days day1=Days.values()[day];
        //check for each branch if he has a store keeper
        for(String branch:branches){
            if(!Superim.get(branch).GetWeekShifts().GetShift(day).getWorkerList().containsKey(Jobs.StoreKeeper)){
                List <String> workers = Superim.get(branch).GetWorkersIDS();
                // check if there is a store keeper that can be added to the shift
                for(String worker:workers){
                    if(shiftTime == 0){
                    if((Workers.get(worker).CanDoJob(Jobs.StoreKeeper) && !Workers.get(worker).IsFree(day1,Superim.get(branch).getStart_morning(day1),Superim.get(branch).getEnd_morning(day1))))
                    {
                        Superim.get(branch).GetWeekShifts().GetShift(day).AddWorker(Jobs.StoreKeeper, Workers.get(worker));
                        Workers.get(worker).AddShift(Days.values()[day]);
                        Workers.get(worker).AddShiftWorked();
                        added.add(branch);
                        }
                    }
                    else{
                        if((Workers.get(worker).CanDoJob(Jobs.StoreKeeper) && !Workers.get(worker).IsFree(day1,Superim.get(branch).getStart_evening(day1),Superim.get(branch).getEnd_evening(day1))))
                        {
                            Superim.get(branch).GetWeekShifts().GetShift(day).AddWorker(Jobs.StoreKeeper, Workers.get(worker));
                            Workers.get(worker).AddShift(Days.values()[day]);
                            Workers.get(worker).AddShiftWorked();
                            added.add(branch);
                        }

                    }
                }
            }
        }
        return added;
    }



    public List<String> getDriver(char licence, int training,int day){
        //create list to put in values of drivers
        List<String> driverInfo=new ArrayList<>();
        //take the parameters and turn them to enums
        Training ability=Training.values()[training];
        Days day1=Days.values()[day];
        //ill load all the drivers that have those training and licence to the DB so we can choose a relevant one
        workerMapper.ReadAllDriversByInfo(licence,ability);
        for(Driver driver:Drivers.values()){
            if(driver.getLicense()==licence&&driver.getAbility()==ability){
                if(driver.checkDay(day1)){
                    //todo check if this works
                    //we tell the driver he works in this day
                    driver.addNewDay(day1);
                    //add the driver shift to driver shifts for each branch
                    //we save the info of the driver so we can send it back
                    driverInfo.add(driver.getID());
                    driverInfo.add(driver.getName());
                    driverInfo.add(String.valueOf(driver.getLicense()));
                    driverInfo.add(driver.getAbility().toString());
                    //todo add more stuff to the driver we give back
                    //when we took all the info go out of loop
                    break;
                }
            }
        }
        //todo if i find a driver i need to assign a store keeper for that day
        //todo or make sure to tell the HR manager to do so
        //if i return it empty, no driver was found
        return driverInfo;
    }

    public List<String>getDriver(String ID){
        Driver ret= workerMapper.getDriver(ID);
        //todo break it up
        return null;
    }

    // funciton to reset changes
    public void resetChanges() {changes.clear();}

    // function to get the changes
    public Set<String> getChanges() {return changes;}

}
