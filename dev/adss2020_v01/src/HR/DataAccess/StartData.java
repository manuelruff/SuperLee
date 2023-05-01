package HR.DataAccess;
import  HR.Domain.*;
import HR.Domain.Jobs;
import HR.Domain.Super;
import HR.Domain.Worker;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//a class to fill the database for the first time
//this is a singleton
public class StartData {
    private static StartData instance=new StartData();
    private static Map<String, Worker> Workers;
    private static Map<String, Super> Superim;

    private StartData(){
        Workers=new HashMap<>();
        Superim=new HashMap<>();
        CreateData();
    }

    public static StartData getInstance(){
        return instance;
    }
    public static void CreateData(){
        //creating shift managers:
        Worker ShiftManager1 = new Worker("1","manu" , 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
        Worker ShiftManager2 = new Worker("2","david",  318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
        Worker ShiftManager3 = new Worker("3","muhamad",  318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
        Worker ShiftManager4 = new Worker( "4","omri", 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
        Worker ShiftManager5 = new Worker( "5", "oded",318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
        Worker ShiftManager6 = new Worker( "6","oded2", 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
        Worker ShiftManager7 = new Worker( "7","peleg", 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
        Worker ShiftManager8 = new Worker("8","mitzi",  318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
        Worker ShiftManager9 = new Worker( "9","hatul", 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
        //puts them in the worker list
        Workers.put("1", ShiftManager1);
        Workers.put("2", ShiftManager2);
        Workers.put("3", ShiftManager3);
        Workers.put("4", ShiftManager4);
        Workers.put("5", ShiftManager5);
        Workers.put("6", ShiftManager6);
        Workers.put("7", ShiftManager7);
        Workers.put("8", ShiftManager8);
        Workers.put("9", ShiftManager9);

        //create cashiers
        Worker Cashier1 = new Worker( "10","mikel", 318, "ata ahla gever", 60, Jobs.Cashier, "123");
        Worker Cashier2 = new Worker( "11","migul", 318, "ata ahla gever", 60, Jobs.Cashier, "123");
        Worker Cashier3 = new Worker( "12","huan", 318, "ata ahla gever", 60, Jobs.Cashier, "123");
        Worker Cashier4 = new Worker( "13","omri-escopar",318, "ata ahla gever", 60, Jobs.Cashier, "123");
        //puts them in the worker list
        Workers.put("10", Cashier1);
        Workers.put("11", Cashier2);
        Workers.put("12", Cashier3);
        Workers.put("13", Cashier4);

        //create StoreKeeper
        Worker StoreKeeper1 = new Worker( "14","oded", 318, "ata ahla gever", 60, Jobs.StoreKeeper, "123");
        Worker StoreKeeper2 = new Worker("15","gal",  318, "ata ahla gever", 60, Jobs.StoreKeeper, "123");
        Worker StoreKeeper3 = new Worker("16","papi",  318, "ata ahla gever", 60, Jobs.StoreKeeper, "123");
        Worker StoreKeeper4 = new Worker( "17","hatul", 318, "ata ahla gever", 60, Jobs.StoreKeeper, "123");
        //puts them in the worker list
        Workers.put("14", StoreKeeper1);
        Workers.put("15", StoreKeeper2);
        Workers.put("16", StoreKeeper3);
        Workers.put("17", StoreKeeper4);


        //create GeneralEmployee
        Worker GeneralEmp1 = new Worker( "18","odedi", 318, "ata ahla gever", 30, Jobs.GeneralEmp, "123");
        Worker GeneralEmp2 = new Worker( "19","shimun", 318, "ata ahla gever", 30, Jobs.GeneralEmp, "123");
        Worker GeneralEmp3 = new Worker( "20","sara", 318, "ata ahla gever", 30, Jobs.GeneralEmp, "123");
        Worker GeneralEmp4 = new Worker("21","gebi",  318, "ata ahla gever", 30, Jobs.GeneralEmp, "123");
        //puts them in the worker list
        Workers.put("18", GeneralEmp1);
        Workers.put("19", GeneralEmp2);
        Workers.put("20", GeneralEmp3);
        Workers.put("21", GeneralEmp4);

        //create guards
        Worker Guard1 = new Worker("22", "yuri", 318, "ata ahla gever", 50, Jobs.Guard, "123");
        Worker Guard2 = new Worker("23","dor",  318, "ata ahla gever", 50, Jobs.Guard, "123");
        Worker Guard3 = new Worker("24","shalev",  318, "ata ahla gever", 50, Jobs.Guard, "123");
        Worker Guard4 = new Worker("25","harel",  318, "ata ahla gever", 50, Jobs.Guard, "123");
        //puts them in the worker list
        Workers.put("22", Guard1);
        Workers.put("23", Guard2);
        Workers.put("24", Guard3);
        Workers.put("25", Guard4);

        //create cleaner
        Worker Cleaner1 = new Worker( "26","rohama", 318, "ata ahla gever", 10, Jobs.Cleaner, "123");
        Worker Cleaner2 = new Worker( "27","avraham", 318, "ata ahla gever", 10, Jobs.Cleaner, "123");
        Worker Cleaner3 = new Worker( "28","shoshana", 318, "ata ahla gever", 10, Jobs.Cleaner, "123");
        Worker Cleaner4 = new Worker("29","alo",  318, "ata ahla gever", 10, Jobs.Cleaner, "123");
        //puts them in the worker list
        Workers.put("26", Cleaner1);
        Workers.put("27", Cleaner2);
        Workers.put("28", Cleaner3);
        Workers.put("29", Cleaner4);

        //create usher
        Worker Usher1 = new Worker( "30","ahrak", 318, "ata ahla gever", 15, Jobs.Usher, "123");
        Worker Usher2 = new Worker( "31", "midbar",318, "ata ahla gever", 15, Jobs.Usher, "123");
        Worker Usher3 = new Worker( "32", "sahara",318, "ata ahla gever", 15, Jobs.Usher, "123");
        Worker Usher4 = new Worker( "33","alosantos", 318, "ata ahla gever", 15, Jobs.Usher, "123");
        //puts them in the worker list
        Workers.put("30", Usher1);
        Workers.put("31", Usher2);
        Workers.put("32", Usher3);
        Workers.put("33", Usher4);

        //create a super
        Super Super1 = new Super("zolretzah");
        Super Super2 = new Super("yakarmeod");

        //add to supers
        Super1.AddWorker(ShiftManager1);
        Super1.AddWorker(ShiftManager2);
        Super1.AddWorker(ShiftManager3);
        Super1.AddWorker(ShiftManager4);
        Super1.AddWorker(ShiftManager5);
        Super1.AddWorker(ShiftManager6);
        Super1.AddWorker(ShiftManager7);
        Super1.AddWorker(ShiftManager8);
        Super1.AddWorker(ShiftManager9);
        Super2.AddWorker(ShiftManager1);
        Super2.AddWorker(ShiftManager2);
        Super2.AddWorker(ShiftManager3);
        Super2.AddWorker(ShiftManager4);
        Super2.AddWorker(ShiftManager5);
        Super2.AddWorker(ShiftManager6);
        Super2.AddWorker(ShiftManager7);
        Super2.AddWorker(ShiftManager8);
        Super2.AddWorker(ShiftManager9);

        Super1.AddWorker(Cashier1);
        Super1.AddWorker(Cashier2);
        Super1.AddWorker(Cashier3);
        Super1.AddWorker(Cashier4);
        Super2.AddWorker(Cashier1);
        Super2.AddWorker(Cashier2);
        Super2.AddWorker(Cashier3);
        Super2.AddWorker(Cashier4);

        Super1.AddWorker(StoreKeeper1);
        Super1.AddWorker(StoreKeeper2);
        Super1.AddWorker(StoreKeeper3);
        Super1.AddWorker(StoreKeeper4);
        Super2.AddWorker(StoreKeeper1);
        Super2.AddWorker(StoreKeeper2);
        Super2.AddWorker(StoreKeeper3);
        Super2.AddWorker(StoreKeeper4);

        Super1.AddWorker(GeneralEmp1);
        Super1.AddWorker(GeneralEmp2);
        Super1.AddWorker(GeneralEmp3);
        Super1.AddWorker(GeneralEmp4);
        Super2.AddWorker(GeneralEmp1);
        Super2.AddWorker(GeneralEmp2);
        Super2.AddWorker(GeneralEmp3);
        Super2.AddWorker(GeneralEmp4);

        Super1.AddWorker(Guard1);
        Super1.AddWorker(Guard2);
        Super1.AddWorker(Guard3);
        Super1.AddWorker(Guard4);
        Super2.AddWorker(Guard1);
        Super2.AddWorker(Guard2);
        Super2.AddWorker(Guard3);
        Super2.AddWorker(Guard4);

        Super1.AddWorker(Cleaner1);
        Super1.AddWorker(Cleaner2);
        Super1.AddWorker(Cleaner3);
        Super1.AddWorker(Cleaner4);
        Super2.AddWorker(Cleaner1);
        Super2.AddWorker(Cleaner2);
        Super2.AddWorker(Cleaner3);
        Super2.AddWorker(Cleaner4);

        Super1.AddWorker(Usher1);
        Super1.AddWorker(Usher2);
        Super1.AddWorker(Usher3);
        Super1.AddWorker(Usher4);
        Super2.AddWorker(Usher1);
        Super2.AddWorker(Usher2);
        Super2.AddWorker(Usher3);
        Super2.AddWorker(Usher4);
        //add the super to the super list
        Superim.put("zolretzah", Super1);
        Superim.put("yakarmeod", Super2);
    }
    public static Map<String, Worker> getWorkers() {
        return Workers;
    }
    public static Map<String, Super> getSuperim() {
        return Superim;
    }
    public static void main(String[] args) {
        WriteWorkers();
        WriteSupers();
        WriteWokersToSupers();
        WriteWokersToJobs();
    }
    //write the workers to the database
    public static void WriteWorkers() {
        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            //shift managers
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (1,'manu',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (2,'david',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (3,'muhamad',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (4,'omri',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (5,'oded',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (6,'oded2',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (7,'peleg',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (8,'mitzi',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (9,'hatul',1,'2000-04-20','asd',123,0,130,0)");

            //cashiers
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (10,'mikel',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (11,'migul',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (12,'migul',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (13,'omri-escopar',1,'2000-04-20','asd',123,0,60,0)");

            //storekeepera
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (14,'oded',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (15,'gal',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (16,'papi',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (17,'hatul-escopar',1,'2000-04-20','asd',123,0,60,0)");
            //General Employees
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (18,'odedi',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (19,'shimun',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (20,'sara',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (21,'gebi',1,'2000-04-20','asd',123,0,30,0)");
            //guars

            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (22,'yuri',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (23,'dor',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (24,'shalev',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (25,'harel',1,'2000-04-20','asd',123,0,50,0)");

            //cleaners
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (26,'rohama',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (27,'avraham',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (28,'shoshana',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (29,'alo',1,'2000-04-20','asd',123,0,10,0)");

            //usher
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (30,'ahrak',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (31,'midbar',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (32,'sahara',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (33,'alosantos',1,'2000-04-20','asd',123,0,15,0)");

        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }
    //write the supers to the database
    public static void WriteSupers(){
        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Super(name) " +
                    "VALUES('zolretzah')");
            stmt.executeUpdate("INSERT INTO Super(name) " +
                    "VALUES('yakarmeod')");
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }
    //connect the workers and supers in thhe database
    public static void WriteWokersToSupers(){
        for (int i=1;i<=33;i++){
            Connection conn = Connect.getConnection();
            try {
                java.sql.Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " +
                        "VALUES("+i+",'yakarmeod')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " +
                        "VALUES("+i+",'zolretzah')");
            }
            catch (SQLException e) {
                System.out.println("i have a problem sorry");
            }
        }
    }
    //connect the workers and jobs in thhe database
    public static void WriteWokersToJobs(){
        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(1,'ShiftManager')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(2,'ShiftManager')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(3,'ShiftManager')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(4,'ShiftManager')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(5,'ShiftManager')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(6,'ShiftManager')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(7,'ShiftManager')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(8,'ShiftManager')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(9,'ShiftManager')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(10,'Cashier')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(11,'Cashier')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(12,'Cashier')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(13,'Cashier')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(14,'StoreKeeper')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(15,'StoreKeeper')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(16,'StoreKeeper')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(17,'StoreKeeper')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(18,'GeneralEmp')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(19,'GeneralEmp')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(20,'GeneralEmp')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(21,'GeneralEmp')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(22,'Guard')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(23,'Guard')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(24,'Guard')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(25,'Guard')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(26,'Cleaner')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(27,'Cleaner')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(28,'Cleaner')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(29,'Cleaner')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(30,'Usher')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(31,'Usher')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(32,'Usher')");
            stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " +
                    "VALUES(33,'Usher')");

        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }

}

