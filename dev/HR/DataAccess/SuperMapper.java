package HR.DataAccess;
import HR.Bussiness.Days;
import HR.Bussiness.Super;
import HR.Bussiness.Zone;
import DBConnect.Connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class SuperMapper {
    private static SuperMapper instance;
    private Map<String, Super> SuperMap;
    private WorkerMapper workerMapper;
    private Connection conn;
    private WeeklyMapper weeklyMapper;
    private CashRegisterMapper cashRegisterMapper;
    private SuperMapper(){
        SuperMap=new HashMap<>();
        conn = Connect.getConnection();
        cashRegisterMapper=CashRegisterMapper.getInstance();
        workerMapper=WorkerMapper.getInstance();
        weeklyMapper=WeeklyMapper.getInstance();
    }
    public static SuperMapper getInstance(){
        if(instance==null)
            instance=new SuperMapper();
        return instance;
    }
    public Map<String,Super>getSuperMap(){return SuperMap;}
    /**
     * @param name  of branch
     * @return the branch asked
     */
    public Super getsuper(String name){
        //if i dont have this worker in the data ill go read it from DB
        if (SuperMap.get(name)==null){
            ReadSuper(name);
        }
        return SuperMap.get(name);
    }

    public List<List<String>> readSupers(){
        try {
            String name,address,phoneNumber,contactName,zone;
            List<List<String>> list=new ArrayList<>();
            java.sql.Statement stmt = conn.createStatement();
            //java.sql.ResultSet rs = stmt.executeQuery("select * from Super WHERE name=="+branchName+"" );
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Super");
            int index=0;
            while(rs.next()) {
                list.add(new ArrayList<>());
                name = rs.getString("name");
                address = rs.getString("Address");
                phoneNumber= rs.getString("PhoneNumber");
                contactName= rs.getString("ContactName");
                zone= rs.getString("Zone");
                list.get(index).add(name);
                list.get(index).add(address);
                list.get(index).add(phoneNumber);
                list.get(index).add(contactName);
                list.get(index).add(zone);
                index++;
            }
            return list;
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
            return null;
        }
    }

    public void readAllSupers(){
        try {
            String name;
            java.sql.Statement stmt = conn.createStatement();
            //java.sql.ResultSet rs = stmt.executeQuery("select * from Super WHERE name=="+branchName+"" );
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Super");
            int index=0;
            while(rs.next()) {
                name = rs.getString("name");
                getsuper(name);
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }
    /**
     * this functiuon read the brnach from the db
     * @param branchName name of super
     *
     */
    private void ReadSuper(String branchName){
        String name,address,phoneNumber,contactName,zone;
        try {
            java.sql.Statement stmt = conn.createStatement();
            //java.sql.ResultSet rs = stmt.executeQuery("select * from Super WHERE name=="+branchName+"" );
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Super WHERE name = '" + branchName + "'");
            if(rs.next()) {
                name = rs.getString("name");
                address = rs.getString("Address");
                phoneNumber= rs.getString("PhoneNumber");
                contactName= rs.getString("ContactName");
                zone= rs.getString("Zone");
                Super branch = new Super(name,address,phoneNumber,contactName, Zone.valueOf(zone));
                //add the worker to the map
                SuperMap.put(name, branch);
                //load his cash registers to the mapper of the cash register
                cashRegisterMapper.PutCashRegister(name,SuperMap.get(name).get_cash_register());
                //rad the times of shift in each day
                ReadSuperTime(name);
                //calculate the date of next week
                String StartDate=GetLastDate();
                //we want to start at next sunday so we add one until we are there
//                DayOfWeek day=StartDate.getDayOfWeek();
//                while(day!=DayOfWeek.SUNDAY){
//                    StartDate=StartDate.plusDays(1);
//                    day=StartDate.getDayOfWeek();
//                }
                //read the weekly
                SuperMap.get(branchName).setWeekly(weeklyMapper.getWeekly(branchName,StartDate));
                //read the workers
                ReadWorkers(branchName);
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }
    private String GetLastDate(){
        String dateMax = "";
        Date date=null;
        LocalDate next=LocalDate.now();
        //we want to start at next sunday so we add one until we are there
        DayOfWeek day=next.getDayOfWeek();
        while(day!=DayOfWeek.SUNDAY){
            next=next.plusDays(1);
            day=next.getDayOfWeek();
        }
        LocalDate prev=LocalDate.now();
        day=prev.getDayOfWeek();
        //we want to start at next sunday so we add one until we are there
        while(day!=DayOfWeek.SUNDAY){
            prev=prev.minusDays(1);
            day=prev.getDayOfWeek();
        }
        String prevString = prev.toString();
        String nextString = next.toString();
        try{
            java.sql.Statement stmt = conn.createStatement();
//            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Weekly WHERE StartDate >= (SELECT * FROM Weekly WHERE StartDate )");
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Weekly WHERE StartDate = '" + nextString + "'");
            if(rs.next()) {
                dateMax = rs.getString("StartDate");
                return dateMax;
            }
            else{
                rs = stmt.executeQuery("SELECT * FROM Weekly WHERE StartDate = '" + prevString + "'");
                if(rs.next()){
                    dateMax = rs.getString("StartDate");
                    return dateMax;
                }
                return prevString;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void ReadSuperTime(String BranchName){
        double sm,em,se,ee;
        String day;
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM SuperShiftsTime WHERE SuperName = '" + BranchName + "'");
            while (rs.next()){
                sm=rs.getDouble("StartMorning");
                em=rs.getDouble("EndMorning");
                se=rs.getDouble("StartEvening");
                ee=rs.getDouble("EndEvening");
                day = rs.getString("Day");
                SuperMap.get(BranchName).setStart_morning(Days.valueOf(day),sm);
                SuperMap.get(BranchName).setEnd_morning(Days.valueOf(day),em);
                SuperMap.get(BranchName).setStart_evening(Days.valueOf(day),se);
                SuperMap.get(BranchName).setEnd_evening(Days.valueOf(day),ee);
            }
        }
        catch (SQLException e) {
            System.out.println("i have a with Read super time problem sorry");
        }
    }
    private void ReadWorkers(String BranchName){
        String ID;
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM WorksAt WHERE SuperName = '" + BranchName + "'");
            while (rs.next()){
                ID = rs.getString("WorkerID");
                SuperMap.get(BranchName).AddWorker(workerMapper.getWorker(ID));
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }
    public void WriteAllSupers() {
        for (Super branch : SuperMap.values()) {
            String name,address,phoneNumber,contactName,zone;
            try {
                java.sql.Statement stmt = conn.createStatement();
                name = branch.getName();
                address=branch.getAddress();
                phoneNumber=branch.getPhoneNumber();
                contactName=branch.getContactName();
                zone=branch.getZone().toString();
                stmt.executeUpdate("INSERT OR IGNORE INTO Super (name, Address, PhoneNumber, ContactName, Zone) VALUES ('" + name + "', '" + address + "', '" + phoneNumber + "', '" + contactName + "', '" + zone + "')");
                //write weekly
                if(branch.GetWeekShifts()!= null) {
                    weeklyMapper.InsertToMapper(branch.getName(), branch.GetWeekShifts());
                }
                // write the super hours
                WriteSuperTime(branch.getName());
                //write all workers
                WriteWorkers(branch.getName());
            } catch (SQLException e) {
                System.out.println("i have a problem in writing the super sorry");
            }
        }
    }
    private void WriteSuperTime(String BranchName){
        try {
            java.sql.Statement stmt = conn.createStatement();
            Super curr = SuperMap.get(BranchName);
            for (Days day : Days.values()) {
                double sm = curr.getStart_morning(day);
                double em = curr.getEnd_morning(day);
                double se = curr.getStart_evening(day);
                double ee = curr.getEnd_evening(day);
                stmt.executeUpdate("INSERT OR REPLACE INTO SuperShiftsTime (SuperName, Day, StartMorning, EndMorning, StartEvening, EndEvening) VALUES ('" + BranchName + "', '" + day.toString() + "', '" + sm + "', '" + em + "', '" + se + "', '" + ee + "')");
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem in writing the super time sorry");
        }
    }
    private void WriteWorkers(String BranchName){
        try {
            java.sql.Statement stmt = conn.createStatement();
            Super curr = SuperMap.get(BranchName);
            for (String worker : curr.GetWorkersIDS()) {
                stmt.executeUpdate("INSERT OR IGNORE INTO WorksAt (SuperName, WorkerID) VALUES ('" + BranchName + "', '" + worker + "')");
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem in writing the workers sorry");
        }
    }

    public void deleteBranch(String branch){
        deleteSuperTime(branch);
        //delete the works at of workers
        deleteWorksAt(branch);

        //todo delete the shifts and weekly that didnt happen yet??
        //delete the workers from the shifts of this branch
        //deleteWorksAtShift(branch);

        //delete the branch
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM Super WHERE name='" + branch + "'");
        }
        catch (SQLException e) {
            System.out.println("i have a problem in deleting super sorry");
        }
    }
    private void deleteSuperTime(String branch){
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM SuperShiftsTime WHERE SuperName='" + branch + "'");
        }
        catch (SQLException e) {
            System.out.println("i have a problem in deleting SuperShiftsTime sorry");
        }
    }
    private void deleteWorksAt(String branch){
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM WorksAt WHERE SuperName='" + branch + "'");
        }
        catch (SQLException e) {
            System.out.println("i have a problem in deleting WorksAt sorry");
        }
    }
    private void deleteWorksAtShift(String branch){
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM WorksAtShift WHERE SuperName='" + branch + "'");
        }
        catch (SQLException e) {
            System.out.println("i have a problem in deleting WorksAtShift sorry");
        }
    }
}
