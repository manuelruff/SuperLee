package HR.DataAccess;
import HR.Bussiness.Super;
import HR.Bussiness.Worker;
import DBConnect.Connect;

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
//        CreateData();
    }

    public static StartData getInstance(){
        return instance;
    }
//    public static void CreateData(){
//        //creating shift managers:
//        Worker ShiftManager1 = new Worker("1","manu" , 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
//        Worker ShiftManager2 = new Worker("2","david",  318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
//        Worker ShiftManager3 = new Worker("3","muhamad",  318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
//        Worker ShiftManager4 = new Worker( "4","omri", 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
//        Worker ShiftManager5 = new Worker( "5", "oded",318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
//        Worker ShiftManager6 = new Worker( "6","oded2", 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
//        Worker ShiftManager7 = new Worker( "7","peleg", 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
//        Worker ShiftManager8 = new Worker("8","mitzi",  318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
//        Worker ShiftManager9 = new Worker( "9","hatul", 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
//        //puts them in the worker list
//        Workers.put("1", ShiftManager1);
//        Workers.put("2", ShiftManager2);
//        Workers.put("3", ShiftManager3);
//        Workers.put("4", ShiftManager4);
//        Workers.put("5", ShiftManager5);
//        Workers.put("6", ShiftManager6);
//        Workers.put("7", ShiftManager7);
//        Workers.put("8", ShiftManager8);
//        Workers.put("9", ShiftManager9);
//
//        //create cashiers
//        Worker Cashier1 = new Worker( "10","mikel", 318, "ata ahla gever", 60, Jobs.Cashier, "123");
//        Worker Cashier2 = new Worker( "11","migul", 318, "ata ahla gever", 60, Jobs.Cashier, "123");
//        Worker Cashier3 = new Worker( "12","huan", 318, "ata ahla gever", 60, Jobs.Cashier, "123");
//        Worker Cashier4 = new Worker( "13","omri-escopar",318, "ata ahla gever", 60, Jobs.Cashier, "123");
//        //puts them in the worker list
//        Workers.put("10", Cashier1);
//        Workers.put("11", Cashier2);
//        Workers.put("12", Cashier3);
//        Workers.put("13", Cashier4);
//
//        //create StoreKeeper
//        Worker StoreKeeper1 = new Worker( "14","oded", 318, "ata ahla gever", 60, Jobs.StoreKeeper, "123");
//        Worker StoreKeeper2 = new Worker("15","gal",  318, "ata ahla gever", 60, Jobs.StoreKeeper, "123");
//        Worker StoreKeeper3 = new Worker("16","papi",  318, "ata ahla gever", 60, Jobs.StoreKeeper, "123");
//        Worker StoreKeeper4 = new Worker( "17","hatul", 318, "ata ahla gever", 60, Jobs.StoreKeeper, "123");
//        //puts them in the worker list
//        Workers.put("14", StoreKeeper1);
//        Workers.put("15", StoreKeeper2);
//        Workers.put("16", StoreKeeper3);
//        Workers.put("17", StoreKeeper4);
//
//
//        //create GeneralEmployee
//        Worker GeneralEmp1 = new Worker( "18","odedi", 318, "ata ahla gever", 30, Jobs.GeneralEmp, "123");
//        Worker GeneralEmp2 = new Worker( "19","shimun", 318, "ata ahla gever", 30, Jobs.GeneralEmp, "123");
//        Worker GeneralEmp3 = new Worker( "20","sara", 318, "ata ahla gever", 30, Jobs.GeneralEmp, "123");
//        Worker GeneralEmp4 = new Worker("21","gebi",  318, "ata ahla gever", 30, Jobs.GeneralEmp, "123");
//        //puts them in the worker list
//        Workers.put("18", GeneralEmp1);
//        Workers.put("19", GeneralEmp2);
//        Workers.put("20", GeneralEmp3);
//        Workers.put("21", GeneralEmp4);
//
//        //create guards
//        Worker Guard1 = new Worker("22", "yuri", 318, "ata ahla gever", 50, Jobs.Guard, "123");
//        Worker Guard2 = new Worker("23","dor",  318, "ata ahla gever", 50, Jobs.Guard, "123");
//        Worker Guard3 = new Worker("24","shalev",  318, "ata ahla gever", 50, Jobs.Guard, "123");
//        Worker Guard4 = new Worker("25","harel",  318, "ata ahla gever", 50, Jobs.Guard, "123");
//        //puts them in the worker list
//        Workers.put("22", Guard1);
//        Workers.put("23", Guard2);
//        Workers.put("24", Guard3);
//        Workers.put("25", Guard4);
//
//        //create cleaner
//        Worker Cleaner1 = new Worker( "26","rohama", 318, "ata ahla gever", 10, Jobs.Cleaner, "123");
//        Worker Cleaner2 = new Worker( "27","avraham", 318, "ata ahla gever", 10, Jobs.Cleaner, "123");
//        Worker Cleaner3 = new Worker( "28","shoshana", 318, "ata ahla gever", 10, Jobs.Cleaner, "123");
//        Worker Cleaner4 = new Worker("29","alo",  318, "ata ahla gever", 10, Jobs.Cleaner, "123");
//        //puts them in the worker list
//        Workers.put("26", Cleaner1);
//        Workers.put("27", Cleaner2);
//        Workers.put("28", Cleaner3);
//        Workers.put("29", Cleaner4);
//
//        //create usher
//        Worker Usher1 = new Worker( "30","ahrak", 318, "ata ahla gever", 15, Jobs.Usher, "123");
//        Worker Usher2 = new Worker( "31", "midbar",318, "ata ahla gever", 15, Jobs.Usher, "123");
//        Worker Usher3 = new Worker( "32", "sahara",318, "ata ahla gever", 15, Jobs.Usher, "123");
//        Worker Usher4 = new Worker( "33","alosantos", 318, "ata ahla gever", 15, Jobs.Usher, "123");
//        //puts them in the worker list
//        Workers.put("30", Usher1);
//        Workers.put("31", Usher2);
//        Workers.put("32", Usher3);
//        Workers.put("33", Usher4);
//
//        //create a super
//        Super Super1 = new Super("zolretzah");
//        Super Super2 = new Super("yakarmeod");
//
//        //add to supers
//        Super1.AddWorker(ShiftManager1);
//        Super1.AddWorker(ShiftManager2);
//        Super1.AddWorker(ShiftManager3);
//        Super1.AddWorker(ShiftManager4);
//        Super1.AddWorker(ShiftManager5);
//        Super1.AddWorker(ShiftManager6);
//        Super1.AddWorker(ShiftManager7);
//        Super1.AddWorker(ShiftManager8);
//        Super1.AddWorker(ShiftManager9);
//        Super2.AddWorker(ShiftManager1);
//        Super2.AddWorker(ShiftManager2);
//        Super2.AddWorker(ShiftManager3);
//        Super2.AddWorker(ShiftManager4);
//        Super2.AddWorker(ShiftManager5);
//        Super2.AddWorker(ShiftManager6);
//        Super2.AddWorker(ShiftManager7);
//        Super2.AddWorker(ShiftManager8);
//        Super2.AddWorker(ShiftManager9);
//
//        Super1.AddWorker(Cashier1);
//        Super1.AddWorker(Cashier2);
//        Super1.AddWorker(Cashier3);
//        Super1.AddWorker(Cashier4);
//        Super2.AddWorker(Cashier1);
//        Super2.AddWorker(Cashier2);
//        Super2.AddWorker(Cashier3);
//        Super2.AddWorker(Cashier4);
//
//        Super1.AddWorker(StoreKeeper1);
//        Super1.AddWorker(StoreKeeper2);
//        Super1.AddWorker(StoreKeeper3);
//        Super1.AddWorker(StoreKeeper4);
//        Super2.AddWorker(StoreKeeper1);
//        Super2.AddWorker(StoreKeeper2);
//        Super2.AddWorker(StoreKeeper3);
//        Super2.AddWorker(StoreKeeper4);
//
//        Super1.AddWorker(GeneralEmp1);
//        Super1.AddWorker(GeneralEmp2);
//        Super1.AddWorker(GeneralEmp3);
//        Super1.AddWorker(GeneralEmp4);
//        Super2.AddWorker(GeneralEmp1);
//        Super2.AddWorker(GeneralEmp2);
//        Super2.AddWorker(GeneralEmp3);
//        Super2.AddWorker(GeneralEmp4);
//
//        Super1.AddWorker(Guard1);
//        Super1.AddWorker(Guard2);
//        Super1.AddWorker(Guard3);
//        Super1.AddWorker(Guard4);
//        Super2.AddWorker(Guard1);
//        Super2.AddWorker(Guard2);
//        Super2.AddWorker(Guard3);
//        Super2.AddWorker(Guard4);
//
//        Super1.AddWorker(Cleaner1);
//        Super1.AddWorker(Cleaner2);
//        Super1.AddWorker(Cleaner3);
//        Super1.AddWorker(Cleaner4);
//        Super2.AddWorker(Cleaner1);
//        Super2.AddWorker(Cleaner2);
//        Super2.AddWorker(Cleaner3);
//        Super2.AddWorker(Cleaner4);
//
//        Super1.AddWorker(Usher1);
//        Super1.AddWorker(Usher2);
//        Super1.AddWorker(Usher3);
//        Super1.AddWorker(Usher4);
//        Super2.AddWorker(Usher1);
//        Super2.AddWorker(Usher2);
//        Super2.AddWorker(Usher3);
//        Super2.AddWorker(Usher4);
//        //add the super to the super list
//        Superim.put("zolretzah", Super1);
//        Superim.put("yakarmeod", Super2);
//    }
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
        WriteDrivers();
    }

    //write the drivers to the database
    public static void WriteDrivers() {
        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            //shift managers
            stmt.executeUpdate("INSERT INTO DriverInfo(DriverID, Licence, Training) VALUES (1000, 'D', 'Freezer')");
            stmt.executeUpdate("INSERT INTO DriverInfo(DriverID, Licence, Training) VALUES (1001, 'D', 'Freezer')");
            stmt.executeUpdate("INSERT INTO DriverInfo(DriverID, Licence, Training) VALUES (1002, 'D', 'Cooling')");
            stmt.executeUpdate("INSERT INTO DriverInfo(DriverID, Licence, Training) VALUES (1003, 'C', 'Regular')");
            stmt.executeUpdate("INSERT INTO DriverInfo(DriverID, Licence, Training) VALUES (1004, 'C', 'Freezer')");
            stmt.executeUpdate("INSERT INTO DriverInfo(DriverID, Licence, Training) VALUES (1005, 'D', 'Freezer')");
            stmt.executeUpdate("INSERT INTO DriverInfo(DriverID, Licence, Training) VALUES (1006, 'C', 'Cooling')");
            stmt.executeUpdate("INSERT INTO DriverInfo(DriverID, Licence, Training) VALUES (1007, 'D', 'Regular')");
            stmt.executeUpdate("INSERT INTO DriverInfo(DriverID, Licence, Training) VALUES (1008, 'C', 'Freezer')");
            stmt.executeUpdate("INSERT INTO DriverInfo(DriverID, Licence, Training) VALUES (1009, 'C', 'Freezer')");
            stmt.executeUpdate("INSERT INTO DriverInfo(DriverID, Licence, Training) VALUES (1010, 'D', 'Cooling')");
            stmt.executeUpdate("INSERT INTO DriverInfo(DriverID, Licence, Training) VALUES (1011, 'D', 'Regular')");
            stmt.executeUpdate("INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" + " VALUES (1000,'ron',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate("INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" + " VALUES (1001,'yanos',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate("INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" + " VALUES (1002,'roi',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate("INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" + " VALUES (1003,'ohayon',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate("INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" + " VALUES (1004,'ron',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate("INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" + " VALUES (1005,'yanos',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate("INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" + " VALUES (1006,'roi',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate("INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" + " VALUES (1007,'ohayon',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate("INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" + " VALUES (1008,'ron',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate("INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" + " VALUES (1009,'yanos',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate("INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" + " VALUES (1010,'roi',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate("INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" + " VALUES (1011,'ohayon',1,'2000-04-20','asd',123,0,130,0)");
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
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
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (10,'manu1',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (11,'manu2',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (12,'manu3',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (13,'manu4',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (14,'manu5',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (15,'manu6',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (16,'manu7',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (17,'manu',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (18,'david',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (19,'muhamad',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (20,'omri',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (21,'oded',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (22,'oded2',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (23,'peleg',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (24,'mitzi',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (25,'hatul',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (26,'manu',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (27,'david',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (28,'muhamad',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (29,'omri',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (30,'oded',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (31,'oded2',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (32,'peleg',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (33,'mitzi',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (34,'hatul',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (35,'manu1',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (36,'manu2',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (37,'manu3',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (38,'manu4',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (39,'manu5',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (40,'manu6',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (41,'manu7',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (42,'manu',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (43,'david',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (44,'muhamad',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (45,'omri',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (46,'oded',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (47,'oded2',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (48,'peleg',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (49,'mitzi',1,'2000-04-20','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (50,'hatul',1,'2000-04-20','asd',123,0,130,0)");

            //cashiers
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (100,'mikel',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (101,'migul',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (102,'migul',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (103,'omri-escopar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (104,'omri-escobar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (105,'omri-escorar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (106,'omri-escotar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (107,'omri-escoyar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (108,'omri-escouar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (109,'omri-escoiar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (110,'omri-escooar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (111,'omri-escopppppar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (112,'mikel',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (113,'migul',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (114,'migul',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (115,'omri-escopar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (116,'omri-escobar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (117,'omri-escorar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (118,'omri-escotar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (119,'omri-escoyar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (120,'omri-escouar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (121,'omri-escoiar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (122,'omri-escooar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (123,'omri-escopppppar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (124,'mikel',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (125,'migul',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (126,'migul',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (127,'omri-escopar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (128,'omri-escobar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (129,'omri-escorar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (130,'omri-escotar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (131,'omri-escoyar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (132,'omri-escouar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (133,'omri-escoiar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (134,'omri-escooar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (135,'omri-escopppppar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (136,'mikel',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (137,'migul',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (138,'migul',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (139,'omri-escopar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (140,'omri-escobar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (141,'omri-escorar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (142,'omri-escotar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (143,'omri-escoyar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (144,'omri-escouar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (145,'omri-escoiar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (146,'omri-escooar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (147,'omri-escopppppar',1,'2000-04-20','asd',123,0,60,0)");


            //storekeepera
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (200,'oded',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (201,'gal',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (202,'papi',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (203,'hatul-escopar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (204,'odediii',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (205,'gal-sama',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (206,'papi-cholo',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (207,'hatul-escopar',1,'2000-04-20','asd',123,0,60,0)");stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (208,'hatul-esaear',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (209,'hatul-esaaaar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (210,'hatul-esar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (211,'hatul-escobar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (212,'oded',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (213,'gal',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (214,'papi',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (215,'hatul-escopar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (216,'odediii',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (217,'gal-sama',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (218,'papi-cholo',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (219,'hatul-escopar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (220,'hatul-esaear',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (221,'hatul-esaaaar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (222,'hatul-esar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (223,'hatul-escobar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (224,'oded',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (225,'gal',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (226,'papi',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (227,'hatul-escopar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (228,'odediii',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (229,'gal-sama',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (230,'papi-cholo',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (231,'hatul-escopar',1,'2000-04-20','asd',123,0,60,0)");stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (232,'hatul-esaear',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (233,'hatul-esaaaar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (234,'hatul-esar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (235,'hatul-escobar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (236,'oded',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (237,'gal',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (238,'papi',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (239,'hatul-escopar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (240,'odediii',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (241,'gal-sama',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (242,'papi-cholo',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (243,'hatul-escopar',1,'2000-04-20','asd',123,0,60,0)");stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (244,'hatul-esaear',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (245,'hatul-esaaaar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (246,'hatul-esar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (247,'hatul-escobar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (248,'oded',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (249,'gal',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (250,'papi',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (251,'hatul-escopar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (252,'odediii',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (253,'gal-sama',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (254,'papi-cholo',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (255,'hatul-escopar',1,'2000-04-20','asd',123,0,60,0)");stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (256,'hatul-esaear',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (257,'hatul-esaaaar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (258,'hatul-esar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (259,'hatul-escobar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (260,'oded',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (261,'gal',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (262,'papi',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (263,'hatul-escopar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (264,'odediii',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (265,'gal-sama',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (266,'papi-cholo',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (267,'hatul-escopar',1,'2000-04-20','asd',123,0,60,0)");stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (268,'hatul-esaear',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (269,'hatul-esaaaar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (270,'hatul-esar',1,'2000-04-20','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (271,'hatul-escobar',1,'2000-04-20','asd',123,0,60,0)");

            //General Employees
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (300,'odedi',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (301,'shimun',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (302,'sara',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (303,'gebi',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (304,'avi',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (305,'avi',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (306,'avi',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (307,'avi',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (308,'muhamad',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (309,'muhamad',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (310,'yard',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (311,'yard',1,'2000-04-20','asd',123,0,30,0)");

            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (312,'odedi',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (313,'shimun',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (314,'sara',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (315,'gebi',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (316,'avi',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (317,'avi',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (318,'avi',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (319,'avi',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (320,'muhamad',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (321,'muhamad',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (322,'yard',1,'2000-04-20','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (323,'yard',1,'2000-04-20','asd',123,0,30,0)");

            //guars

            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (400,'yuri',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (401,'dor',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (402,'shalev',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (403,'harel',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (404,'yuri',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (405,'dor',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (406,'shalev',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (407,'harel',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (408,'yuri',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (409,'dor',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (410,'shalev',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (411,'harel',1,'2000-04-20','asd',123,0,50,0)");

            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (412,'yuri',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (413,'dor',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (414,'shalev',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (415,'harel',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (416,'yuri',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (417,'dor',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (418,'shalev',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (419,'harel',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (420,'yuri',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (421,'dor',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (422,'shalev',1,'2000-04-20','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (423,'harel',1,'2000-04-20','asd',123,0,50,0)");


            //cleaners
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (500,'rohama',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (501,'avraham',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (502,'shoshana',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (503,'alo',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (504,'rohama',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (505,'avraham',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (506,'shoshana',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (507,'alo',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (508,'rohama',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (509,'avraham',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (510,'shoshana',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (511,'alo',1,'2000-04-20','asd',123,0,10,0)");

            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (512,'rohama',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (513,'avraham',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (514,'shoshana',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (515,'alo',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (516,'rohama',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (517,'avraham',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (518,'shoshana',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (519,'alo',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (520,'rohama',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (521,'avraham',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (522,'shoshana',1,'2000-04-20','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (523,'alo',1,'2000-04-20','asd',123,0,10,0)");

            //usher
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (600,'ahrak',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (601,'midbar',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (602,'sahara',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (603,'alosantos',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (604,'ahrak',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (605,'midbar',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (606,'sahara',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (607,'alosantos',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (608,'ahrak',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (609,'midbar',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (610,'sahara',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (611,'alosantos',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (612,'ahrak',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (613,'midbar',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (614,'sahara',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (615,'alosantos',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (616,'ahrak',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (617,'midbar',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (618,'sahara',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (619,'alosantos',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (620,'ahrak',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (621,'midbar',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (622,'sahara',1,'2000-04-20','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (623,'alosantos',1,'2000-04-20','asd',123,0,15,0)");

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
            stmt.executeUpdate("INSERT INTO Super(name, PhoneNumber, ContactName, Zone, Address) " + "VALUES('branch1', '000000000', 'na', 'South', '123 Main St')");
            stmt.executeUpdate("INSERT INTO Super(name, PhoneNumber, ContactName, Zone, Address) " + "VALUES('branch2', '111111111', 'nah', 'South', '125 Main St')");
            stmt.executeUpdate("INSERT INTO Super(name, PhoneNumber, ContactName, Zone, Address) " + "VALUES('branch3', '222222222', 'nahm', 'Center', '125 Main St')");
            stmt.executeUpdate("INSERT INTO Super(name, PhoneNumber, ContactName, Zone, Address) " + "VALUES('branch4', '333333333', 'nahma', 'Center', '125 Main St')");
            stmt.executeUpdate("INSERT INTO Super(name, PhoneNumber, ContactName, Zone, Address) " + "VALUES('branch5', '444444444', 'nahman', 'Center', '125 Main St')");

        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }
    //connect the workers and supers in thhe database
    public static void WriteWokersToSupers(){
        for (int i=1;i<=50;i++){
            Connection conn = Connect.getConnection();
            try {
                java.sql.Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch1')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch2')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch3')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch4')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch5')");
            }
            catch (SQLException e) {
                System.out.println("i have a problem sorry");
            }
        }
        for (int i=100;i<=147;i++){
            Connection conn = Connect.getConnection();
            try {
                java.sql.Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch1')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch2')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch3')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch4')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch5')");
            }
            catch (SQLException e) {
                System.out.println("i have a problem sorry");
            }
        }
        for (int i=200;i<=271;i++){
            Connection conn = Connect.getConnection();
            try {
                java.sql.Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch1')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch2')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch3')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch4')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch5')");
            }
            catch (SQLException e) {
                System.out.println("i have a problem sorry");
            }
        }
        for (int i=300;i<=323;i++){
            Connection conn = Connect.getConnection();
            try {
                java.sql.Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch1')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch2')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch3')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch4')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch5')");
            }
            catch (SQLException e) {
                System.out.println("i have a problem sorry");
            }
        }
        for (int i=400;i<=423;i++){
            Connection conn = Connect.getConnection();
            try {
                java.sql.Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch1')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch2')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch3')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch4')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch5')");
            }
            catch (SQLException e) {
                System.out.println("i have a problem sorry");
            }
        }
        for (int i=500;i<=523;i++){
            Connection conn = Connect.getConnection();
            try {
                java.sql.Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch1')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " +
                        "VALUES("+i+",'branch2')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " +
                        "VALUES("+i+",'branch3')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " +
                        "VALUES("+i+",'branch4')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " +
                        "VALUES("+i+",'branch5')");
            }
            catch (SQLException e) {
                System.out.println("i have a problem sorry");
            }
        }
        for (int i=600;i<=623;i++){
            Connection conn = Connect.getConnection();
            try {
                java.sql.Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " + "VALUES("+i+",'branch1')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " +
                        "VALUES("+i+",'branch2')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " +
                        "VALUES("+i+",'branch3')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " +
                        "VALUES("+i+",'branch4')");
                stmt.executeUpdate("INSERT INTO WorksAt(WorkerId, SuperName) " +
                        "VALUES("+i+",'branch5')");
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
            for(int i=1;i<=50;i++){
                stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " + "VALUES("+i+",'ShiftManager')");
            }
            for(int i=100;i<=147;i++){
                stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " + "VALUES("+i+",'Cashier')");
                stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " + "VALUES("+i+",'StoreKeeper')");
            }
            for(int i=200;i<=271;i++){
                stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " + "VALUES("+i+",'StoreKeeper')");
            }
            for(int i=300;i<=323;i++){
                stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " + "VALUES("+i+",'GeneralEmp')");
                stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " + "VALUES("+i+",'StoreKeeper')");
            }
            for(int i=400;i<=423;i++){
                stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " + "VALUES("+i+",'Guard')");
                stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " + "VALUES("+i+",'StoreKeeper')");
            }
            for(int i=500;i<=523;i++){
                stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " + "VALUES("+i+",'Cleaner')");
            }
            for(int i=600;i<=623;i++){
                stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " + "VALUES("+i+",'Usher')");
                stmt.executeUpdate("INSERT INTO WorkersJobs(WorkerID, Job) " + "VALUES("+i+",'StoreKeeper')");
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }

}

