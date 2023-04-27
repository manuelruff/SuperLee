package DataAccess;

import Domain.Weekly;
import junit.framework.Test;

import java.nio.file.WatchKey;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//this will be singleton
public class WeeklyMapper {
    private static WeeklyMapper instance = new WeeklyMapper();
    //<Branchname,list of weekly>
    private static Map<String, List<Weekly>> WeeklyMap;
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
        if (WeeklyMap.get(Branch)==null || WeeklyMap.get(Branch).contains(StartDate)) {
            ReadWeekly(Branch,StartDate);
        }
        return WeeklyMap.get(Branch).get(WeeklyMap.get(Branch).indexOf(StartDate));
    }
    public static void ReadWeekly(String Branch,String StartDate) {
        try {
            java.sql.Statement stmt = conn.createStatement();
            //java.sql.ResultSet rs = stmt.executeQuery("select * from Super WHERE name=="+branchName+"" );
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Weekly WHERE SuperName = '" + Branch + "'" + " AND StartDate = '" + StartDate + "'");
            while (rs.next()) {
                Weekly weekly = new Weekly(StartDate);
                if(WeeklyMap.containsKey(Branch)){
                    WeeklyMap.put(Branch, new ArrayList<>());
                }
                WeeklyMap.get(Branch).add(weekly);
                //add the shifts to the weekly

            }
        } catch (Exception e) {
            System.out.println("i have a problem in weekly");
        }
    }


    public static void WriteAllWeekly() {
        String SuperName,StartDate;
        try {
            for ( String Branch:WeeklyMap.keySet()) {
                List<Weekly> weeklies=WeeklyMap.get(Branch);
                if(weeklies!=null){
                for(Weekly weekly :weeklies) {
                    SuperName = Branch;
                    StartDate = weekly.getStartDate().toString();
                    java.sql.Statement stmt = conn.createStatement();
                    stmt.executeUpdate("INSERT OR IGNORE INTO Weekly (StartDate, SuperName) VALUES ('" + StartDate + "','" + SuperName + "')");
                    //write the shifts
                    
                    }
                }
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem in writing the super sorry");
        }
    }
}
