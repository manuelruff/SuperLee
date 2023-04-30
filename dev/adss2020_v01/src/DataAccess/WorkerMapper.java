package DataAccess;

import Domain.CantWork;
import Domain.Days;
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
    private static Connection conn;
    private WorkerMapper(){
        WorkerMap=new HashMap<>();
        conn = Connect.getConnection();
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
        String id,name, bank, startdate, contract, password, bonus, wage, shiftworked;
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("select * from Worker WHERE ID=="+ID+"" );
            if(rs.next()) {
                id = rs.getString("ID");
                name = rs.getString("name");
                bank = rs.getString("bank");
                startdate = rs.getString("startdate");
                contract = rs.getString("contract");
                password = rs.getString("password");
                bonus = rs.getString("bonus");
                wage = rs.getString("wage");
                //we dont need this one it will update when we add the shifts
                //shiftworked=rs.getString("shiftworked");
                shiftworked = "0";
                Worker worker = new Worker(id, name, Integer.parseInt(bank), contract, Double.parseDouble(wage), password, LocalDate.parse(startdate), Double.parseDouble(bonus), Integer.parseInt(shiftworked));
                //add the worker to the map
                WorkerMap.put(id, worker);
                //add the roles to the worker
                ReadJobs(ID);
                //add the shifts days he has to the worker
                ReadWorkingDays(ID);
                //add the shifts cant work he has to the worker
                ReadConstraints(ID);
            }
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
            String job;
            try {
                java.sql.Statement stmt = conn.createStatement();
                java.sql.ResultSet rs = stmt.executeQuery("select * from WorkersJobs WHERE WorkerID=="+ID+"" );
                while (rs.next()){
                    job=rs.getString("Job");
                    WorkerMap.get(ID).AddJob(Jobs.valueOf(job));
                }
            }
            catch (SQLException e) {
                System.out.println("i have a problem sorry");
        }
    }
    /**
     * adds the working days to the worker
     * @param ID
     */
    private static void ReadWorkingDays(String ID){
        String day;
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("select * from WeeklyWorkingDays WHERE WorkerID=="+ID+"" );
            while (rs.next()){
                day=rs.getString("Day");
                WorkerMap.get(ID).AddShift(Days.valueOf(day));
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }
    /**
     * adds the constraints to the worker
     * @param ID
     */
    private static void ReadConstraints(String ID){

        String start,end,reason,day;
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("select * from CantWork WHERE WorkerID=="+ID+"" );
            while (rs.next()){
                start=rs.getString("Start");
                end=rs.getString("End");
                reason=rs.getString("Reason");
                day=rs.getString("Day");
                WorkerMap.get(ID).AddCantWork(Days.valueOf(day),Double.parseDouble(start),Double.parseDouble(end),reason);
            }
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

    /**
     * @param Name name of branch
     *it will read all the workers from that branch
     */
    public static void ReadAllWorkersFromSuper(String Name){
        String id;
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM WorksAt WHERE SuperName='" + Name + "'");
            while (rs.next()){
                id=rs.getString("WorkerID");
                //if i had him in database already i wont do it again
                getWorker(id);
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }
    public static void ReadAllWorkers(){
        String id;
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("select * from Worker" );
            //ill get each worker
            while (rs.next()){
                id=rs.getString("ID");
                //if i had him in database already i wont do it again
                getWorker(id);
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }
    //we will write all the workers to the db when done, it will write the new ones and update the old ones??
    public static void WriteAllWorkers(){
        for (Worker worker:WorkerMap.values()){
            String id,name, startdate, contract, password;
            int bank,shiftworked;
            double bonus,wage;
            try {
                java.sql.Statement stmt = conn.createStatement();
                id=worker.getID();
                name=worker.getName();
                bank=Integer.parseInt(String.valueOf(worker.getBank()));
                startdate=worker.getStartDate().toString();
                contract=worker.getContract();
                password=worker.getPassword();
                bonus=Double.parseDouble(String.valueOf(worker.getBonus()));
                wage=Double.parseDouble(String.valueOf(worker.getWage()));
                shiftworked=Integer.parseInt((String.valueOf(worker.getShiftWorked())));
                java.sql.ResultSet rs = stmt.executeQuery("select * from Worker WHERE ID=="+id+"" );
                //if it doesnt exists we will insert it
                if(!rs.next()){
                    stmt.executeUpdate("INSERT INTO Worker(ID, name, bank, StartDate, contract, Password, bonus, wage, ShiftWorked) " +
                            "VALUES (" + id + ", '" + name + "', " + bank + ", '" + startdate + "', '" + contract + "', '" + password + "', " + bonus + ", " + wage + ", " + shiftworked + ")");

                }
                //if its in we update it
                else{
                    stmt.executeUpdate("UPDATE Worker SET name='" + name + "', bank='" + bank + "', StartDate='" + startdate +
                            "', contract='" + contract + "', Password='" + password + "', bonus=" + bonus + ", wage=" + wage +
                            ", ShiftWorked=" + shiftworked + " WHERE ID=" + id);
                    //we need to update all his other stuff
                }

                //need to update constraint working days and etc...
                WriteJobs(id);
                WriteWorkingDays(id);
                WriteConstraints(id);
            }
            catch (SQLException e) {
                System.out.println("i have a problem in writing the worker sorry");
            }
        }
    }

    /**
     * update the roles to the worker in the db
     * @param ID
     */
    private static void WriteJobs(String ID){
        try {
            java.sql.Statement stmt = conn.createStatement();
            for (Jobs job : WorkerMap.get(ID).getRoles()) {
                stmt.executeUpdate("INSERT OR IGNORE INTO WorkersJobs (WORKERID, JOB) VALUES (" + ID + ", '" + job.toString() + "')");
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem int writing the worker job sorry");
        }
    }
    /**
     * update the working days to the worker in the db
     * @param ID
     */
    private static void WriteWorkingDays(String ID){
        try {
            java.sql.Statement stmt = conn.createStatement();
            for (Days day : WorkerMap.get(ID).getWeeklyWorkingDays()) {
                stmt.executeUpdate("INSERT OR IGNORE INTO WeeklyWorkingDays (WorkerID, Day) VALUES (" + ID + ", '" + day + "')");
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem in writing the workers days sorry");
        }
    }
    /**
     * update the constraints to the worker in the db
     * @param ID
     */
    private static void WriteConstraints(String ID){
        try {
            java.sql.Statement stmt = conn.createStatement();
            for(Days day: WorkerMap.get(ID).getShiftsCantWork().keySet()){
                for (CantWork cantwork : WorkerMap.get(ID).getShiftsCantWork().get(day)) {
                    stmt.executeUpdate("INSERT OR IGNORE INTO CantWork (WorkerID, Start, END, DAY, Reason) VALUES (" + ID + ", '" + cantwork.getStart()
                            + "', '" + cantwork.getEnd() + "', '" + day + "', '" + cantwork.getReason() + "')");
                }
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem iun writing constraints sorry");
        }
    }
    /**
     * we will delete his constraints and it will be updated with the new ones in the db after the program closees
     * @param ID id of worker
     */
    public static void DeleteConstraints(String ID){
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM CantWork WHERE WorkerID="+ID+"");
        }
        catch (SQLException e) {
            System.out.println("i have a problem iun writing constraints sorry");
        }
    }
    /**
     * we will delete his working days and it will be updated with the new ones in the db after the program closees
     * @param ID id of worker
     */
    public static void DeleteWorkingDays(String ID){
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM WeeklyWorkingDays WHERE WorkerID="+ID+"");
        }
        catch (SQLException e) {
            System.out.println("i have a problem iun writing constraints sorry");
        }
    }




//    public static void main(String[] args) {
//        ReadWorker("1");
//        ReadWorker("2");
//    }
}
