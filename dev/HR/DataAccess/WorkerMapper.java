package HR.DataAccess;
import HR.Bussiness.*;
import resource.Connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

//this will be singleton
public class WorkerMapper {
    private static WorkerMapper instance=new WorkerMapper();
    private static Map<String, Worker> WorkerMap;
    private static Map<String, Driver> DriverMap;

    private static Connection conn;
    private WorkerMapper(){
        WorkerMap=new HashMap<>();
        DriverMap=new HashMap<>();
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
            //we will read it from the driver info table to know if its a driver
            java.sql.ResultSet isDriver = stmt.executeQuery("select * from DriverInfo WHERE DriverID=="+ID+"" );
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
                //check if its driver or worker
                if(isDriver.next()){
                    char licence=isDriver.getString("licence").charAt(0);
                    String training=isDriver.getString("training");
                    Driver driver=new Driver(id, name, Integer.parseInt(bank),
                            contract, Double.parseDouble(wage), password,
                            LocalDate.parse(startdate), Double.parseDouble(bonus),
                            Integer.parseInt(shiftworked),licence,Training.valueOf(training));
                    //add the driver to the map
                    DriverMap.put(id, driver);
                    //add the shifts days he has to the driver
                    ReadWorkingDays(ID);
                    //add the shifts cant work he has to the driver
                    ReadConstraints(ID);
                }
                //if worker we do it for worker
                else {
                    Worker worker = new Worker(id, name, Integer.parseInt(bank),
                            contract, Double.parseDouble(wage), password,
                            LocalDate.parse(startdate), Double.parseDouble(bonus),
                            Integer.parseInt(shiftworked));
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
    public static Map<String, Driver> getDriverMap() {
        return DriverMap;
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

    public static void ReadAllDriversByInfo(char licence,Training ability){
        //todo check if workign
        String id;
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM DriverInfo WHERE Licence = '" + licence + "' AND Training = '" + ability.toString() + "'");
            //ill get each worker
            while (rs.next()){
                id=rs.getString("DriverID");
                //if i had him in database already i wont do it again
                getWorker(id);
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }
    //write all workers from the mappers
    public static void WriteAllWorkers(){
        for (Worker worker:WorkerMap.values()){
            WriteAllAWorkers(worker);
            WriteJobs(worker.getID());
            //write his working days
            WriteWorkingDays(worker);
            //write the cant work days
            WriteConstraints(worker);
        }
    }

    public static void WriteAllDrivers(){
        for (Driver driver:DriverMap.values()){
            WriteAllAWorkers(driver);
            WriteAllLicenceAndTraining(driver.getID());
            //write his working days
            WriteWorkingDays(driver);
            //write the cant work days
            WriteConstraints(driver);
        }
    }
    //we will write all the workers to the db when done, it will write the new ones and update the old ones??
    public static void WriteAllAWorkers(AWorker worker){
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //we wrrite the licence and training
    public static void WriteAllLicenceAndTraining(String ID){
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT OR IGNORE INTO DriverInfo (DriverID,Licence,Training ) VALUES (" + ID + ", '" + DriverMap.get(ID) .getLicense() + "', '" + DriverMap.get(ID).getAbility() + "')");
        }
        catch (SQLException e) {
            System.out.println("i have a problem int writing the worker job sorry");
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
     * @param worker a worker or driver we work on
     */
    private static void WriteWorkingDays(AWorker worker){
        try {
            java.sql.Statement stmt = conn.createStatement();
            for (Days day : worker.getWeeklyWorkingDays()) {
                stmt.executeUpdate("INSERT OR IGNORE INTO WeeklyWorkingDays (WorkerID, Day) VALUES (" + worker.getID() + ", '" + day + "')");
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem in writing the workers days sorry");
        }
    }
    /**
     * update the constraints to the worker in the db
     * @param worker worker or driver we work on
     */
    private static void WriteConstraints(AWorker worker){
        try {
            java.sql.Statement stmt = conn.createStatement();
            for(Days day: worker.getShiftsCantWork().keySet()){
                for (CantWork cantwork : worker.getShiftsCantWork().get(day)) {
                    stmt.executeUpdate("INSERT OR IGNORE INTO CantWork (WorkerID, Start, END, DAY, Reason) VALUES (" + worker.getID() + ", '" + cantwork.getStart()
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
