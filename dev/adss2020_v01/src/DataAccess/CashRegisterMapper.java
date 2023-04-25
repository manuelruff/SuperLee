package DataAccess;
import Domain.Cancellations;
import Domain.CashRegister;
import Domain.CashRegisterController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CashRegisterMapper {
    //<String,CashRegister>
    private static CashRegisterMapper instance = new CashRegisterMapper();
    private static Map<String, CashRegister> CashRegisterMap;
    private static Map<String,String>AlreadyLoaded;
    private static Connection conn;

    private CashRegisterMapper(){
        CashRegisterMap = new HashMap<>();
        AlreadyLoaded = new HashMap<>();
        conn = Connect.getConnection();
    }

    public static CashRegisterMapper GetInstance(){
        if(instance == null)
            instance = new CashRegisterMapper();
        return instance;
    }

    public static void ReadCancellations(String BranchName,int year,int month,int day){
        Connection conn = Connect.getConnection();
        String StringDate = day+"."+month+"."+year;
        if(AlreadyLoaded.containsKey(BranchName)){
            return;
        }
        else {
            double amount;
            String NameOfCanceler, IDOfCanceler, NameOfProduct;
            try {
                java.sql.Statement stmt = conn.createStatement();
                //java.sql.ResultSet rs = stmt.executeQuery("select * from Cancellations WHERE Date==" + StringDate + " AND SuperName== " + BranchName + "");
                java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Cancellations WHERE Date='" + StringDate + "' AND SuperName='" + BranchName + "'");

                while(rs.next()){
                    NameOfCanceler = rs.getString("NameOfCanceler");
                    IDOfCanceler = rs.getString("IDOfCanceler");
                    NameOfProduct = rs.getString("NameOfProduct");
                    amount = rs.getDouble("Amount");
                    Cancellations cancel = new Cancellations(amount, NameOfProduct, IDOfCanceler, NameOfCanceler);
                    CashRegisterMap.get(BranchName).AddCancalation(cancel);
                }
                AlreadyLoaded.put(BranchName,StringDate);
            }
            catch (SQLException e) {
                System.out.println("i have a problem sorry");
            }
        }
    }

    // create new Cash register to the branch if it's not exist already
    public static void LoadBranchCashRegister(String BranchName){
        if(!CashRegisterMap.containsKey(BranchName)){
//            CashRegisterMap.put(BranchName,new CashRegister());
            CashRegisterMap.put(BranchName, CashRegisterController.getCashRegister(BranchName));
        }
    }

    public static void WriteAllCancellations(){
        for(String name:CashRegisterMap.keySet()){
            for (Cancellations cancellations : CashRegisterMap.get(name).getAllCancellations()){
                Connection conn = Connect.getConnection();
                String StringDate, StringTime ,NameOfCanceler, IDOfCanceler, NameOfProduct;
                double amount;
                try {
                    java.sql.Statement stmt = conn.createStatement();
                    amount =Double.parseDouble(String.valueOf(cancellations.getAmount()));
                    NameOfCanceler = cancellations.getNameOfCanceller();
                    IDOfCanceler = cancellations.getIDOfCanceller();
                    NameOfProduct = cancellations.getNameOfProduct();
                    StringDate = cancellations.getDate();
                    StringTime = cancellations.getTime();
                    stmt.executeUpdate("INSERT OR IGNORE INTO Cancellations (SuperName, Time, Date, Amount, NameOfCanceler, IDOfCanceler, NameOfProduct) VALUES ('" + name + "', '" + StringTime + "', '" + StringDate + "', "+ amount +", '" + NameOfCanceler + "', '" + IDOfCanceler +  "', '" +NameOfProduct +"')");
                }
                catch (SQLException e) {
                    System.out.println("i have a problem sorry");
                }
            }
        }

    }

}
