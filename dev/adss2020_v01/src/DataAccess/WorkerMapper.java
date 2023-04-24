package DataAccess;

import Domain.Worker;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//this will be singleton
public class WorkerMapper {
    private static WorkerMapper instance;

    private static Map<String,Worker> WorkerMap;
    private WorkerMapper(){
        WorkerMap=new HashMap<>();
    }

    public static WorkerMapper GetInstance(){
        if(instance==null){
            instance=new WorkerMapper();
        }
        return instance;
    }

    public static Worker getWorker(String ID){
        //if i dont have this worker in the data ill go read it from DB
        if (WorkerMap.get(ID)==null){
            ReadWorker(ID);
        }
        return WorkerMap.get(ID);
    }
    public static void ReadWorker(String ID){
        Connection conn = Connect.getConnection();
        String id,name, bank, startdate, contract, password, bonus, wage, shiftworked;
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("select * from Worker WHERE ID=="+ID+"" );
            id=rs.getString("ID");
            name=rs.getString("name");
            bank=rs.getString("bank");
            startdate=rs.getString("startdate");
            contract=rs.getString("contract");
            password=rs.getString("password");
            bonus=rs.getString("bonus");
            wage=rs.getString("wage");
            shiftworked=rs.getString("shiftworked");
            Worker worker=new Worker(id,name,Integer.parseInt(bank),contract,Integer.parseInt(wage),password);
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }

    public static void main(String[] args) {
        ReadWorker("1");
    }
}
