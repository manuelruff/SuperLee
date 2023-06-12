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
            dataController.getSuper(branch);
            if(!Superim.get(branch).HasWeekly()){
                return false;
            }
        }
        return true;
    }
    //this one checks for next week or this week, we need to knwo what he wants
    public boolean checkHasWeekly(List<String> branches, LocalDate date){
        for(String branch:branches){
            dataController.getSuper(branch);
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
        // if we dont have this shift return false
        for(String branch:branches){
            if(Superim.get(branch).GetWeekShifts().GetShift(shifttime).IsEmptyShift()||Superim.get(branch).GetWeekShifts().GetShift(shifttime+1).IsEmptyShift())
                return false;
        }
        // get all the branches that have can a store keeper-
        List<String>addedStoreKeeper1=CanAddStoreKeeper(branches,day,shifttime,0);
        List<String>addedStoreKeeper2=CanAddStoreKeeper(branches,day,shifttime+1,1);
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
    public List<String> CanAddStoreKeeper(List<String> branches,int day,int shiftnumber,int shiftTime){
        List<String> added=new ArrayList<>();
        Days day1=Days.values()[day];
        //check for each branch if he has a store keeper
        for(String branch:branches){
            if(Superim.get(branch).GetWeekShifts().GetShift(shiftnumber).IsEmptyShift())
                continue;
            boolean noStoreKeeperFlag = Superim.get(branch).GetWeekShifts().GetShift(shiftnumber).getWorkerList().containsKey(Jobs.StoreKeeper);
            if(!noStoreKeeperFlag){
                List <String> workers = Superim.get(branch).GetWorkersIDS();
                // check if there is a store keeper that can be added to the shift
                for(String worker:workers){
                    if(shiftTime == 0){
                    if((Workers.get(worker).CanDoJob(Jobs.StoreKeeper) && !Workers.get(worker).IsFree(day1,Superim.get(branch).getStart_morning(day1),Superim.get(branch).getEnd_morning(day1))))
                    {
                        Superim.get(branch).GetWeekShifts().GetShift(shiftnumber).AddWorker(Jobs.StoreKeeper, Workers.get(worker));
                        Workers.get(worker).AddShift(Days.values()[day]);
                        Workers.get(worker).AddShiftWorked();
                        added.add(branch);
                        break;
                        }
                    }
                    else{
                        if((Workers.get(worker).CanDoJob(Jobs.StoreKeeper) && !Workers.get(worker).IsFree(day1,Superim.get(branch).getStart_evening(day1),Superim.get(branch).getEnd_evening(day1))))
                        {
                            Superim.get(branch).GetWeekShifts().GetShift(shiftnumber).AddWorker(Jobs.StoreKeeper, Workers.get(worker));
                            Workers.get(worker).AddShift(Days.values()[day]);
                            Workers.get(worker).AddShiftWorked();
                            added.add(branch);
                            break;
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
        return dataController.getSupers();
    }

    // check if site is exist
    public boolean checkSite(String name){
        // check it from the mapper by the dataController
        return dataController.getSuper(name) != null;
    }

    //print all the drivers we have
    public void printDrivers(){
        //first read all the workers from the DB by the datacontroller
        dataController.loadAllWorkers();
        // print them
        for(Driver driver: Drivers.values()){
            driver.Printme();
            System.out.println();
        }
    }

    public void RemoveShiftFromDriver(String ID,int day){
        Driver driver = dataController.getDriver(ID);
        dataController.DeleteWorkingDays(ID,day);
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
    // funciton to reset changes
    public void resetChanges() {changes.clear();}

    // function to get the changes
    public Set<String> getChanges() {return changes;}

    public boolean checkStoreKeeperNow(List<String> branches,LocalDate day) {
        int count=0;
        for (String branch : branches) {
            dataController.getSuper(branch);
            for (Shift sh : Superim.get(branch).GetWeekShifts().getShiftList()) {
                if (sh.getDate() == day) {
                    if(sh.getWorkerList().get(Jobs.StoreKeeper)==null || sh.getWorkerList().get(Jobs.StoreKeeper).size()==0 ){
                        return false;
                    }
                    count++;
                }
            }
        }
        //we didnt even found a shift for the day
        if (count==0){
            return false;
        }
        return true;
    }


    //stuff for gui service
    //function that return representation of all drivers and their scedual in string
    public List<List<String>>getDriversSchedule(){
        //load all the drivers from the db
        dataController.loadAllWorkers();
        List<List<String>>ret=new ArrayList<>();
        for(Driver driver:Drivers.values()){
            List<String>temp=new ArrayList<>();
            temp.add(driver.getID());
            temp.add(driver.getName());
            List<Days> days=driver.getWeeklyWorkingDays();
            for (int i=0; i < days.size(); i++){
                temp.add((days.get(i).toString()));
            }
            ret.add(temp);
        }
        return ret;
    }
    //function that return representation 2 shifts in a day for a branch
    public List<List<String>>getShift(String name,int day){
        return helpGetWeekly(dataController.getSuper(name).GetWeekShifts(),day);
//        List<List<String>>ret=new ArrayList<>();
//        //get the two shifts we want
//        Shift shift1=dataController.getSuper(name).GetWeekShifts().GetShift(day*2);
//        Shift shift2=dataController.getSuper(name).GetWeekShifts().GetShift(day*2+1);
//        //ill put in one list the info of the mornning shift in the first place
//        List<String>sh1=new ArrayList<>();
//        sh1.add(shift1.getDate().toString());
//        sh1.add(shift1.getDate().getDayOfWeek().toString());
//        sh1.add(shift1.getShift_time().toString());
//        //put all the values inside
//        if(shift1.IsEmptyShift()){
//            sh1.add("Empty");
//        }
//        else{
//            sh1.add(Double.toString(shift1.getStart()));
//            sh1.add(Double.toString(shift1.getEnd()));
//            for (Jobs job : shift1.getWorkerList().keySet()) {
//                if(shift1.getWorkerList().get(job).size()!=0){
//                    sh1.add("As "+job+" the Workers are:");
//                    for (Worker worker: shift1.getWorkerList().get(job)){
//                        sh1.add("Name: "+worker.getName() + " with ID: "+worker.getID());
//                    }
//                }
//            }
//        }
//        //same for second shift
//        //ill put in one list the info of the evening shift in the second place
//        List<String>sh2=new ArrayList<>();
//        sh2.add(shift2.getDate().toString());
//        sh2.add(shift2.getDate().getDayOfWeek().toString());
//        sh2.add(shift2.getShift_time().toString());
//        if(shift2.IsEmptyShift()){
//            sh2.add("Empty");
//        }
//        else{
//            sh2.add(Double.toString(shift2.getStart()));
//            sh2.add(Double.toString(shift2.getEnd()));
//            for (Jobs job : shift2.getWorkerList().keySet()) {
//                if(shift2.getWorkerList().get(job).size()!=0){
//                    sh2.add("As "+job+" the Workers are:");
//                    for (Worker worker: shift2.getWorkerList().get(job)){
//                        sh2.add("Name: "+worker.getName() + " with ID: "+worker.getID());
//                    }
//                }
//            }
//        }
//        ret.add(sh1);
//        ret.add(sh2);
//        return ret;
    }
    //get info on shifts from weekly in history
    public List<List<String>> getWeeklyFromHist(String name, int year, int month, int day, int dayInWeek) {
        List<List<String>>ret=new ArrayList<>();
        //take date from input
        LocalDate date=LocalDate.of(year,month,day);
        Weekly week=dataController.getWeekly(name,date.toString());
        //if no weekly with this date we return null
        if (week==null){
            return null;
        }
        //if not null we return the day he wanted
        else{
            return helpGetWeekly(week,dayInWeek);
        }
    }


    private List<List<String>> helpGetWeekly(Weekly week,int day){
        List<List<String>>ret=new ArrayList<>();
        //get the two shifts we want
        Shift shift1=week.GetShift(day*2);
        Shift shift2=week.GetShift(day*2+1);
        //ill put in one list the info of the mornning shift in the first place
        List<String>sh1=new ArrayList<>();
        sh1.add(shift1.getDate().toString());
        sh1.add(shift1.getDate().getDayOfWeek().toString());
        sh1.add(shift1.getShift_time().toString());
        //put all the values inside
        if(shift1.IsEmptyShift()){
            sh1.add("Empty");
        }
        else{
            sh1.add(Double.toString(shift1.getStart()));
            sh1.add(Double.toString(shift1.getEnd()));
            for (Jobs job : shift1.getWorkerList().keySet()) {
                if(shift1.getWorkerList().get(job).size()!=0){
                    sh1.add("As "+job+" the Workers are:");
                    for (Worker worker: shift1.getWorkerList().get(job)){
                        sh1.add("Name: "+worker.getName() + " with ID: "+worker.getID());
                    }
                }
            }
        }
        //same for second shift
        //ill put in one list the info of the evening shift in the second place
        List<String>sh2=new ArrayList<>();
        sh2.add(shift2.getDate().toString());
        sh2.add(shift2.getDate().getDayOfWeek().toString());
        sh2.add(shift2.getShift_time().toString());
        if(shift2.IsEmptyShift()){
            sh2.add("Empty");
        }
        else{
            sh2.add(Double.toString(shift2.getStart()));
            sh2.add(Double.toString(shift2.getEnd()));
            for (Jobs job : shift2.getWorkerList().keySet()) {
                if(shift2.getWorkerList().get(job).size()!=0){
                    sh2.add("As "+job+" the Workers are:");
                    for (Worker worker: shift2.getWorkerList().get(job)){
                        sh2.add("Name: "+worker.getName() + " with ID: "+worker.getID());
                    }
                }
            }
        }
        ret.add(sh1);
        ret.add(sh2);
        return ret;
    }

    //get info on worker for him to update
    public List<String>getWorkerInfo(String id) {
        List<String>ret=new ArrayList<>();
        Worker w=dataController.getWorker(id);
        ret.add(w.getID());
        ret.add(w.getName());
        ret.add(w.getPassword());
        ret.add(String.valueOf(w.getBank()));
        return ret;
    }
    //get info on worker cant work days
    public List<String> getWorkerCantWorkDays(String id) {
        List<String> ret = new ArrayList<>();
        Worker w=dataController.getWorker(id);
        Map<Days, List<CantWork>> info=w.getShiftsCantWork();
        for (Days day:Days.values()) {
            if (info.get(day) != null) {
                ret.add(day.toString());
                for (CantWork c : info.get(day)) {
                    ret.add(c.toString());
                }
            }
        }
        return ret;
    }
}
