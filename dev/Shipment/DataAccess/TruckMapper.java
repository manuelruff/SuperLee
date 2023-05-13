package Shipment.DataAccess;
import Shipment.Bussiness.*;
import resource.Connect;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TruckMapper {
    private static TruckMapper instance;
    private Map<String, Truck> truckMap;
    private Connection connect;

    private TruckMapper() {
        truckMap = new HashMap<>();
        connect = Connect.getConnection();
    }

    public static TruckMapper getInstance() {
        if (instance == null) {
            instance = new TruckMapper();
        }
        return instance;
    }

    public void addTruckToMap(Truck truck) {
        truckMap.putIfAbsent(truck.getTruckNumber(), truck);
    }

    public Map<String, Truck> getTruckMap() {
        return truckMap;
    }

    public Truck getTruck(String truckNumber) {
        if (!truckMap.containsKey(truckNumber))
            readTruck(truckNumber);
        return truckMap.get(truckNumber);
    }

    private void readTruck(String truckNumber) {
        String storage, model;
        int totalWeight, truckWeight;
        try {
            java.sql.Statement stat = connect.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("select * from Truck where truckNumber == '" + truckNumber + "'");
            if (rs.next()) {
                totalWeight = rs.getInt("totalWeight");
                truckWeight = rs.getInt("truckWeight");
                model = rs.getString("model");
                storage = rs.getString("storage");
                Truck truck = null;
                switch (storage) {
                    case "Freezer" -> truck = new FreezerTruck(truckNumber, totalWeight, truckWeight, model);
                    case "Cooling" -> truck = new CoolingTruck(truckNumber, totalWeight, truckWeight, model);
                    case "Regular" -> truck = new RegularTruck(truckNumber, totalWeight, truckWeight, model);
                }
                truckMap.put(truckNumber, truck);
                readWorkingDays(truckNumber);

            }
        } catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }

    }

    private void readWorkingDays(String truckNumber) {
        String day;
        try {
            java.sql.Statement stmt = connect.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("select * from TruckWorkingDays WHERE truckNumber=='" + truckNumber + "'");
            while (rs.next()) {
                day = rs.getString("Day");
                truckMap.get(truckNumber).addNewDay(Days.valueOf(day));
            }
        } catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }

    public void writeAllTrucks() {
        for (Truck truck : truckMap.values()) {
            String model, storage, truckNumber;
            int totalWeight, truckWeight;
            try {
                java.sql.Statement stat = connect.createStatement();
                truckNumber = truck.getTruckNumber();
                model = truck.getModel();
                storage = truck.getStorageType().toString();
                totalWeight = truck.getTotalWeight();
                truckWeight = truck.getTruckWeight();
                stat.executeUpdate("INSERT OR IGNORE INTO Truck(truckNumber, totalWeight, truckWeight,model,Storage) " +
                        "VALUES ('" + truckNumber + "', " + totalWeight + " ," + truckWeight + ",'" + model + "', '" + storage + "')");

            } catch (SQLException e) {
                System.out.println("i have a problem sorry");
            }
        }
    }

    private void WriteWorkingDays(Truck truck) {
        try {
            java.sql.Statement stat = connect.createStatement();
            for (Days day : truck.getInUse()) {
                stat.executeUpdate("INSERT OR IGNORE INTO TruckWorkingDays (TruckNumber, Day) VALUES (" + truck.getTruckNumber() + ", '" + day + "')");
            }
        } catch (SQLException e) {
            System.out.println("i have a problem in writing the Truck days sorry");
        }
    }

    public void readAllTrucks() {
        String storage, model, truckNumber;
        int totalWeight, truckWeight;
        try {
            java.sql.Statement stmt = connect.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("select * from Truck");
            while (rs.next()) {
                truckNumber = rs.getString("truckNumber");
                totalWeight = rs.getInt("totalWeight");
                truckWeight = rs.getInt("truckWeight");
                model = rs.getString("model");
                storage = rs.getString("storage");
                if (truckMap.containsKey(truckNumber))
                    continue;
                Truck truck = null;
                switch (storage) {
                    case "Freezer" -> truck = new FreezerTruck(truckNumber, totalWeight, truckWeight, model);
                    case "Cooling" -> truck = new CoolingTruck(truckNumber, totalWeight, truckWeight, model);
                    case "Regular" -> truck = new RegularTruck(truckNumber, totalWeight, truckWeight, model);
                }
                truckMap.put(truckNumber, truck);
                readWorkingDays(truckNumber);
            }

        } catch (SQLException e) {
            System.out.println("i have a problem in getting the Trucks");
        }
    }

    public void deleteTruck(String truckNumber) {
        try {
            java.sql.Statement stmt = connect.createStatement();
            stmt.executeQuery("DELETE FROM Truck WHERE truckNumber='" + truckNumber + "'");
            stmt.executeQuery("DELETE FROM TruckWorkingDays where TruckNumber='" + truckNumber + "'");
        } catch (SQLException e) {
            System.out.println("i have a problem in deleting a truck");
        }
    }
}
