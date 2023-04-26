package DataAccess;

import Domain.Days;
import Domain.Super;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ShiftMapper {
    private static ShiftMapper instance = new ShiftMapper();
    // branchName,date
    private static Map<String,String>ShiftMap;
    private static Connection conn;

    private ShiftMapper(){
        ShiftMap = new HashMap<>();
        conn=Connect.getConnection();
    }
    public static ShiftMapper GetInstance(){return instance;}
    public static Map<String,String>getShiftMap(){return ShiftMap;}

//    private static void ReadShift(String branchName,String date){
//        String name;
//        try {
//            java.sql.Statement stmt = conn.createStatement();
//            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Shift WHERE ShiftDate='" + date + "' AND SuperName='" + branchName + "'");
//            if(rs.next()) {
//                name = rs.getString("name");
//                Super branch = new Super(name);
//                //add the worker to the map
//                ShiftMap.put(name, branch);
//            }
//        }
//        catch (SQLException e) {
//            System.out.println("i have a problem sorry");
//        }
//    }


//    private static void WriteShiftHours(String BranchName, Days day){
//        Super curr = SuperMap.get(BranchName);
//        double sm = curr.getStart_morning(day);
//        double em = curr.getEnd_morning(day);
//        double se = curr.getStart_evening(day);
//        double ee = curr.getEnd_evening(day);
//        try{
//            java.sql.Statement stmt = conn.createStatement();
//            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM WeeklyShiftsTime WHERE Day='" + day.toString() + "' AND SuperName='" + BranchName + "'");
//            //if it doesnt exists we will insert it
//            if(!rs.next()){
//                stmt.executeUpdate("INSERT INTO WeeklyShiftsTime(SuperName, Day, StartMorning, EndMorning, StartEvening, EndEvening) " +
//                        "VALUES ('" + BranchName + "', '" + day.toString() + "', '" + sm + "', '" + em + "', '" + se + "', '" + ee + "')");
//            }
//            //if its in we update it
//            else{
//                stmt.executeUpdate("UPDATE WeeklyShiftsTime SET StartMorning = '" + sm + "', EndMorning = '" + em + "', StartEvening = '" + se + "', EndEvening = '" + ee + "' WHERE SuperName = '" + BranchName + "' AND Day = '" + day.toString() + "'");
//            }
//        } catch (SQLException e) {
//            System.out.println("i have a problem with write shift hours");
//        }

//    }



}
