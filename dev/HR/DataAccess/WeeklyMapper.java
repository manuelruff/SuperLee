package HR.DataAccess;
import HR.Bussiness.*;
import DBConnect.Connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//this will be singleton
public class WeeklyMapper {
    private static WeeklyMapper instance;
    //<Branchname,map of weekly <startdate,weekly>>
    private Map<String, Map<String , Weekly>> WeeklyMap;
    private Connection conn;
    private WorkerMapper workerMapper;
    private WeeklyMapper() {
        WeeklyMap=new HashMap<>();
        conn = Connect.getConnection();
        workerMapper=WorkerMapper.getInstance();
    }
    public static WeeklyMapper getInstance() {
        if (instance == null) {
            instance = new WeeklyMapper();
        }
        return instance;
    }
    /**
     * @param Branch a branch name
     * @param StartDate the starting date of a week
     * @return a weekly object that we will put somewhere (in the super or to show to the user)
     */
    public Weekly getWeekly(String Branch,String StartDate) {
        //if we
        if (WeeklyMap.get(Branch)==null || WeeklyMap.get(Branch).containsKey(StartDate)) {
            if (!ReadWeekly(Branch, StartDate)){
                return null;
            }
        }
        return WeeklyMap.get(Branch).get(StartDate);
    }
    private boolean ReadWeekly(String Branch,String StartDate) {
        try {
            java.sql.Statement stmt = conn.createStatement();
            //java.sql.ResultSet rs = stmt.executeQuery("select * from Super WHERE name=="+branchName+"" );
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Weekly WHERE SuperName = '" + Branch + "'" + " AND StartDate = '" + StartDate + "'");
            if(!rs.isBeforeFirst()){
                return false;
            }
            while (rs.next()) {
                Weekly weekly = new Weekly(StartDate);
                if(!WeeklyMap.containsKey(Branch)){
                    WeeklyMap.put(Branch, new HashMap<>());
                }
                WeeklyMap.get(Branch).put(StartDate,weekly);
                //add the shifts to the weekly
                ReadShifts(Branch,StartDate);
            }
        } catch (Exception e) {
            System.out.println("i have a problem in weekly");
        }
        return true;
    }
    private void ReadShifts(String Branch,String StartDate){
        String date,start,end,shift_time;
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Shift WHERE StartDate='" + StartDate + "' AND SuperName='" + Branch + "'");
            while(rs.next()) {
                date = rs.getString("ShiftDate");
                start = rs.getString("Start");
                end = rs.getString("End");
                shift_time = rs.getString("ShiftTime");
                //add the shift to the weekly
                Shift curr=new Shift(LocalDate.parse(date), ShiftTime.valueOf(shift_time),Double.parseDouble(start),Double.parseDouble(end));
                WeeklyMap.get(Branch).get(StartDate).AddShift(curr);
                //read workers
                ReadWorkersFromShifts(Branch,curr);
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem in reading shifts");
        }
    }
    private void ReadWorkersFromShifts(String BranchName,Shift curr){
        //List<String> allWorkers = new ArrayList<>();
        String ShiftTime = curr.getShift_time().toString();
        String ShiftDate = curr.getDate().toString();
        String WorkerID,role;
        try{
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM WorksAtShift WHERE ShiftTime = '" + ShiftTime + "' AND ShiftDate='" + ShiftDate + "' AND SuperName='" + BranchName + "'");
            while(rs.next()) {
                WorkerID = rs.getString("WorkerID");
                role = rs.getString("Role");
                // add the worker to the workers list
                curr.AddWorker(Jobs.valueOf(role),workerMapper.getWorker(WorkerID));
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem in reading wrokers from shifts");
        }
    }
    public void WriteAllWeekly() {
        String SuperName,StartDate;
        try {
            for ( String Branch:WeeklyMap.keySet()) {
                Map<String ,Weekly> weeklies=WeeklyMap.get(Branch);
                if(weeklies!=null){
                for(Weekly weekly :weeklies.values()) {
                    SuperName = Branch;
                    StartDate = weekly.getStartDate().toString();
                    java.sql.Statement stmt = conn.createStatement();
                    stmt.executeUpdate("INSERT OR IGNORE INTO Weekly (StartDate, SuperName) VALUES ('" + StartDate + "','" + SuperName + "')");
                    //write the shifts
                    WriteShifts(Branch,StartDate);
                    }
                }
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem in writing the write weekly sorry");
        }
    }
    private void WriteShifts(String Branch,String StartDate){
        Weekly curr=WeeklyMap.get(Branch).get(StartDate);
        String date,shift_time;
        double start,end;
        //first we delete all we know about the shifts and then rewrite them
        DeleteShifts(Branch,StartDate);
        try {
            for (Shift shift : curr.getShiftList()) {
                date=shift.getDate().toString();
                shift_time=shift.getShift_time().toString();
                start=shift.getStart();
                end=shift.getEnd();
                java.sql.Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO Shift (StartDate, SuperName, ShiftDate, Start, End, ShiftTime)" +
                        " VALUES ('" + StartDate + "','" + Branch + "','"+ date +  "','"+start+ "','"+end +
                        "','"+ shift_time + "')");
                //write workers for the shift
                WriteWorkersToShifts(Branch,shift,StartDate);
            }
        }
        catch (SQLException e){
            System.out.println("i have a problem in writing the write shifts sorry");
        }
    }
    private void DeleteShifts(String Branch,String StartDate){
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM Shift WHERE StartDate='" + StartDate + "' AND SuperName='" + Branch + "'");
        }
        catch (SQLException e) {
            System.out.println("i have a problem in deleting shifts sorry");
        }
    }
    private void WriteWorkersToShifts(String BranchName,Shift curr,String StartDate){
        Map<Jobs, List<Worker>> workers = curr.getWorkerList();
        try {
            java.sql.Statement stmt = conn.createStatement();
            for(Jobs job: workers.keySet()){
                for(Worker worker : workers.get(job))
                {
                    stmt.executeUpdate("INSERT OR IGNORE INTO WorksAtShift(WorkerID, SuperName, StartDate, ShiftDate, ShiftTime, Role) VALUES (" + worker.getID() + ", '" + BranchName
                            + "', '" + StartDate + "', '" + curr.getDate().toString() + "', '" + curr.getShift_time().toString() + "', '" + job.toString() + "')");

                }
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem in writing workers to shift sorry");
        }
    }
    public void DeleteWorkerFromShift(String ID,Shift curr){
        String ShiftDate = curr.getDate().toString();
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM WorksAtShift WHERE WorkerID='" + ID + "' AND ShiftDate='" + ShiftDate + "'");
        }
        catch (SQLException e) {
            System.out.println("i have a problem iun deleting workers from shift sorry");
        }
    }
    public void InsertToMapper(String Branch,Weekly weekly){
        if(!WeeklyMap.containsKey(Branch)){
            WeeklyMap.put(Branch,new HashMap<>());
        }
        WeeklyMap.get(Branch).put(weekly.getStartDate().toString(),weekly);

    }



}
