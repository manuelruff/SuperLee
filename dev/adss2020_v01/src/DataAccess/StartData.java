package DataAccess;
import Domain.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//a class to fill the database for the first time

public class StartData {
    private static Map<String, Worker> Workers;
    private static Map<String, Super> Superim;

    public StartData(){
        Workers=new HashMap<>();
        Superim=new HashMap<>();
        CreateData();
    }
    public static void CreateData(){
        //creating shift managers:
        Worker ShiftManager1 = new Worker("manu", "1", 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
        Worker ShiftManager2 = new Worker("david", "2", 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
        Worker ShiftManager3 = new Worker("muhamad", "3", 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
        Worker ShiftManager4 = new Worker("omri", "4", 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
        Worker ShiftManager5 = new Worker("oded", "5", 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
        Worker ShiftManager6 = new Worker("oded2", "6", 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
        Worker ShiftManager7 = new Worker("peleg", "7", 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
        Worker ShiftManager8 = new Worker("mitzi", "8", 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
        Worker ShiftManager9 = new Worker("hatul", "9", 318, "ata ahla gever", 130, Jobs.ShiftManager, "123");
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
        Worker Cashier1 = new Worker("mikel", "10", 318, "ata ahla gever", 60, Jobs.Cashier, "123");
        Worker Cashier2 = new Worker("migul", "11", 318, "ata ahla gever", 60, Jobs.Cashier, "123");
        Worker Cashier3 = new Worker("huan", "12", 318, "ata ahla gever", 60, Jobs.Cashier, "123");
        Worker Cashier4 = new Worker("omri-escopar", "13", 318, "ata ahla gever", 60, Jobs.Cashier, "123");
        //puts them in the worker list
        Workers.put("10", Cashier1);
        Workers.put("11", Cashier2);
        Workers.put("12", Cashier3);
        Workers.put("13", Cashier4);

        //create StoreKeeper
        Worker StoreKeeper1 = new Worker("oded", "14", 318, "ata ahla gever", 60, Jobs.StoreKeeper, "123");
        Worker StoreKeeper2 = new Worker("gal", "15", 318, "ata ahla gever", 60, Jobs.StoreKeeper, "123");
        Worker StoreKeeper3 = new Worker("papi", "16", 318, "ata ahla gever", 60, Jobs.StoreKeeper, "123");
        Worker StoreKeeper4 = new Worker("hatul", "17", 318, "ata ahla gever", 60, Jobs.StoreKeeper, "123");
        //puts them in the worker list
        Workers.put("14", StoreKeeper1);
        Workers.put("15", StoreKeeper2);
        Workers.put("16", StoreKeeper3);
        Workers.put("17", StoreKeeper4);


        //create GeneralEmployee
        Worker GeneralEmp1 = new Worker("odedi", "18", 318, "ata ahla gever", 30, Jobs.GeneralEmp, "123");
        Worker GeneralEmp2 = new Worker("shimun", "19", 318, "ata ahla gever", 30, Jobs.GeneralEmp, "123");
        Worker GeneralEmp3 = new Worker("sara", "20", 318, "ata ahla gever", 30, Jobs.GeneralEmp, "123");
        Worker GeneralEmp4 = new Worker("gebi", "21", 318, "ata ahla gever", 30, Jobs.GeneralEmp, "123");
        //puts them in the worker list
        Workers.put("18", GeneralEmp1);
        Workers.put("19", GeneralEmp2);
        Workers.put("20", GeneralEmp3);
        Workers.put("21", GeneralEmp4);

        //create guards
        Worker Guard1 = new Worker("yuri", "22", 318, "ata ahla gever", 50, Jobs.Guard, "123");
        Worker Guard2 = new Worker("dor", "23", 318, "ata ahla gever", 50, Jobs.Guard, "123");
        Worker Guard3 = new Worker("shalev", "24", 318, "ata ahla gever", 50, Jobs.Guard, "123");
        Worker Guard4 = new Worker("harel", "25", 318, "ata ahla gever", 50, Jobs.Guard, "123");
        //puts them in the worker list
        Workers.put("22", Guard1);
        Workers.put("23", Guard2);
        Workers.put("24", Guard3);
        Workers.put("25", Guard4);

        //create cleaner
        Worker Cleaner1 = new Worker("rohama", "26", 318, "ata ahla gever", 10, Jobs.Cleaner, "123");
        Worker Cleaner2 = new Worker("avraham", "27", 318, "ata ahla gever", 10, Jobs.Cleaner, "123");
        Worker Cleaner3 = new Worker("shoshana", "28", 318, "ata ahla gever", 10, Jobs.Cleaner, "123");
        Worker Cleaner4 = new Worker("alo", "29", 318, "ata ahla gever", 10, Jobs.Cleaner, "123");
        //puts them in the worker list
        Workers.put("26", Cleaner1);
        Workers.put("27", Cleaner2);
        Workers.put("28", Cleaner3);
        Workers.put("29", Cleaner4);

        //create usher
        Worker Usher1 = new Worker("ahrak", "30", 318, "ata ahla gever", 15, Jobs.Usher, "123");
        Worker Usher2 = new Worker("midbar", "31", 318, "ata ahla gever", 15, Jobs.Usher, "123");
        Worker Usher3 = new Worker("sahara", "32", 318, "ata ahla gever", 15, Jobs.Usher, "123");
        Worker Usher4 = new Worker("alosantos", "33", 318, "ata ahla gever", 15, Jobs.Usher, "123");
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
        Super2.AddWorker(ShiftManager1);
        Super2.AddWorker(ShiftManager2);
        Super2.AddWorker(ShiftManager3);
        Super2.AddWorker(ShiftManager4);

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
    }
    //write the workers to the database
    public static void WriteWorkers() {
        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            //shift managers
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (1,'manu',1,'4.2.2000','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (2,'david',1,'4.2.2000','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (3,'muhamad',1,'4.2.2000','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (4,'omri',1,'4.2.2000','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (5,'oded',1,'4.2.2000','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (6,'oded2',1,'4.2.2000','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (7,'peleg',1,'4.2.2000','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (8,'mitzi',1,'4.2.2000','asd',123,0,130,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (9,'hatul',1,'4.2.2000','asd',123,0,130,0)");

            //cashiers
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (10,'mikel',1,'4.2.2000','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (11,'migul',1,'4.2.2000','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (12,'migul',1,'4.2.2000','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (13,'omri-escopar',1,'4.2.2000','asd',123,0,60,0)");

            //storekeepera
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (14,'oded',1,'4.2.2000','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (15,'gal',1,'4.2.2000','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (16,'papi',1,'4.2.2000','asd',123,0,60,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (17,'hatul-escopar',1,'4.2.2000','asd',123,0,60,0)");
            //General Employees
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (18,'odedi',1,'4.2.2000','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (19,'shimun',1,'4.2.2000','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (20,'sara',1,'4.2.2000','asd',123,0,30,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (21,'gebi',1,'4.2.2000','asd',123,0,30,0)");
            //guars

            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (22,'yuri',1,'4.2.2000','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (23,'dor',1,'4.2.2000','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (24,'shalev',1,'4.2.2000','asd',123,0,50,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (25,'harel',1,'4.2.2000','asd',123,0,50,0)");

            //cleaners
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (26,'rohama',1,'4.2.2000','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (27,'avraham',1,'4.2.2000','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (28,'shoshana',1,'4.2.2000','asd',123,0,10,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (29,'alo',1,'4.2.2000','asd',123,0,10,0)");

            //usher
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (30,'ahrak',1,'4.2.2000','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (31,'midbar',1,'4.2.2000','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (32,'sahara',1,'4.2.2000','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (33,'alosantos',1,'4.2.2000','asd',123,0,15,0)");



        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }
    public static void WriteSupers(){
        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (30,'ahrak',1,'4.2.2000','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (31,'midbar',1,'4.2.2000','asd',123,0,15,0)");
            stmt.executeUpdate(
                    "INSERT INTO Worker(id, name, bank, startdate, contract, password, bonus, wage, shiftworked)" +
                            " VALUES (32,'sahara',1,'4.2.2000','asd',123,0,15,0)");
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }
}

