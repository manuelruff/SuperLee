package DataAccess;
import Domain.Cancellations;
import Domain.CashRegister;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CashRegisterMapper {
    //<String,CashRegister>
    private static CashRegisterMapper instance;
    private static Map<String, CashRegister> CashRegisterMap;
    private static Map<String,String>AlreadyLoaded;

    private CashRegisterMapper(){
        CashRegisterMap = new HashMap<>();
        AlreadyLoaded = new HashMap<>();
    }

    public static CashRegisterMapper GetInstance(){
        if(instance == null)
            instance = new CashRegisterMapper();
        return instance;
    }

    public static void ReadCancellations(String BranchName,int year,int month,int day){
        Connection conn = Connect.getConnection();
        String StringTime = day+"."+month+"."+year;
        if(AlreadyLoaded.get(BranchName).contains(StringTime)) {
            double amount;
            String NameOfCanceler, IDOfCanceler, NameOfProduct;
            try {
                java.sql.Statement stmt = conn.createStatement();
                java.sql.ResultSet rs = stmt.executeQuery("select * from Cancellations WHERE Time==" + StringTime + " AND SuperName== " + BranchName + "");
                while(rs.next()){
                    NameOfCanceler = rs.getString("NameOfCanceler");
                    IDOfCanceler = rs.getString("IDOfCanceler");
                    NameOfProduct = rs.getString("NameOfProduct");
                    amount = rs.getDouble("Amount");
                    Cancellations cancel = new Cancellations(amount, NameOfProduct, IDOfCanceler, NameOfCanceler);
                    CashRegisterMap.get(BranchName).AddCancalation(cancel);
                }
                AlreadyLoaded.put(BranchName,StringTime);
            }
            catch (SQLException e) {
                System.out.println("i have a problem sorry");
            }
        }
    }

    // create new Cash register to the branch if it's not exist already
    public static void LoadBranchCashRegister(String BranchName){
        if(!CashRegisterMap.containsKey(BranchName))
            CashRegisterMap.put(BranchName,new CashRegister());
    }
/*
    public static void WriteAllCancellation(){
        for(String name:CashRegisterMap.keySet()){
            for (Cancellations cancellations : CashRegisterMap.get(name).getAllCancellations()){
                Connection conn = Connect.getConnection();
                String SuperName,StringDate, NameOfCanceler, IDOfCanceler, NameOfProduct;
                double amount;
                try {
                    java.sql.Statement stmt = conn.createStatement();
                    amount =cancellations.getAmount();
                    NameOfCanceler = cancellations.getNameOfCanceller();
                    IDOfCanceler = cancellations.getIDOfCanceller();
                    NameOfProduct = cancellations.getNameOfProduct();
                    stmt.executeUpdate("INSERT INTO Worker " +
                            "VALUES("+id+","+name+","+bank+","+startdate+","+contract+","+password+"," +
                            ""+bonus+","+wage+","+shiftworked+") " +
                            "WHERE ("+id+" NOT IN (SELECT ID FROM Worker)");
                }
                catch (SQLException e) {
                    System.out.println("i have a problem sorry");
                }
            }
        }

    }



 */


}
