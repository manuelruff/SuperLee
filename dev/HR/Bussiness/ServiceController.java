package HR.Bussiness;
import HR.DataAccess.DataController;

import java.time.LocalDate;
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
    private DataController dataController;

    private Set<String> changes;

    private ServiceController() {
        dataController=DataController.getInstance();
        Superim = dataController.getSuperMap();
        Workers= dataController.getWorkerMap();
        Drivers=dataController.getDriverMap();
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
    //this one checks for next week or this week, we need to knwo what he wants
    public boolean checkHasWeekly(List<String> branches, LocalDate date){
        for(String branch:branches){
            if(!Superim.get(branch).HasWeekly()){
                return false;
            }
            //we check the weekly contains the date we got
            LocalDate dateSuper=Superim.get(branch).GetWeekShifts().getStartDate();
            if(!dateSuper.plusDays(7).isAfter(date)){
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
        dataController.ReadAllDriversByInfo(licence,ability);
        for(Driver driver:Drivers.values()){
            if(driver.getLicense()>=licence&&driver.getAbility().ordinal()>=ability.ordinal()){
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
                    //when we took all the info go out of loop
                    break;
                }
            }
        }
        //if i return it empty, no driver was found
        return driverInfo;
    }

    // the function return all the inforamtion needed to build Driver Object
    public List<String>getDriver(String ID){
        Driver ret= dataController.getDriver(ID);
        //todo check if it works
        List<String>driverInfo=new ArrayList<>();
        driverInfo.add(ret.getID());
        driverInfo.add(ret.getName());
        driverInfo.add(String.valueOf(ret.getLicense()));
        driverInfo.add(ret.getAbility().toString());
        return driverInfo;
    }

    // the function return all the information needed to build Super Object
    public List<String>getSite(String BranchName){
        Super ret = dataController.getSuper(BranchName);
        List<String>branchInfo = new ArrayList<>();
        //[String name, String address, String phoneNumber, String contactName, Zone zone]
        branchInfo.add(ret.getName());
        branchInfo.add(ret.getAddress());
        branchInfo.add(ret.getPhoneNumber());
        branchInfo.add(ret.getContactName());
        branchInfo.add(ret.getZone().toString());
        return branchInfo;
    }
    public List<List<String>>getSites(){
        dataController.getSupers();
        List<List<String>> arr = new ArrayList<>();
        for(Super sup : Superim.values()){
            List<String>branchInfo = new ArrayList<>();
            //[String name, String address, String phoneNumber, String contactName, Zone zone]
            branchInfo.add(sup.getName());
            branchInfo.add(sup.getAddress());
            branchInfo.add(sup.getPhoneNumber());
            branchInfo.add(sup.getContactName());
            branchInfo.add(sup.getZone().toString());
            arr.add(branchInfo);
        }
        return arr;
    }

    // check if site is exist
    public boolean checkSite(String name){
        // check it from the mapper by the dataController
        return dataController.getSuper(name) != null;
    }

    //print all the drivers we have
    public void printDrivers(){
        //first read all the workers from the DB by the datacontroller
        dataController.loadAllWorkersFrom();
        // print them
        for(Driver driver: Drivers.values()){
            driver.Printme();
        }
    }

    public void RemoveShiftFromDriver(String ID,int day){
        Driver driver = dataController.getDriver(ID);
        driver.RemoveShift(Days.values()[day]);
    }

    // update driver informantion
    public void UpdateDriver(String ID,char licence, int training){
        Driver driver = dataController.getDriver(ID);
        driver.setLicense(licence);
        driver.setAbility(Training.values()[training]);
    }

    // add message to HR manager if shipment cancelled in specific day
    public void UpdateSiteMessage(String branchName, int day){
        Super branch = dataController.getSuper(branchName);
        String message = "The site " + branch.getName() + " no longer have shipment for day " + Days.values()[day];
        changes.add(message);
    }

    //update contact info for a site
    public void UpdateSiteContact(String branchName, String contactName, String phoneNumber){
        Super branch = dataController.getSuper(branchName);
        branch.setContactName(contactName);
        branch.setPhoneNumber(phoneNumber);
    }

    // remove a shift day from a driver
    public void RemoveShiftDay(String ID, int day){
        Driver driver = dataController.getDriver(ID);
        // if the worker works at this day - remove it from his shifts - if not dont do nothing
        if(!driver.checkDay(Days.values()[day]))
            driver.RemoveShift(Days.values()[day]);
    }
    // funciton to reset changes
    public void resetChanges() {changes.clear();}

    // function to get the changes
    public Set<String> getChanges() {return changes;}

}
