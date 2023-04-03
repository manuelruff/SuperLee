import java.util.*;

public class shipmentManagement {
    private Map<String, List<Order>> vendorMap;
    private List<Driver> drivers;
    private List<Truck> trucks;
    private List<Site> sites;

    public shipmentManagement(){
        vendorMap = new HashMap<>();
        drivers = new ArrayList<>();
        trucks = new ArrayList<>();
        sites = new ArrayList<>();
    }

    /**
     * This function checks if the ID of a driver is in the system.
     * @param ID ID of the driver.
     * @return true if found. false otherwise.
     */
    public boolean checkID(String ID){
        for (Driver driver : drivers) {
            if (Objects.equals(driver.getID(), ID)) {
                return true;
            }
        }
        return false;
    }
    /**
     * This function creates a new driver and adds it to the system.
     * @param name drivers name
     * @param ID drivers ID
     * @param license license type. (C/D)
     * @param train training type. (regular/cooling/freezer)
     */
    public void addDriver(String name, String ID, char license, Training train){
        Driver driver = new Driver(name, ID, license, train);
        drivers.add(driver);
    }


    /**
     * This function checks if a truck number is already in the system.
     * @param truckNumber string, the truck number.
     * @return true if the truck is found. false otherwise.
     */
    public boolean checkTruckNumber(String truckNumber){
        for (Truck truck : trucks) {
            if (Objects.equals(truckNumber, truck.getTruckNumber())) {
                return true;
            }
        }
        return false;
    }

    /**
     * this function creates a new truck and adds it to they system.
     * @param truckNumber the number of the truck.
     * @param totalWeight the total weight of the truck.
     * @param truckWeight the weight of an empty truck.
     * @param model the model of the truck.
     * @param train training type. (regular/cooling/freezer)
     */
    public void addTruck(String truckNumber, int totalWeight, int truckWeight, String model, int train){
        Truck truck = null;
        switch (train){
            case 1:
                truck = new RegularTruck(truckNumber,totalWeight,truckWeight,model);
                break;

            case 2:
                truck = new CoolingTruck(truckNumber, totalWeight, truckWeight, model);
                break;

            case 3:
                truck = new FreezerTruck(truckNumber, totalWeight, truckWeight, model);
                break;
        }
        trucks.add(truck);
    }

    public void addVendor(String name, String address, String phoneNumber, String contactName){
        Vendor vendor;
        if (!(vendorMap.containsKey(name))){
            vendor = new Vendor(name, address, phoneNumber,contactName);
            List<Order> orderList = new ArrayList<>();
            vendorMap.put
        }

    }



}