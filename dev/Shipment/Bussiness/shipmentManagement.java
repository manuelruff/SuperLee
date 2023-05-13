package Shipment.Bussiness;

import HR.Bussiness.ManagerController;
import HR.Service.ShipmentService;
import Shipment.DataAccess.DataController;
import Shipment.DataAccess.ShipmentMapper;

import java.time.LocalDate;
import java.util.*;


// singleton
public class shipmentManagement {

    private static DataController dataController;
    private static Truck staticsTruck;
    private final Map<String, List<Order>> vendorMap;
    private final List<Driver> drivers;
    private final Map<String,Truck> trucks;
    private final List<Site> sites;
    private String shipmentManagerPassword;
    private ShipmentService shipmentService;
    private final Map<String, Shipment> shipments;

    private List<Shipment> availableShipments;

    private static shipmentManagement instance = null;

    private shipmentManagement() {
        dataController = DataController.getInstance();
        shipmentService = ShipmentService.getInstance();
        vendorMap = new HashMap<>();
        drivers = new ArrayList<>();
        trucks = dataController.getTrucksMap();
        sites = new ArrayList<>();
        shipmentManagerPassword = dataController.getShipmentManagerPassword();
        shipments = dataController.getShipmentsMap();
        availableShipments = new ArrayList<>();
        availableShipments = dataController.getAvailableShipmentsIntoList();
        availableShipments.sort(Comparator.comparing(Shipment::getDate));


    }
    public static shipmentManagement getInstance() {
        if (instance == null) {
            instance = new shipmentManagement();
        }
        return instance;
    }

    /**
     * @param password input password
     * @return true if password is correct
     */
    public boolean checkPassword(String password){
        return password.equals(shipmentManagerPassword);
    }
    /**
     * change the password in the database and the instance
     * @param password new password
     */
    public void setManagerPassword(String password) {
        shipmentManagerPassword = password;
        dataController.setShipmentManagerPassword(password);
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
     * This function update the licence of a specific driver
     * @param ID String, driver ID
     * @param licence char, licence type.(C/D)
     */
    public void updateDriverLicence(String ID, char licence){
        for (Driver driver : drivers){
            if (ID.equals(driver.getID())){
                if (driver.getLicense() < licence) {
                    driver.setLicense(licence);
                    System.out.println("This driver licence has been upgraded to " + licence);
                }
                else {
                    System.out.println("This driver licence is " + driver.getLicense() + " which is already higher then " + licence);
                }
                return;
            }
        }
    }

    /**
     * This function update the ability of a specific driver.
     * @param ID String, driver ID
     * @param training int, will indicate the Training Enum.
     */
    public void updateDriverTraining(String ID, int training) {
        for (Driver driver : drivers) {
            if (ID.equals(driver.getID())) {
                if (driver.getAbility().ordinal() < training){
                    driver.setAbility(Training.values()[training]);
                    System.out.println("This driver training has been upgraded to " + driver.getAbility().toString());
                }
                else{System.out.println("This driver training is " + driver.getLicense() + " which is already higher then " + Training.values()[training]);}
                return;
            }
        }
    }
    /**
     * This function creates a new driver and adds it to the system.
     */
    public Driver addDriver(List<String> list){
        if (list == null)
            return null;
        return new Driver(list.get(0),list.get(1), list.get(2).charAt(0), Training.valueOf(list.get(3)));

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
                return;
            }
        }
    }

    /**
     * This function search for a specific driver and return it.
     * @param ID String, ID of the driver
     * @return found driver/ null
     */
    public Driver getDriver(String ID)
    {
        for (Driver driver : drivers)
        {
            if(driver.getID().equals(ID))
                return driver;
        }
        return null;
    }

    /**
     * This function search for a driver that can do a shipment.
     *
     * @param train enum, his ability to driver truck.
     * @param day   int represent the day of the week.
     * @return the available driver.
     */
    public Driver searchForDriver(Training train, int day, List<String> driverNameList) {
        for (Driver driver : drivers) {
            if (driverNameList.contains(driver.getName())){
                continue;
            }
            if (driver.getAbility().ordinal() <= train.ordinal())
                if (driver.addNewDay(Days.values()[day])) {
                    return driver;
                }
        }
        return null;
    }

    /**
     * This Function prints every driver in the system.
     */
    public void printDrivers() {
        for (Driver driver : drivers) {
            driver.Printme();
            System.out.println(" ");
        }
    }

    /**
     * This function search for a new driver in case of truck exchange, if the old driver is suitable for the new
     * truck, there is no changes.
     * @param oldDriver Driver, old driver of the shipment.
     * @param truck Truck, the new truck that was assigned to the shipment.
     * @param day Days enum, the day of the week.
     * @return Driver. (old Driver\new Driver).
     */
    private Driver changeDriver(Driver oldDriver, Truck truck, Days day)
    {
        if(oldDriver.getAbility().ordinal() >= truck.getStorageType().ordinal())
        {
            if(oldDriver.getLicense() == 'D')
                return oldDriver;
            else if (oldDriver.getLicense() == 'C' && truck.getTotalWeight() <= 12000) {
                return oldDriver;
            }
        }
        else
        {
            for (Driver driver1 : drivers)
            {
                if(oldDriver.checkDay(day)) {
                    if (driver1.getAbility().ordinal() >= truck.getStorageType().ordinal()) {
                        if (driver1.getLicense() == 'D') {
                            driver1.addNewDay(day);
                            oldDriver.removeDay(day);
                            return driver1;
                        } else if (oldDriver.getLicense() == 'C' && truck.getTotalWeight() <= 12000) {
                            driver1.addNewDay(day);
                            oldDriver.removeDay(day);
                            return driver1;
                        }
                    }
                }
            }
        }
        return null;
    }



    /****************************** Truck related Methods ******************************/


    /**
     * This function checks if a truck number is already in the system.
     *
     * @param truckNumber string, the truck number.
     * @return true if the truck is found. false otherwise.
     */
    public boolean checkTruckNumber(String truckNumber) {
        return dataController.getTruck(truckNumber) != null;
    }

    /**
     * This function looks for a specific truck and return it.
     * @param truckNumber String, ID of the truck.
     * @return found truck/null
     */
    public Truck getTruck(String truckNumber)
    {
        return dataController.getTruck(truckNumber);
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
            case 0:
                truck = new RegularTruck(truckNumber, totalWeight, truckWeight, model);
                break;

            case 1:
                truck = new CoolingTruck(truckNumber, totalWeight, truckWeight, model);
                break;

            case 2:
                truck = new FreezerTruck(truckNumber, totalWeight, truckWeight, model);
                break;
        }
        trucks.put(truckNumber,truck);
    }

    /**
     * This function removes a truck from the system.
     *
     * @param truckNumber string, the truck number.
     */
    public void removeTruck(String truckNumber) {
        dataController.deleteTruck(truckNumber);
    }

    /**
     * This function search for a suitable truck and returns its truck number.
     *
     * @param train Enum, they type of the truck.
     * @param day   int, represent the day of the week.
     * @return string, the truck Number.
     */
    public String searchForTruck(Training train, int day) {
        for (Truck truck : trucks.values()) {
                if (truck.addNewDay(Days.values()[day])) {
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
    public void printTrucks() {
        dataController.loadAllTrucks();
        System.out.println("******* Trucks details *******");
        System.out.println("Number of trucks: " + trucks.size() + "\n");
        for (Truck truck : trucks.values()) {
            truck.printTruck();
        }
    }


    /**
     * This function searching for a new truck for the shipment.
     */
    public void changeTruck() {
        Shipment shipment = shipments.get(0);
        Truck currentTruck = getTruck(shipment.getTruckNumber());
        Driver currentDriver = shipment.getDriver();
        for(Truck truck : trucks.values())
        {
            if(currentTruck.getTotalWeight() < truck.getTotalWeight())
            {
                if(truck.checkDay(shipment.getDayOfTheWeek())) //check about getDay function
                {
                    if (((truck instanceof FreezerTruck) && (currentTruck instanceof FreezerTruck))
                            || ((truck instanceof CoolingTruck) && (currentTruck instanceof CoolingTruck))
                            || ((truck instanceof RegularTruck) && (currentTruck instanceof RegularTruck)))
                    {
                        //check Driver to see if he can not be change
                        shipment.setDriver(changeDriver(shipment.getDriver(), truck, shipment.getDayOfTheWeek()));
                        if(shipment.getDriver() == null)
                            shipment.setDriver(currentDriver);
                        else
                        {
                            shipment.setTruckNumber(truck.getTruckNumber());
                            truck.addNewDay(shipment.getDayOfTheWeek());
                            currentTruck.removeDay(shipment.getDayOfTheWeek());
                            System.out.println("Truck Changed");
                            return;
                        }
                    }
                }

            }
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
     * This site updates the data of the site.
     * @param oldSite String, name of the site to change.
     * @param somethingToChane String, somthing to change.
     * @param number int, represent what to change.
     */
    public void updateSite(String oldSite ,String somethingToChane, int number){
        Site site = getSite(oldSite);
        switch (number){
            case 1:
                site.setName(somethingToChane);
                break;
            case 2:
                site.setAddress(somethingToChane);
                break;
            case 3:
                site.setContactName(somethingToChane);
                break;
            case 4:
                site.setPhoneNumber(somethingToChane);
                break;
        }
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
    public void addBranch(String name, String address, String phoneNumber, String contactName, int zone) {
        Branch branch;
        branch = new Branch(name, address, phoneNumber, contactName, Zone.values()[zone]);
        sites.add(branch);
    }


    /**
     * This function removes a site from the system
     *
     * @param name string, name of the site.
     */
    public void deleteSite(String name) {
        for (Site site : sites) {
            if (Objects.equals(site.getName(), name)) {
                sites.remove(site);
                vendorMap.remove(name);
                return;
            }
        }
    }

    /**
     * This function checks if a vendor is already exist in the system.
     *
     * @param name String, name of the vendor.
     * @return true if found. false otherwise.
     */

    public boolean checkVendor(String name) {
        if (vendorMap.containsKey(name))
            return true;
        else{
            return dataController.getVendor(name) != null;
        }
    }

    /**
     * This function checks if a branch is already exist in the system.
     *
     * @param name String, name of the branch.
     * @return true if found. false otherwise.
     */

    //todo fix it
    public boolean checkBranch(String name) {
        if (shipmentService.askForSite(name) == null){
            return false;
        }
        for (Site site : sites) {
            if (Objects.equals(site.getName(), name)) {
                if (site instanceof Branch)
                    return true;
                break;
            }
        }
        return false;
    }

    /**
     * This function search for a site in the system and returns it
     *
     * @param name string, name of the site.
     * @return the site if found.
     */
    public Site getSite(String name) {
        for (Site site : sites) {
            if (Objects.equals(site.getName(), name))
                return site;
        }
        return null;
    }

    public void printSites(){
        System.out.println("******* SITE DETAILS *******");
        System.out.println("Number of Sites: " + sites.size() + "\n");
        for (Site site : sites){
            site.printSite();
        }
    }

    /**
     * This function removes the last site from the shipment, and creates an order of the item that were deleted.
     */

    public boolean removeLastSiteFromShipment() {
        Shipment shipment = availableShipments.get(0);
        String source = shipment.getSource().getName();
        Order order;
        int size = shipment.getDestinations().size();
        if (size == 1) {
            return false;
        }
        Site siteToRemove = shipment.getDestinations().get(size - 1);
        for (ItemsDoc doc : shipment.getDocs()) {
            String docSiteName = doc.getSiteName();
            if (Objects.equals(docSiteName, siteToRemove.getName())){
                createOrder(source, docSiteName);
                for (Item item : doc.getItemList()) {
                    order = vendorMap.get(source).get(vendorMap.get(source).size() - 1);
                    order.addItemToOrder(item);
                }
                shipment.getDocs().remove(doc);
                shipment.removeSite(siteToRemove);
                shipment.setShipmentStatus(Status.SiteChange);
                System.out.println("The site: " + siteToRemove.getName() + " was removed from the shipment");
                return true;
            }
        }
        return false;
    }


    /************************************* Order Related Methods *************************************/

    /**
     * Creates a new order and adding it to the system.
     *
     * @param source      string, name of the supplier.
     * @param destination string, name of the branch to deliver.
     */
    public void createOrder(String source, String destination) {
        Zone zone = null;
        for (Site site : sites) {
            if (Objects.equals(destination, site.getName())) {
                zone = ((Branch) site).getZone();
            }
        }
        Order order = new Order(destination, zone, source);
        vendorMap.get(source).add(order);
    }

    public void printLastOrder(String source){
        vendorMap.get(source).get(vendorMap.get(source).size() - 1).printOrder();
    }
    public void printOrders() {
        for (Map.Entry<String, List<Order>> entry : vendorMap.entrySet()){
            System.out.println("********** " + entry.getKey() + " **********");
            if (entry.getValue().isEmpty()) {
                System.out.println("No orders");
            }
            System.out.println();
            for (Order order : entry.getValue()){
                order.printOrder();
            }
        }
    }



    /**
     * Creates a new item and adding it to the last order that was created of a specific vendor
     *
     * @param source           string, vendors name.
     * @param itemName         string, the name of the item.
     * @param amount           int, the amount of this item.
     * @param storageCondition int, which will be represented as enum: regular/cooling/freezer
     */
    public void addItemToOrder(String source, String itemName, int amount, int storageCondition) {
        Item item = new Item(itemName, amount, Training.values()[storageCondition]);
        vendorMap.get(source).get(vendorMap.get(source).size() - 1).addItemToOrder(item);
    }




    /************************************* Shipment related methods *************************************/


    /**
     * This function prints every shipment in the system.
     */
    public void printShipments(){
        dataController.loadAllShipments();
        if (shipments.isEmpty()) {
            System.out.println("There isn't any shipments!");
            return;
        }
        System.out.println("******************** SHIPMENTS ********************");
        for (Shipment shipment : shipments.values()){
            shipment.printShipment();
            System.out.println("\n");
        }
    }

    /**
     * This function prints every shipment that is ready to be executed.
     */
    public void printAvailableShipments(){
        if (availableShipments.isEmpty()) {
            System.out.println("There isn't any shipments!");
            return;
        }
        System.out.println("******************** SHIPMENTS ********************");
        for ( Shipment shipment : availableShipments){
            shipment.printShipment();
        }
    }



    /**
     * This function checks if a shipment is already exist in the system.
     *
     * @param ID string, ID of the shipment.
     * @return true if found. false otherwise
     */
    public boolean checkShipmentID(String ID) {
        return dataController.getShipment(ID) != null;
    }


    /**
     * This function get a shipment and add it to the shipment array sorted by date.
     * @param shipment Shipment, the shipment to add.
     */
    private void addShipmentSorted(Shipment shipment){
        if (availableShipments.isEmpty()){
            availableShipments.add(shipment);
            return;
        }
        for (int i=0; i < availableShipments.size(); i++){
            if (shipment.getDayOfTheWeek().ordinal() <= availableShipments.get(i).getDayOfTheWeek().ordinal()){
                availableShipments.add(i, shipment);
                return;
            }
        }
        availableShipments.add(shipment);
    }

    /**
     * This function prints all the shipment item docs of the system.
     */
    public void printAllDocs(){
        dataController.loadAllShipments();
        for(Shipment shipment : shipments.values()){
            for(ItemsDoc itemsDoc : shipment.getDocs()){
                itemsDoc.printItemsDoc();
            }
        }
    }
    /**
     * This function creates a new shipment, first the function checks if the vendor has orders, if an order was found,
     * the function will go over the orders and combine those with same delivery zones and creating a new shipments.
     * the function finds a matching driver truck pair, the type of the truck is decided by the type of the first
     * item that was checked.
     *
     * @param dayOfWeek int representing the date.
     * @param ID        string, the ID of the shipment.
     * @param source    string, Vendor.
     * @return True/false if the shipment was created.
     */
    public boolean createShipment(int dayOfWeek, LocalDate date , String ID, String source) {
        ItemsDoc itemsDoc;
        Shipment shipment;
        List<String> destinations = new ArrayList<>();
        Driver driverForShipment = null;
        String truckNumberForShipment = "";
        if (vendorMap.get(source).isEmpty()) {
            System.out.println("This vendor: " + source + " does not have any orders");
            return false;
        }
        // initialize the lists
        List<Site> branchList = new ArrayList<>();
        List<ItemsDoc> itemsDocList = new ArrayList<>();

        // saving the values of the first order.
        Order firstOrder = vendorMap.get(source).get(0);
        Zone firstZone = firstOrder.getZone();
        branchList.add(getSite(firstOrder.getDestination()));
        Training trainToSearchBy = firstOrder.firstItemType();
        Site vendor = getSite(source);

        //finding suitable truck
        truckNumberForShipment = searchForTruck(trainToSearchBy, dayOfWeek);
        Truck truck = getTruck(truckNumberForShipment);
        char licence =  truck.getLicenceType();
        // adding the first order to the shipment
        itemsDoc = new ItemsDoc(firstOrder.getDestination());
        itemsDoc.addListOfItems(firstOrder.getItemsForShipping(trainToSearchBy));
        itemsDocList.add(itemsDoc);
        destinations.add(itemsDoc.getSiteName());

        // in case there is only one order from the specific vendor
        if (vendorMap.get(source).size() == 1) {
            if (firstOrder.checkIfEmpty())
                vendorMap.get(source).remove(firstOrder);
        }
        else {
            boolean skip = true;
            boolean found;
            // in case there is more than 1 order.
            for (Order order : vendorMap.get(source)) {
                if (skip) {   // skipping the first site
                    skip = false;
                    continue;
                }
                found = false;
                // if there is another site with same zone.
                if (order.getZone() == firstZone) {
                    // checking if an item doc was already created for that site.
                    for (ItemsDoc itemD : itemsDocList) {
                        if (Objects.equals(order.getDestination(), itemD.getSiteName())) {
                            itemD.addListOfItems(order.getItemsForShipping(trainToSearchBy));
                            // checking if the order is empty, to delete.
                            found = true;
                            break;
                        }
                    }
                    // checking if the for above was executed to the fullest.
                    if (found)
                        continue;
                    // in case of a new site.
                    branchList.add(getSite(order.getDestination()));
                    itemsDoc = new ItemsDoc(order.getDestination());
                    itemsDoc.addListOfItems(order.getItemsForShipping(trainToSearchBy));
                    itemsDocList.add(itemsDoc);
                    destinations.add(itemsDoc.getSiteName());

                }
            }
        }
        if (shipmentService.checkWeekly(destinations, date)) {
            driverForShipment = addDriver(shipmentService.askForDriver(licence, truck.getStorageType().ordinal(), dayOfWeek, destinations));
            if (driverForShipment == null) {
                System.out.println("There isn't any available driver that can work at that time");
                truck.removeDay(Days.values()[dayOfWeek]);
                for (ItemsDoc doc : itemsDocList)
                    turnItemDocIntoOrder(doc, source);
                return false;
            }
        }

        vendorMap.get(source).removeIf(Order::checkIfEmpty);
        if (driverForShipment == null){
            shipment = new Shipment(ID, truckNumberForShipment, Days.values()[dayOfWeek - 1], vendor, branchList, itemsDocList, date);
        }
        else{
            shipment = new Shipment(ID, truckNumberForShipment, driverForShipment, Days.values()[dayOfWeek - 1], vendor, branchList, itemsDocList, date);
        }
        addShipmentSorted(shipment);

        return true;
    }

    private void turnItemDocIntoOrder(ItemsDoc itemsDoc, String source){
        Branch branch = ((Branch)getSite(itemsDoc.getSiteName()));
        Order order = new Order(itemsDoc.getSiteName(), branch.getZone(), source);
        for (Item item : itemsDoc.getItemList()){
            order.addItemToOrder(item);
        }
        vendorMap.get(source).add(order);
    }



































//    /**
//     * This function executes the shipment with the closest date.
//     */
//    public void executeShipment() {
//        if (availableShipments.isEmpty()) {
//            System.out.println("There is no available shipment!");
//            return;
//        }
//        //asking for shipment from DAO.
//        Shipment shipment = availableShipments.get(0);
//
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//        LocalTime time = null;
//        while (time == null) {
//            System.out.println("Enter the time (in HH:MM format): ");
//            String timeStr = scanner.nextLine();
//
//            try {
//                time = LocalTime.parse(timeStr, formatter);
//            } catch (DateTimeParseException e) {
//                System.out.println("Invalid time format. Please enter time in HH:MM format.");
//            }
//        }
//        shipment.setDepartureTime(time);
//        System.out.println("you have arrived at your destination: ");
//        shipment.getSource().printSite();
//        Truck currTruck = searchTruckByID(shipment.getTruckNumber());
//        int weight;
//        while (true){
//            try{
//                System.out.println("Please enter the weight of the truck with the items (in KG): ");
//                weight = Integer.parseInt(scanner.nextLine());
//                break;
//            }
//            catch (NumberFormatException e)
//            {
//                System.out.println("Please enter an Integer");
//            }
//        }
//        int firstWeight = weight;
//        while (true) {
//            if (shipment.getShipmentStatus() != Status.NoChanges) {
//                try{
//                    System.out.println("Please enter the new Weight (in KG):");
//                    weight = Integer.parseInt(scanner.nextLine());
//                }
//                catch (NumberFormatException e)
//                {
//                    System.out.println("Please enter an Integer");
//                }
//
//            }
//            while (weight > firstWeight) {
//                try {
//                    System.out.println("The input was incorrect, please enter only numbers");
//                    weight = Integer.parseInt(scanner.nextLine());
//                    break;
//                }
//                catch (NumberFormatException e)
//                {
//                    System.out.println("Please enter an Integer");
//                }
//            }
//            if (weight > currTruck.getTotalWeight() - currTruck.getTruckWeight()) {
//                String input = "0";
//                while (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
//                    System.out.println("The truck exceeded the max carry weight, in order to proceed with the shipment\n" +
//                            "please choose one of the options: ");
//                    System.out.println("1. Take out some of the items");
//                    System.out.println("2. Switch to a bigger truck");
//                    if (shipment.getDestinations().size() != 1)
//                        System.out.println("3. remove the last site from this shipment");
//                    input = scanner.nextLine();
//                    switch (input) {
//                        case "1" -> {
//                            if (itemsToDelete(shipment)) {
//                                System.out.println("There is no items left in the shipment, so the shipment is canceled");
//                                shipment.setShipmentStatus(Status.Canceled);
//                                return;
//                            }
//                            shipment.setShipmentStatus(Status.ItemsChange);
//                        }
//                        case "2" -> {
//                            changeTruck(shipment);
//                            if (!Objects.equals(currTruck.getTruckNumber(), shipment.getTruckNumber()))
//                                shipment.setShipmentStatus(Status.TruckExchange);
//                            else {
//                                System.out.println("There isn't a bigger truck available at the moment");
//                            }
//                            currTruck = searchTruckByID(shipment.getTruckNumber());
//                        }
//                        case "3" -> {
//                            removeLastSiteFromShipment(shipment);
//                            shipment.setShipmentStatus(Status.SiteChange);
//                        }
//                    }
//                }
//
//            }
//            else{
//                break;
//            }
//        }
//        shipments.add(shipment);
//        availableShipments.remove(shipment);
//        System.out.println("The items were delivered to the branches!");
//        shipment.printShipment();
//    }

    /**
     * This function deletes a specific shipment from the system, the orders that were in this shipment returns to the
     * map.
     * @param ID String, Shipment ID.
     */
    public void deleteShipment(String ID){
        Shipment shipmentToDelete = null;
        for(Shipment shipment : availableShipments){
            if (shipment.getID().equals(ID)){
                shipmentToDelete = shipment;
                break;
            }
        }
        assert shipmentToDelete != null;
        String source = shipmentToDelete.getSource().getName();
        for(int i = 0; i < shipmentToDelete.getDestinations().size(); i++){
            createOrder(source, shipmentToDelete.getDestinations().get(i).getName());
            Order order = vendorMap.get(source).get(vendorMap.get(source).size() - 1);
            order.addListOfItems(shipmentToDelete.getDocs().get(i).getItemList());
        }
        shipmentToDelete.printShipment();
        availableShipments.remove(shipmentToDelete);
        System.out.println("This shipment has been deleted!");
    }


    /******************* itemsToDelete ******************/

    /**
     * check if the site is in the shipment
     */
    public boolean checkSiteID(String name){
        for(Site site : availableShipments.get(0).getDestinations()){
            if (name.equals(site.getName())){
                return true;
            }
        }
        return false;
    }
    /**
     * this function prints the sites of the shipment that need to be changed
     */
    public void printSiteOfShipment(){
        for(Site site : availableShipments.get(0).getDestinations()){
            site.printSite();
            System.out.println();
        }
    }

    /**
     * this function checks if the truck is full, and can't carry more items.
     * @param weight integer, curr truck weight.
     * @return true/false.
     */
    public boolean checkTruckWeight(int weight){
        if (staticsTruck == null)
            staticsTruck = getTruck(availableShipments.get(0).getTruckNumber());
        return weight >= staticsTruck.getTotalWeight();
    }




    public void loadDrivers(){
//        addDriver("Ron", "000000000", 'C', 0);
//        addDriver("Roee", "000000001", 'D', 1);
//        addDriver("tom", "000000002", 'D', 2);
//        addDriver("omer", "000000003", 'D', 0);
//        addDriver("yuval", "000000004", 'D', 1);
//        addDriver("ido", "000000005", 'C', 2);
//        addDriver("manu", "000000006", 'C', 0);
//        addDriver("matan", "000000007", 'C', 1);
//        addDriver("omri", "000000008", 'D',2);
//        addDriver("freshi", "000000009", 'C', 1);
    }

    public void loadTrucks(){
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


    public void loadSites(){
        addVendor("Osem", "beer sheva", "0547388475", "luffy");
        addVendor("Tnuva", "TLV", "0547388476", "sanji");
        addVendor("Zogloveg", "TLV", "0547388477", "zorro");
        addVendor("Strauss", "kiryat gat", "0547388478", "chopper");
        addVendor("Elit", "haifa", "0547388479", "brook");

        addBranch("branch1", "beer sheva", "0542318475", "jinbi", 2);
        addBranch("branch2", "kiryat gat", "0542318476", "robin", 2);
        addBranch("branch3", "TLV", "0542318477", "garp", 1);
        addBranch("branch4", "givataim", "0542318478", "shanks", 1);
        addBranch("branch5", "katzrin", "0542318479", "kaido", 0);
        addBranch("branch6", "haifa", "0542318470", "oden", 0);
    }


        public void LoadOrder()
        {
            createOrder("Osem", "branch1");
            addItemToOrder("Osem", "ketchup", 10, 0);
            addItemToOrder("Osem", "mayo", 10, 0);
            addItemToOrder("Osem", "spaghetti", 20, 0);
            createOrder("Osem", "branch1");
            addItemToOrder("Osem", "ketchup", 10, 0);
            addItemToOrder("Osem", "apropo", 20, 1);
            createOrder("Osem", "branch2");
            addItemToOrder("Osem", "pene", 10, 0);
            addItemToOrder("Osem", "bamba", 20, 1);
            addItemToOrder("Osem", "peti ber", 10, 0);
            addItemToOrder("Osem", "bisli", 20, 2);
            createOrder("Zogloveg", "branch3");
            addItemToOrder("Zogloveg", "pastrama", 10, 1);
            addItemToOrder("Zogloveg", "hot dog", 20, 2);
            addItemToOrder("Zogloveg", "salami", 10, 1);
            addItemToOrder("Zogloveg", "hamburger", 20, 2);
            createOrder("Zogloveg", "branch4");
            addItemToOrder("Zogloveg", "pastrama", 10, 1);
            addItemToOrder("Zogloveg", "hot dog", 20, 2);
            addItemToOrder("Zogloveg", "salami", 10, 1);
            addItemToOrder("Zogloveg", "hamburger", 20, 2);
            createOrder("Zogloveg", "branch5");
            addItemToOrder("Zogloveg", "pastrama", 10, 1);
            addItemToOrder("Zogloveg", "hot dog", 20, 2);
            addItemToOrder("Zogloveg", "salami", 10, 1);
            addItemToOrder("Zogloveg", "hamburger", 20, 2);
            createOrder("Zogloveg", "branch6");
            addItemToOrder("Zogloveg", "pastrama", 10, 1);
            addItemToOrder("Zogloveg", "hot dog", 20, 2);
            addItemToOrder("Zogloveg", "salami", 10, 1);
            addItemToOrder("Zogloveg", "hamburger", 20, 2);
            createOrder("Elit", "branch3");
            addItemToOrder("Elit", "chocolate", 100, 0);
            addItemToOrder("Elit", "dark chocolate", 10, 0);
            addItemToOrder("Elit", "Egozi", 76, 0);
            addItemToOrder("Elit", "Nutella", 100, 0);
            createOrder("Elit", "branch4");
            addItemToOrder("Elit", "chocolate", 100, 0);
            addItemToOrder("Elit", "dark chocolate", 10, 0);
            addItemToOrder("Elit", "Egozi", 76, 0);
            addItemToOrder("Elit", "Nutella", 100, 0);
            createOrder("Tnuva", "branch5");
            addItemToOrder("Tnuva", "milk", 100, 1);
            addItemToOrder("Tnuva", "white cheese", 100, 1);
            addItemToOrder("Tnuva", "koteg", 100, 1);
            addItemToOrder("Tnuva", "butter", 130, 1);
            createOrder("Tnuva", "branch4");
            addItemToOrder("Tnuva", "milk", 100, 1);
            addItemToOrder("Tnuva", "white cheese", 100, 1);
            addItemToOrder("Tnuva", "koteg", 90, 1);
            addItemToOrder("Tnuva", "butter", 30, 1);
            createOrder("Tnuva", "branch6");
            addItemToOrder("Tnuva", "milk", 100, 1);
            addItemToOrder("Tnuva", "white cheese", 100, 1);
            addItemToOrder("Tnuva", "koteg", 100, 1);
            addItemToOrder("Tnuva", "butter", 100, 1);
            createOrder("Strauss", "branch2");
            addItemToOrder("Strauss", "coffee", 100, 0);
            addItemToOrder("Strauss", "doritos", 60, 0);
            addItemToOrder("Strauss", "chips", 100, 0);
            addItemToOrder("Strauss", "milki", 100, 1);
            createOrder("Strauss", "branch6");
            addItemToOrder("Strauss", "coffee", 100, 0);
            addItemToOrder("Strauss", "doritos", 100, 0);
            addItemToOrder("Strauss", "chips", 60, 0);
            addItemToOrder("Strauss", "milki", 100, 1);
            createOrder("Strauss", "branch4");
            addItemToOrder("Strauss", "coffee", 100, 0);
            addItemToOrder("Strauss", "doritos", 60, 0);
            addItemToOrder("Strauss", "chips", 100, 0);
            addItemToOrder("Strauss", "milki", 60, 1);
            createOrder("Strauss", "branch3");
            addItemToOrder("Strauss", "coffee", 60, 0);
            addItemToOrder("Strauss", "doritos", 100, 0);
            addItemToOrder("Strauss", "chips", 60, 0);
            addItemToOrder("Strauss", "milki", 100, 1);
        }

        public void loadAll(){
            loadDrivers();
            loadTrucks();
            loadSites();
            LoadOrder();
        }

    public boolean checkAvailableShipment() {
        return availableShipments.isEmpty();
    }

    public void printItemsDoc(String siteName){
        for(ItemsDoc itemsDoc : availableShipments.get(0).getDocs()){
            if (itemsDoc.getSiteName().equals(siteName)){
                itemsDoc.printItemsDoc();
            }
        }
    }

    /**
     * This function delete an item from shipment, this function is used when there is a problem loading the items at
     * the vendor.
     * @param itemName String, name of the item to delete.
     * @param amount Int, the amount of the item to delete.
     * @param siteName String, site to delete the item from.
     * @return True if the item was deleted, false if not found.
     */
    public boolean deleteItemFromShipment(String itemName, int amount, String siteName){
        ItemsDoc itmDoc = null;
        for(ItemsDoc itemsDoc : availableShipments.get(0).getDocs()){
            if (itemsDoc.getSiteName().equals(siteName)){
                itmDoc = itemsDoc;
            }
        }
        assert itmDoc != null;
        for(Item item : itmDoc.getItemList()){
            if (Objects.equals(item.getName(), itemName)){
                if (item.getQuantity() >= amount){
                    itmDoc.deleteItem(item);
                    System.out.println("This item was deleted: ");
                    item.printItem();
                }
                else{
                   item.setQuantity(item.getQuantity() - amount);
                   System.out.println("The amount of the item was reduced to " + item.getQuantity());
                }
                shipments.get(0).setShipmentStatus(Status.ItemsChange);
                return true;
            }
        }
        return false;
    }
}


