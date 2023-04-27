package DataAccess;

import Domain.Shift;
import Domain.ShiftTime;
import Domain.Weekly;
import Domain.Worker;
import junit.framework.Test;

import java.nio.file.WatchKey;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//this will be singleton
public class WeeklyMapper {
    private static WeeklyMapper instance = new WeeklyMapper();
    //<Branchname,map of weekly <startdate,weekly>>
    private static Map<String, Map<String ,Weekly>> WeeklyMap;
    private static Connection conn;

    private WeeklyMapper() {
        WeeklyMap=new HashMap<>();
        conn = Connect.getConnection();
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
    public static Weekly getWeekly(String Branch,String StartDate) {
        //if we
        if (WeeklyMap.get(Branch)==null || WeeklyMap.get(Branch).containsKey(StartDate)) {
            ReadWeekly(Branch,StartDate);
        }
        return WeeklyMap.get(Branch).get(WeeklyMap.get(Branch).get(StartDate));
    }
    private static void ReadWeekly(String Branch,String StartDate) {
        try {
            java.sql.Statement stmt = conn.createStatement();
            //java.sql.ResultSet rs = stmt.executeQuery("select * from Super WHERE name=="+branchName+"" );
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Weekly WHERE SuperName = '" + Branch + "'" + " AND StartDate = '" + StartDate + "'");
            while (rs.next()) {
                Weekly weekly = new Weekly(StartDate);
                if(WeeklyMap.containsKey(Branch)){
                    WeeklyMap.put(Branch, new HashMap<>());
                }
                WeeklyMap.get(Branch).put(StartDate,weekly);
                //add the shifts to the weekly
                ReadShifts(Branch,StartDate);
            }
        } catch (Exception e) {
            System.out.println("i have a problem in weekly");
        }
    }

    private static void ReadShifts(String Branch,String StartDate){
        String date,start,end,shift_time;
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Shift WHERE StartDate='" + StartDate + "' AND SuperName='" + Branch + "'");
            while(rs.next()) {
                date = rs.getString("ShiftDate");
                start = rs.getString("StartTime");
                end = rs.getString("EndTime");
                shift_time = rs.getString("ShiftTime");
                //add the shift to the weekly
                Shift curr=new Shift(LocalDate.parse(date), ShiftTime.valueOf(shift_time),Double.parseDouble(start),Double.parseDouble(end));
                WeeklyMap.get(Branch).get(StartDate).AddShift(curr);
                //read workers

            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem in reading shifts");
        }
    }

    private static void ReadWorkersFromShifts(Shift curr, String BranchName,String ShiftDate,String ShiftTime){
        List<String> allWorkers = new ArrayList<>();
        String WorkerID;
        try{
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT WorkerID FROM WorksAtShift WHERE ShiftTime = + ShiftTime + ShiftDate='" + ShiftDate + "' AND SuperName='" + BranchName + "'");
            while(rs.next()) {
                WorkerID = rs.getString("WorkerID");
                // add the worker to the workers list
                allWorkers.add(WorkerID);
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem in reading shifts");
        }
    }


    public static void WriteAllWeekly() {
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



    private static void WriteShifts(String Branch,String StartDate){
        Weekly curr=WeeklyMap.get(Branch).get(StartDate);
        String date,start,end,shift_time;
        try {
            for (Shift shift : curr.getShiftList()) {
                date=shift.getDate().toString();
                shift_time=shift.getShift_time().toString();
                start=String.valueOf(shift.getStart());
                end=String.valueOf(shift.getEnd());
                java.sql.Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO Shift (StartDate, SuperName, ShiftDate, Start, End, ShiftTime) " +
                        "VALUES ('" + StartDate + "','" + Branch + "','"+ date +  "','"+start
                        + "','"+end + "','"+ shift_time + "') " +
                        "ON DUPLICATE KEY UPDATE StartDate='" + StartDate + "', SuperName='"
                        + Branch + "', ShiftDate='" + date + "', Start='" + start + "'," +
                        " End='" + end + "', ShiftTime='" + shift_time + "'");
                //write workers for the shift
            }
        }
        catch (SQLException e){
            System.out.println("i have a problem in writing the write shifts sorry");
        }
    }
}
