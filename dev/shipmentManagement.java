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
        loadDrivers();
        loadTrucks();
        loadSites();
        LoadOrder();
        TestShipment();
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
    public void addDriver(String name, String ID, char license, int train) {
        Driver driver = new Driver(name, ID, license, Training.values()[train]);
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
                return;
            }
        }
    }

    /**
     * This function search for a driver that can do a shipment.
     *
     * @param train enum, his ability to driver truck.
     * @param day   int represent the day of the week.
     * @return the available driver.
     */
    public Driver searchForDriver(Training train, int day) {
        for (Driver driver : drivers) {
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
                return;
            }
        }
    }

    /**
     * This function search for a suitable truck and returns its truck number.
     *
     * @param train Enum, they type of the truck.
     * @param day   int, represent the day of the week.
     * @return string, the truck Number.
     */
    public String searchForTruck(Training train, int day) {
        for (Truck truck : trucks) {
            if (truck.addNewDay(Days.values()[day])) {
                if (((truck instanceof FreezerTruck) && (train == Training.Freezer))
                        || ((truck instanceof CoolingTruck) && (train == Training.Cooling))
                        || ((truck instanceof RegularTruck) && (train == Training.Regular)))
                    return truck.getTruckNumber();
            }
        }
        return "";
    }


    public Truck searchTruckByID(String truckNumber) {
        for (Truck truck : trucks) {
            if (truck.getTruckNumber().equals(truckNumber)) {
                return truck;
            }
        }
        return null;
    }

    /**
     * This function prints every truck in the system.
     */
    public void printTrucks() {
        System.out.println("******* Trucks details *******");
        System.out.println("Number of truck: " + trucks.size() + "\n");
        for (Truck truck : trucks) {
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
        return vendorMap.containsKey(name);
    }

    /**
     * This function checks if a branch is already exist in the system.
     *
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

    private void removeLastSiteFromShipment(Shipment shipment) {
        String source = shipment.getSource().getName();
        Order order;
        for (ItemsDoc doc : shipment.getDocs()) {
            if (Objects.equals(doc.getSiteName(), shipment.getDestinations().get(shipment.getDestinations().size() - 1).getName())) {
                createOrder(source, doc.getSiteName());
                for (Item item : doc.getItemList()) {
                    order = vendorMap.get(source).get(vendorMap.get(source).size() - 1);
                    order.addItemToOrder(item);
                }
                shipment.getDocs().remove(doc);
            }
        }
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
        Order order = new Order(destination, zone);
        vendorMap.get(source).add(order);
    }

    public void printOrders() {
        for (Map.Entry<String, List<Order>> entry : vendorMap.entrySet()){
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
        for (Shipment shipment : shipments){
            shipment.printShipment();
        }
    }

    /**
     * This function prints every shipment that is ready to be executed.
     */
    public void printAvailableShipments(){
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
        for (Shipment ship : shipments) {
            if (Objects.equals(ship.getID(), ID)) {
                return true;
            }
        }
        return false;
    }


    private void addShipmentSorted(Shipment shipment){
        if (availableShipments.isEmpty()){
            availableShipments.add(shipment);
            return;
        }
        for (int i=0; i < availableShipments.size(); i++){
            if (shipment.getDate().compareTo(availableShipments.get(i).getDate()) <= 0){
                availableShipments.add(i, shipment);
                return;
            }
        }
        availableShipments.add(shipment);
    }

    /**
     * This function creates a new shipment, first the function checks if the vendor has orders, if an order was found,
     * the function will go over the orders and combine those with same delivery zones and creating a new shipments.
     * the function finds a matching driver truck pair, the type of the truck is decided by the type of the first
     * item that was checked.
     *
     * @param dayOfWeek int representing the date. //todo maybe change later ****important****
     * @param ID        string, the ID of the shipment.
     * @param source    string, Vendor.
     * @return True/false if the shipment was created.
     */
    public boolean createShipment(int dayOfWeek, String ID, String source) {
        Date date = new Date();
        ItemsDoc itemsDoc;
        Shipment shipment;
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

        // finding driver and truck
        Driver driverForShipment = searchForDriver(trainToSearchBy, dayOfWeek);
        String truckNumberForShipment = searchForTruck(trainToSearchBy, dayOfWeek);

        // creating the first item doc and adding it to the list of items.
        itemsDoc = new ItemsDoc(ID, firstOrder.getDestination());
        itemsDoc.addListOfItems(firstOrder.getItemsForShipping(trainToSearchBy));
        itemsDocList.add(itemsDoc);

        // in case there is only one order from the specific vendor
        if (vendorMap.get(source).size() == 1) {
            shipment = new Shipment(ID, truckNumberForShipment, driverForShipment.getName(), date, vendor, branchList, itemsDocList);
            addShipmentSorted(shipment);
            if (firstOrder.checkIfEmpty())
                vendorMap.get(source).remove(firstOrder);
            return true;
        } else {
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
                            if (firstOrder.checkIfEmpty())
                                vendorMap.get(source).remove(firstOrder);
                            found = true;
                            break;
                        }
                    }
                    // checking if the for above was executed to the fullest.
                    if (found)
                        continue;
                    // in case of a new site.
                    branchList.add(getSite(order.getDestination()));
                    itemsDoc = new ItemsDoc(ID, order.getDestination());
                    itemsDoc.addListOfItems(order.getItemsForShipping(trainToSearchBy));
                    itemsDocList.add(itemsDoc);
                    if (firstOrder.checkIfEmpty())
                        vendorMap.get(source).remove(firstOrder);
                }
            }
            shipment = new Shipment(ID, truckNumberForShipment, driverForShipment.getName(), date, vendor, branchList, itemsDocList);
            addShipmentSorted(shipment);
            return true;
        }
    }

    /**
     * This function executes the shipment with the closest date.
     */
    public void executeShipment() {
        if (availableShipments.isEmpty()) {
            System.out.println("There is no available shipment!");
            return;
        }
        Shipment shipment = availableShipments.get(0);
        System.out.println("U have arrived to ur destination: ");
        shipment.getSource().printSite();
        Truck currTruck = searchTruckByID(shipment.getTruckNumber());
        Scanner scanner = new Scanner(System.in);
        String input = "";
        System.out.println("Please enter the weight of the truck with the items (in KG): ");
        int weight;
        weight = scanner.nextInt();
        int firstWeight = weight;
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.println("The input was incorrect, please enter only numbers");
                weight = scanner.nextInt();
                System.out.println("The input was incorrect, please enter only numbers");

            }
            if (weight > currTruck.getTotalWeight()) {
                while (!Objects.equals(input, "1") && !Objects.equals(input, "2") && !Objects.equals(input, "3")) {
                    System.out.println("The truck exceeded the max carry weight, in order to proceed with the shipment\n" +
                            "please choose one of the options: ");
                    System.out.println("1. Take out some of the items");
                    System.out.println("2. Switch to a bigger truck");
                    if (shipment.getDestinations().size() != 1)
                        System.out.println("3. remove the last site from this shipment");
                    input = scanner.nextLine();
                    switch (input) {

                        case "1":
                            if (itemsToDelete(shipment)){
                                System.out.println("There is no items left in the shipment, so the shipment is canceled");
                                shipment.setShipmentStatus(Status.Canceled);
                                return;
                            }
                            shipment.setShipmentStatus(Status.ItemsChange);
                            break;

                        case "2":
                            changeTruck(shipment);
                            shipment.setShipmentStatus(Status.TruckExchange);
                            break;

                        case "3":
                            removeLastSiteFromShipment(shipment);
                            shipment.setShipmentStatus(Status.SiteChange);
                            break;
                    }
                }

            }
            else{
                break;
            }
        }
        shipments.add(availableShipments.get(0));
    }

    private void changeTruck(Shipment shipment) {
        Truck currentTruck = getTruck(shipment.getTruckNumber());
        String currentDriverName = shipment.getDriverName();;
        for(Truck truck : trucks)
        {
            if(currentTruck.getTotalWeight() < truck.getTotalWeight())
            {
                if(truck.checkDay(shipment.getDate().getDay())) //check about getDay function
                {
                    if (((truck instanceof FreezerTruck) && (currentTruck instanceof FreezerTruck))
                            || ((truck instanceof CoolingTruck) && (currentTruck instanceof CoolingTruck))
                            || ((truck instanceof RegularTruck) && (currentTruck instanceof RegularTruck)))
                    {
                        shipment.setDriverName(changeDriver(shipment.getDriverName(), truck,shipment.getDate().getDay()));
                        if(shipment.getDriverName() == null)
                            shipment.setDriverName(currentDriverName);
                        else
                        {
                            shipment.setTruckNumber(truck.getTruckNumber());
                            truck.addNewDay(Days.values()[shipment.getDate().getDay()]);
                            currentTruck.removeDay(Days.values()[shipment.getDate().getDay()]);
                            System.out.println("Truck Changed");
                        }
                    }
                }

            }
        }
    }

    public String changeDriver(String driverName, Truck truck,int day)
    {
        Days days = Days.values()[day];
        Driver driver = getDriver(driverName);
        if(driver.getAbility().ordinal() >= truck.getStorageType().ordinal())
        {
            if(driver.getLicense() == 'd')
                return driverName;
            else if (driver.getLicense() == 'c' && truck.getTotalWeight() <= 12000) {
                return driverName;
            }
        }
        else
        {
            for (Driver driver1 : drivers)
            {
                if(driver.checkDay(days)) {
                    if (driver1.getAbility().ordinal() >= truck.getStorageType().ordinal()) {
                        if (driver1.getLicense() == 'd') {
                            driver1.addNewDay(days);
                            driver.removeDay(days);
                            return driver1.getName();
                        } else if (driver.getLicense() == 'c' && truck.getTotalWeight() <= 12000) {
                            driver1.addNewDay(days);
                            driver.removeDay(days);
                            return driver1.getName();
                        }
                    }
                }
            }
        }
        return null;
    }
    public Truck getTruck(String truckNumber)
    {
        for(Truck truck : trucks)
        {
            if(truck.getTruckNumber().equals(truckNumber))
                return truck;
        }
        return null;
    }
    public Driver getDriver(String driverName)
    {
        for (Driver driver : drivers)
        {
            if(driver.getName().equals(driverName))
                return driver;
        }
        return null;
    }

    private boolean itemsToDelete(Shipment shipment) {
        System.out.println("Those are the branches of the shipments: ");
        for (Site site : shipment.getDestinations()){
            site.printSite();
            System.out.println();
        }
        // choose site to delete item from.
        System.out.println("Please choose the one you want to delete the item from, enter the name of the site.");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        boolean found = false;
        while(!found) {
            for (Site site : shipment.getDestinations()){
                if (Objects.equals(answer, site.getName())) { // the site is in the shipment.
                    found = true;
                    break;
                }
            }
            if (found)
                continue;
            System.out.println("This site is not exist, please enter again");
            answer = scanner.nextLine();
        }
        ItemsDoc itemsD = null;
        // finds the item document that goes the site.
        for (ItemsDoc itemsDoc : shipment.getDocs()){
            if (Objects.equals(itemsDoc.getSiteName(), answer)){
                itemsDoc.printItemsDoc();
                itemsD = itemsDoc;
            }
        }
        System.out.println("Please choose an item you want to delete.");
        answer = scanner.nextLine();
        while(true){
            if (itemsD != null) {
                // finding the item and change it.
                for(Item item : itemsD.getItemList()){
                    if (Objects.equals(item.getName(), answer)){
                        System.out.println("Please enter the amount: ");
                        int amount = scanner.nextInt();
                        while(!scanner.hasNextInt()){
                            System.out.println("Please enter the amount: ");
                            amount = scanner.nextInt();
                        }
                        // if the input was higher, then delete the item.
                        if (amount > item.getQuantity()){
                            itemsD.deleteItem(item);
                            if (itemsD.isEmpty()){ // if it was the las item of the doc.
                                shipment.deleteItemDoc(itemsD);
                                shipment.removeSite(getSite(itemsD.getSiteName()));
                                return shipment.checkItemDocEmpty();  // in case it was the last doc in the shipment, then return true.
                            }
                        }
                        else{
                            item.setQuantity(amount);
                        }
                        return false;
                    }
                }
                System.out.println("This item does not exist, Please choose an item you want to delete.");
                answer = scanner.nextLine();
            }
        }
    }

    public void loadDrivers(){
        addDriver("Ron", "000000000", 'C', 0);
        addDriver("Roee", "000000001", 'D', 1);
        addDriver("tom", "000000002", 'D', 2);
        addDriver("omer", "000000003", 'D', 0);
        addDriver("yuval", "000000004", 'D', 1);
        addDriver("ido", "000000005", 'C', 2);
        addDriver("manu", "000000006", 'C', 0);
        addDriver("matan", "000000007", 'C', 1);
        addDriver("omri", "000000008", 'D',2);
        addDriver("freshi", "000000009", 'C', 1);
    }

    public void loadTrucks(){
        addTruck("0000", 15000, 2000, "mercedes", 0);
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
        addVendor("tnuva", "TLV", "0547388476", "sanji");
        addVendor("teva", "TLV", "0547388477", "zorro");
        addVendor("shtraus", "kiryat gat", "0547388478", "chopper");
        addVendor("elit", "haifa", "0547388479", "brook");
        addVendor("shufersal", "givataim", "0547388123", "nami");


        addBranch("snif1", "beer sheva", "0542318475", "jinbi", 2);
        addBranch("snif2", "kiryat gat", "0542318476", "robin", 2);
        addBranch("snif3", "TLV", "0542318477", "garp", 1);
        addBranch("snif4", "givataim", "0542318478", "shanks", 1);
        addBranch("snif5", "katzrin", "0542318479", "kaido", 0);
        addBranch("snif6", "haifa", "0542318470", "oden", 0);
    }


        public void LoadOrder()
        {
            createOrder("Osem", "snif1");
            addItemToOrder("Osem", "ketchup", 10, 0);
            addItemToOrder("Osem", "mayo", 10, 0);
            addItemToOrder("Osem", "x", 20, 0);
            createOrder("Osem", "snif1");
            addItemToOrder("Osem", "ketchup", 10, 0);
            addItemToOrder("Osem", "y", 20, 1);
            printOrders();
        }


        public void TestShipment(){
        createShipment(1, "123123", "Osem");
        executeShipment();
        }

}


