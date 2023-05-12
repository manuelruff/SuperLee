package Shipment.DataAccess;

import Shipment.Bussiness.CoolingTruck;
import Shipment.Bussiness.FreezerTruck;
import Shipment.Bussiness.RegularTruck;
import Shipment.Bussiness.Truck;
import resource.Connect;

import java.util.Map;

public class LoadData {

    private LoadData instance = new LoadData();

    private static TruckMapper truckMapper;
    private static OrderMapper orderMapper;


    public LoadData()
    {
        truckMapper = TruckMapper.getInstance();
        orderMapper = OrderMapper.getInstance();
    }
    public static void main(String[] args) {
        truckMapper = TruckMapper.getInstance();
        orderMapper = OrderMapper.getInstance();
        loadTrucks();
    }


    public static void loadTrucks(){
        addTruck("0000", 6000, 2000, "mercedes", 0);
        addTruck("0001", 13000, 2000, "mercedes", 1);
        addTruck("0002", 12000, 2000, "mercedes", 2);
        addTruck("0003", 10000, 2000, "mercedes", 0);
        addTruck("0004", 8000, 1000, "mercedes", 1);
        addTruck("0005", 9000, 1000, "mercedes", 2);
        addTruck("0006", 16000, 2000, "mercedes", 0);
        addTruck("0007", 7000, 1000, "mercedes", 1);
        addTruck("0008", 8000, 1000, "mercedes", 2);
        addTruck("0009", 11000, 2000, "mercedes", 0);
        addTruck("0010", 12000, 2000, "mercedes", 1);
    }
    public static void addTruck(String truckNumber, int totalWeight, int truckWeight, String model, int train) {
        Truck truck = switch (train) {
            case 0 -> new RegularTruck(truckNumber, totalWeight, truckWeight, model);
            case 1 -> new CoolingTruck(truckNumber, totalWeight, truckWeight, model);
            case 2 -> new FreezerTruck(truckNumber, totalWeight, truckWeight, model);
            default -> null;
        };
        truckMapper.addTruckToMap(truck);
    }
}
