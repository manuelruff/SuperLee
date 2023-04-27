package DataAccess;
import Domain.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
public class SuperMapper {
    private static SuperMapper instance = new SuperMapper();
    private static Map<String, Super> SuperMap;
    private static Connection conn;
    private SuperMapper(){
        SuperMap=new HashMap<>();
        conn = Connect.getConnection();
    }
    public static SuperMapper GetInstance(){return instance;}
    public static Map<String,Super>getSuperMap(){return SuperMap;}
    /**
     * @param name  of branch
     * @return the branch asked
     */
    public static Super getsuper(String name){
        //if i dont have this worker in the data ill go read it from DB
        if (SuperMap.get(name)==null){
            ReadSuper(name);
        }
        return SuperMap.get(name);
    }
    /**
     * this functiuon read the brnach from the db
     * @param branchName name of super
     *
     */
    private static void ReadSuper(String branchName){
        String name;
        try {
            java.sql.Statement stmt = conn.createStatement();
            //java.sql.ResultSet rs = stmt.executeQuery("select * from Super WHERE name=="+branchName+"" );
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Super WHERE name = '" + branchName + "'");
            if(rs.next()) {
                name = rs.getString("name");
                Super branch = new Super(name);
                //add the worker to the map
                SuperMap.put(name, branch);
                //load his cash registers to the mapper of the cash register
                CashRegisterMapper.PutCashRegister(name,SuperMap.get(name).get_cash_register());
                //rad the times of shift in each day
                ReadSuperTime(name);
                //calculate the date of next week
                LocalDate StartDate=LocalDate.now();
                //we want to start at next sunday so we add one until we are there
                DayOfWeek day=StartDate.getDayOfWeek();
                while(day!=DayOfWeek.SUNDAY){
                    StartDate=StartDate.plusDays(1);
                    day=StartDate.getDayOfWeek();
                }
                //read the weekly
                SuperMap.get(branchName).AddWeekly(WeeklyMapper.getWeekly(branchName,StartDate.toString()));
                //read the workers
                ReadWorkers(branchName);
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }
    private static void ReadSuperTime(String BranchName){
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
    private static void ReadWorkers(String BranchName){
        String ID;
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM WorksAt WHERE SuperName = '" + BranchName + "'");
            while (rs.next()){
                ID = rs.getString("WorkerID");
                SuperMap.get(BranchName).AddWorker(WorkerMapper.getWorker(ID));
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }
    public static void WriteAllSupers() {
        for (Super branch : SuperMap.values()) {
            String name;
            try {
                java.sql.Statement stmt = conn.createStatement();
                name = branch.getName();
                stmt.executeUpdate("INSERT OR IGNORE INTO Super (name) VALUES ('" + name + "')");
                //write weekly
                WeeklyMapper.InsertToMapper(branch.getName(),branch.GetWeekShifts());
                // write the super hours
                WriteSuperTime(branch.getName());
                //write all workers
                WriteWorkers(branch.getName());
            } catch (SQLException e) {
                System.out.println("i have a problem in writing the super sorry");
            }
        }
    }
    private static void WriteSuperTime(String BranchName){
        try {
            java.sql.Statement stmt = conn.createStatement();
            Super curr = SuperMap.get(BranchName);
            for (Days day : Days.values()) {
                double sm = curr.getStart_morning(day);
                double em = curr.getEnd_morning(day);
                double se = curr.getStart_evening(day);
                double ee = curr.getEnd_evening(day);
                stmt.executeUpdate("INSERT OR IGNORE INTO SuperShiftsTime (SuperName, Day, StartMorning, EndMorning, StartEvening, EndEvening) VALUES ('" + BranchName + "', '" + day.toString() + "', '" + sm + "', '" + em + "', '" + se + "', '" + ee + "')");
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem in writing the super time sorry");
        }
    }
    private  static void WriteWorkers(String BranchName){
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
}
