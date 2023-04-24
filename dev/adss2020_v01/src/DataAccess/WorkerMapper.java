package DataAccess;

import Domain.Jobs;
import Domain.Worker;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

//this will be singleton
public class WorkerMapper {
    private static WorkerMapper instance=new WorkerMapper();
    private static Map<String,Worker> WorkerMap;
    private WorkerMapper(){
        WorkerMap=new HashMap<>();
    }
    public static WorkerMapper GetInstance(){
        return instance;
    }

    /**
     * @param ID id of worker
     * @return the worker asked
     */
    public static Worker getWorker(String ID){
        //if i dont have this worker in the data ill go read it from DB
        if (WorkerMap.get(ID)==null){
            ReadWorker(ID);
        }
        return WorkerMap.get(ID);
    }

    /**
     * this functiuon read the worker from the db
     * @param ID id if worker
     *
     */
    private static void ReadWorker(String ID){
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
            Worker worker=new Worker(id,name,Integer.parseInt(bank),contract,Double.parseDouble(wage),password,LocalDate.parse(startdate),Double.parseDouble(bonus),Integer.parseInt(shiftworked));
            //add the worker to the map
            WorkerMap.put(id,worker);
            //add the roles to the worker
            ReadJobs(ID);
            //add the shifts to the worker

        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }

    /**
     * adds the roles to the worker
     * @param ID
     */
    private static void ReadJobs(String ID){
        Connection conn = Connect.getConnection();
        String job;
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("select * from WorkersJobs WHERE WorkerID=="+ID+"" );
            job=rs.getString("Job");
            WorkerMap.get(ID).AddJob(Jobs.valueOf(job));
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }
    /**
     * @return the map of all the workers
     */
    public static Map<String, Worker> getWorkerMap() {
        return WorkerMap;
    }
    public static void ReadAllWorkersFromSuper(String Name){
        Connection conn = Connect.getConnection();
        String id;
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("select * from WorkesAt WHERE SuperName=="+Name+"" );
            while (rs.next()){
                id=rs.getString("ID");
                //if i had him in database already i wont do it again
                getWorker(id);
            }
        }
        catch (SQLException e) {

        }
    }
    //we will write all the workers to the db when done, it will write the new ones and update the old ones??
    public static void WriteAllWorkers(){
        for (Worker worker:WorkerMap.values()){
            Connection conn = Connect.getConnection();
            String id,name, bank, startdate, contract, password, bonus, wage, shiftworked;
            try {
                java.sql.Statement stmt = conn.createStatement();
                id=worker.getID();
                name=worker.getName();
                bank=String.valueOf(worker.getBank());
                //check what this gives
                startdate=worker.getStartDate().toString();
                contract=worker.getContract();
                password=worker.getPassword();
                bonus=String.valueOf(worker.getBonus());
                wage=String.valueOf(worker.getWage());
                shiftworked=String.valueOf(worker.getShiftWorked());
                stmt.executeUpdate("INSERT OR UPDATE INTO Worker " +
                        "VALUES("+id+","+name+","+bank+","+startdate+","+contract+","+password+"," +
                        ""+bonus+","+wage+","+shiftworked+") ");
            }
            catch (SQLException e) {
                System.out.println("i have a problem sorry");
            }
        }
    }
    /*
    public static void UpdateWorker(String ID) {
        Worker worker=WorkerMap.get(ID);
        Connection conn = Connect.getConnection();
        String id,name, bank, startdate, contract, password, bonus, wage, shiftworked;
        try {
            java.sql.Statement stmt = conn.createStatement();
            id=worker.getID();
            name=worker.getName();
            bank=String.valueOf(worker.getBank());
            //check what this gives
            startdate=worker.getStartDate().toString();
            contract=worker.getContract();
            password=worker.getPassword();
            bonus=String.valueOf(worker.getBonus());
            wage=String.valueOf(worker.getWage());
            shiftworked=String.valueOf(worker.getShiftWorked());
            stmt.executeUpdate("UPDATE Worker SET name="+name+",bank="+bank+",startdate="+startdate+
                    ",contract="+contract+",password="+password+",bonus="+bonus+",wage="+wage+",shiftworked="+shiftworked+
                    " WHERE ID="+id);
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }

     */
    public static void main(String[] args) {
        ReadWorker("1");
        ReadWorker("2");
    }
}
