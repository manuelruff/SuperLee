import java.util.*;

public class shipmentManagement {
    private final Map<String, List<Order>> vendorMap;
    private final List<Driver> drivers;
    private final List<Truck> trucks;
    private final List<Site> sites;

    private final List<Shipment> shipments;

    private List<Shipment> availableShipments;
    public shipmentManagement() {
        vendorMap = new HashMap<>();
        drivers = new ArrayList<>();
        trucks = new ArrayList<>();
        sites = new ArrayList<>();
        shipments = new ArrayList<>();
        availableShipments = new ArrayList<>();
    }

    /****************************** Drivers related Methods ******************************/


    /**
     * This function checks if the ID of a driver is in the system.
     *
     * @param ID ID of the driver.
     * @return true if found. false otherwise.
     */
    public boolean checkID(String ID) {
        for (Driver driver : drivers) {
            if (Objects.equals(driver.getID(), ID)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This function creates a new driver and adds it to the system.
     *
     * @param name    drivers name
     * @param ID      drivers ID
     * @param license license type. (C/D)
     * @param train   training type. (regular/cooling/freezer)
     */
    public void addDriver(String name, String ID, char license, Training train) {
        Driver driver = new Driver(name, ID, license, train);
        drivers.add(driver);
    }

    /**
     * This function deletes driver from the system.
     *
     * @param ID drivers ID.
     */
    public void removeDriver(String ID) {
        for (Driver driver : drivers) {
            if (Objects.equals(driver.getID(), ID)) {
                drivers.remove(driver);
            }
        }
    }

    /**
     * This function search for a driver that can do a shipment.
     * @param train enum, his ability to driver truck.
     * @param day int represent the day of the week.
     * @return the available driver.
     */
    public Driver searchForDriver(Training train, int day){
        for (Driver driver : drivers){
            if (driver.getAbility().ordinal() <= train.ordinal())
                if (driver.addNewDay(Days.values()[day])){
                    return driver;
                }
        }
        return null;
    }

    /**
     * This Function prints every driver in the system.
     */
    public void printDrivers(){
        for (Driver driver : drivers){
            driver.printDriver();
            System.out.println(" ");
        }
    }


    /****************************** Truck related Methods ******************************/


    /**
     * This function checks if a truck number is already in the system.
     *
     * @param truckNumber string, the truck number.
     * @return true if the truck is found. false otherwise.
     */
    public boolean checkTruckNumber(String truckNumber) {
        for (Truck truck : trucks) {
            if (Objects.equals(truckNumber, truck.getTruckNumber())) {
                return true;
            }
        }
        return false;
    }

    /**
     * this function creates a new truck and adds it to they system.
     *
     * @param truckNumber the number of the truck.
     * @param totalWeight the total weight of the truck.
     * @param truckWeight the weight of an empty truck.
     * @param model       the model of the truck.
     * @param train       training type. (regular/cooling/freezer)
     */
    public void addTruck(String truckNumber, int totalWeight, int truckWeight, String model, int train) {
        Truck truck = null;
        switch (train) {
            case 1:
                truck = new RegularTruck(truckNumber, totalWeight, truckWeight, model);
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

    /**
     * This function removes a truck from the system.
     *
     * @param truckNumber string, the truck number.
     */
    public void removeTruck(String truckNumber) {
        for (Truck truck : trucks) {
            if (Objects.equals(truckNumber, truck.getTruckNumber())) {
                trucks.remove(truck);
            }
        }
    }

    /**
     * This function search for a suitable truck and returns its truck number.
     * @param train Enum, they type of the truck.
     * @param day   int, represent the day of the week.
     * @return string, the truck Number.
     */
    public String searchForTruck(Training train, int day){
        for (Truck truck : trucks){
            if (truck.addNewDay(Days.values()[day])){
                if (((truck instanceof FreezerTruck) && (train == Training.Freezer))
                        || ((truck instanceof CoolingTruck) && (train == Training.Cooling))
                            || ((truck instanceof RegularTruck) && (train == Training.Regular)))
                    return truck.getTruckNumber();
            }
        }
        return "";
    }

    /**
     * This function prints every truck in the system.
     */
    public void printTrucks(){
        for (Truck truck : trucks){
            truck.printTruck();
        }
    }


    /****************************** Sites related Methods ******************************/

    /**
     * This function checks if a site is already exist in the system.
     *
     * @param name String, name of the site.
     * @return true if found. false otherwise.
     */

    public boolean checkSite(String name) {
        for (Site site : sites) {
            if (Objects.equals(site.getName(), name))
                return true;
        }
        return false;
    }


    /**
     * This function creates a new vendor and adds it to the system.
     *
     * @param name        string, name of the vendor.
     * @param address     string, address of the vendor.
     * @param phoneNumber string, phone number of the contact person.
     * @param contactName string, name of the contact person.
     */

    public void addVendor(String name, String address, String phoneNumber, String contactName) {
        Vendor vendor;
        vendor = new Vendor(name, address, phoneNumber, contactName);
        List<Order> orderList = new ArrayList<>();
        vendorMap.put(name, orderList);
        sites.add(vendor);
    }

    /**
     * This function creates a new branch and adds it to the system.
     *
     * @param name        string, name of the branch.
     * @param address     string, address of the vendor.
     * @param phoneNumber string, phone number of the contact person.
     * @param contactName string, name of the contact person.
     * @param zone        enum Zone, zone area.
     */
    public void addBranch(String name, String address, String phoneNumber, String contactName, Zone zone) {
        Branch branch;
        branch = new Branch(name, address, phoneNumber, contactName, zone);
        sites.add(branch);
    }


    /**
     * This function removes a site from the system
     * @param name string, name of the site.
     */
    public void deleteSite(String name) {
        for (Site site : sites) {
            if (Objects.equals(site.getName(), name)) {
                sites.remove(site);
                vendorMap.remove(name);
            }
        }
    }

    /**
     * This function checks if a vendor is already exist in the system.
     * @param name String, name of the vendor.
     * @return true if found. false otherwise.
     */

    public boolean checkVendor(String name) {
        return vendorMap.containsKey(name);
    }

    /**
     * This function checks if a branch is already exist in the system.
     * @param name String, name of the branch.
     * @return true if found. false otherwise.
     */

    public boolean checkBranch(String name) {
        for (Site site : sites) {
            if (Objects.equals(site.getName(), name)) {
                if (site instanceof Branch)
                    return true;
                break;
            }
        }
        return false;
    }



    /************************************* Order Related Methods *************************************/

    /**
     * Creates a new order and adding it to the system.
     * @param source string, name of the supplier.
     * @param destination string, name of the branch to deliver.
     */
    public void createOrder(String source, String destination) {
        Zone zone = null;
        for (Site site : sites) {
            if (Objects.equals(destination, site.getName())) {
                zone = ((Branch) site).getZone();
            }
        }
        Order order = new Order(destination, zone);
        vendorMap.get(source).add(order);
    }


    /**
     * Creates a new item and adding it to the last order that was created of a specific vendor
     * @param source string, vendors name.
     * @param itemName string, the name of the item.
     * @param amount int, the amount of this item.
     * @param storageCondition int, which will be represented as enum: regular/cooling/freezer
     */
    public void addItemToOrder(String source, String itemName, int amount, int storageCondition){
        Item item = new Item(itemName,amount,Training.values()[storageCondition]);
        vendorMap.get(source).get(vendorMap.get(source).size() - 1).addItemToOrder(item);
    }


    /**
     * This function checks if a shipment is already exist in the system.
     * @param ID string, ID of the shipment.
     * @return true if found. false otherwise
     */
    public boolean checkShipmentID(String ID){
        for (Shipment ship : shipments){
            if (Objects.equals(ship.getID(), ID)){
                return true;
            }
        }
        return false;
    }



    public boolean createShipment(int dayOfWeek, String ID, String source){
        List<String> branchList = new ArrayList<>();
        if (vendorMap.get(source).isEmpty()){
            System.out.println("This vendor: " + source + " does not have any orders");
            return false;
        }
        // saving the values of the first order.
        Order firstOrder = vendorMap.get(source).get(0);
        Zone firstZone = firstOrder.getZone();
        branchList.add(firstOrder.getDestination());
        return true;
    }
}
