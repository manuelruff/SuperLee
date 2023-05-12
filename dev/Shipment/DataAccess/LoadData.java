package Shipment.DataAccess;

import HR.Service.ShipmentService;
import Shipment.Bussiness.*;

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
//        loadTrucks();
//        truckMapper.writeAllTrucks();
//        LoadOrders();
//        orderMapper.writeAllOrders();

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
    public static void LoadOrders()
    {
        ShipmentService shipmentService = ShipmentService.getInstance();
        Order order;
        order = new Order("branch1", Zone.valueOf(shipmentService.askForSite("zolretzhah").get(4)),"Osem");
        order.addItemToOrder(new Item( "ketchup", 10, Training.valueOf("Regular")));
        order.addItemToOrder(new Item( "spaghetti", 20, Training.valueOf("Regular")));
        order.addItemToOrder(new Item( "mayo", 10, Training.valueOf("Regular")));
        orderMapper.addOrderToMap(order);

        order = new Order("branch1", Zone.valueOf(shipmentService.askForSite("zolretzhah").get(4)),"Osem");
        order.addItemToOrder(new Item( "ketchup", 10, Training.valueOf("Regular")));
        order.addItemToOrder(new Item( "apropo", 20, Training.valueOf("Regular")));
        orderMapper.addOrderToMap(order);

        order = new Order("branch2", Zone.valueOf(shipmentService.askForSite("zolretzhah").get(4)),"Osem");
        order.addItemToOrder(new Item( "pene", 10, Training.valueOf("Regular")));
        order.addItemToOrder(new Item( "bamba", 20, Training.valueOf("Regular")));
        order.addItemToOrder(new Item( "bisli", 20, Training.valueOf("Regular")));
        order.addItemToOrder(new Item( "peti ber", 20, Training.valueOf("Regular")));
        orderMapper.addOrderToMap(order);

        order = new Order("branch3", Zone.valueOf(shipmentService.askForSite("yakarmeod").get(4)),"Zogloveg");
        order.addItemToOrder(new Item( "pastrama", 10, Training.valueOf("Cooling")));
        order.addItemToOrder(new Item( "hot dog", 20, Training.valueOf("Freezer")));
        order.addItemToOrder(new Item( "salami", 20, Training.valueOf("Cooling")));
        order.addItemToOrder(new Item( "hamburger", 20, Training.valueOf("Freezer")));
        orderMapper.addOrderToMap(order);

        order = new Order("branch4", Zone.valueOf(shipmentService.askForSite("yakarmeod").get(4)),"Zogloveg");
        order.addItemToOrder(new Item( "pastrama", 20, Training.valueOf("Cooling")));
        order.addItemToOrder(new Item( "hot dog", 10, Training.valueOf("Freezer")));
        order.addItemToOrder(new Item( "salami", 10, Training.valueOf("Cooling")));
        order.addItemToOrder(new Item( "hamburger", 30, Training.valueOf("Freezer")));
        orderMapper.addOrderToMap(order);

        order = new Order("branch5", Zone.valueOf(shipmentService.askForSite("yakarmeod").get(4)),"Zogloveg");
        order.addItemToOrder(new Item( "pastrama", 30, Training.valueOf("Cooling")));
        order.addItemToOrder(new Item( "hot dog", 10, Training.valueOf("Freezer")));
        order.addItemToOrder(new Item( "salami", 20, Training.valueOf("Cooling")));
        order.addItemToOrder(new Item( "hamburger", 20, Training.valueOf("Freezer")));
        orderMapper.addOrderToMap(order);
//        createOrder("Zogloveg", "branch6");
//        addItemToOrder("Zogloveg", "pastrama", 10, 1);
//        addItemToOrder("Zogloveg", "hot dog", 20, 2);
//        addItemToOrder("Zogloveg", "salami", 10, 1);
//        addItemToOrder("Zogloveg", "hamburger", 20, 2);
//        createOrder("Elit", "branch3");
//        addItemToOrder("Elit", "chocolate", 100, 0);
//        addItemToOrder("Elit", "dark chocolate", 10, 0);
//        addItemToOrder("Elit", "Egozi", 76, 0);
//        addItemToOrder("Elit", "Nutella", 100, 0);
//        createOrder("Elit", "branch4");
//        addItemToOrder("Elit", "chocolate", 100, 0);
//        addItemToOrder("Elit", "dark chocolate", 10, 0);
//        addItemToOrder("Elit", "Egozi", 76, 0);
//        addItemToOrder("Elit", "Nutella", 100, 0);
//        createOrder("Tnuva", "branch5");
//        addItemToOrder("Tnuva", "milk", 100, 1);
//        addItemToOrder("Tnuva", "white cheese", 100, 1);
//        addItemToOrder("Tnuva", "koteg", 100, 1);
//        addItemToOrder("Tnuva", "butter", 130, 1);
//        createOrder("Tnuva", "branch4");
//        addItemToOrder("Tnuva", "milk", 100, 1);
//        addItemToOrder("Tnuva", "white cheese", 100, 1);
//        addItemToOrder("Tnuva", "koteg", 90, 1);
//        addItemToOrder("Tnuva", "butter", 30, 1);
//        createOrder("Tnuva", "branch6");
//        addItemToOrder("Tnuva", "milk", 100, 1);
//        addItemToOrder("Tnuva", "white cheese", 100, 1);
//        addItemToOrder("Tnuva", "koteg", 100, 1);
//        addItemToOrder("Tnuva", "butter", 100, 1);
//        createOrder("Strauss", "branch2");
//        addItemToOrder("Strauss", "coffee", 100, 0);
//        addItemToOrder("Strauss", "doritos", 60, 0);
//        addItemToOrder("Strauss", "chips", 100, 0);
//        addItemToOrder("Strauss", "milki", 100, 1);
//        createOrder("Strauss", "branch6");
//        addItemToOrder("Strauss", "coffee", 100, 0);
//        addItemToOrder("Strauss", "doritos", 100, 0);
//        addItemToOrder("Strauss", "chips", 60, 0);
//        addItemToOrder("Strauss", "milki", 100, 1);
//        createOrder("Strauss", "branch4");
//        addItemToOrder("Strauss", "coffee", 100, 0);
//        addItemToOrder("Strauss", "doritos", 60, 0);
//        addItemToOrder("Strauss", "chips", 100, 0);
//        addItemToOrder("Strauss", "milki", 60, 1);
//        createOrder("Strauss", "branch3");
//        addItemToOrder("Strauss", "coffee", 60, 0);
//        addItemToOrder("Strauss", "doritos", 100, 0);
//        addItemToOrder("Strauss", "chips", 60, 0);
//        addItemToOrder("Strauss", "milki", 100, 1);
    }
}
