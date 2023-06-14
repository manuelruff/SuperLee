package Shipment.Bussiness;

import HR.Service.ShipmentService;
import Shipment.DataAccess.DataController;

import java.time.LocalDate;
import java.util.*;

public class ServiceController {
    private static  ServiceController instance;
    private DataController dataController;
    private shipmentManagement shipmentM;
    private List<Shipment> shipments;
    private Map<String,Order> orders;
    private Map<String,Truck> trucks;
    private final Map<String, Vendor> vendors;
    private ShipmentService shipmentService;


    public static ServiceController getInstance() {
        if (instance == null)
            instance = new ServiceController();
        return instance;
    }
    private ServiceController(){
        shipmentM = shipmentManagement.getInstance();
        dataController = DataController.getInstance();
        shipments = shipmentM.getAvailableShipment();
        orders = dataController.getOrderMap();
        trucks = dataController.getTrucksMap();
        vendors = shipmentM.getVendors();
        shipmentService = ShipmentService.getInstance();
    }

    /**
     * prints all shipments that leave on the given day and contains the destination given
     * @param day day of the week
     * @param siteName destination name
     */
    public void printShipmentsDS(LocalDate day, String siteName)
    {
        for(Shipment shipment : shipments )
        {
            if(shipment.getDate() == day)
            {
                for(Site site: shipment.getDestinations())
                {
                    if(site.getName().equals(siteName))
                    {
                        shipment.printShipment();
                        break;
                    }
                }

            }
        }
        System.out.println("there is no shipments in this day: " + day + " for this site: " + siteName);
    }

    /**
     * deletes all shipments that leave on the given day and contains the destination given
     * @param day day of the week
     * @param siteName destination name
     */
    public void deleteShipmentsDS(LocalDate day, String siteName)
    {
        for(Shipment shipment : shipments){
            if (shipment.getDate() == day){
                if (shipment.getDestinations().size() == 1){
                    if (shipment.getDestinations().get(0).getName().equals(siteName)){
                        shipmentM.deleteShipment(shipment.getID());
                        return;
                    }
                }
                for(Site site: shipment.getDestinations()){
                    if (site.getName().equals(siteName)){
                        shipment.getDestinations().remove(site);
                        ItemsDoc itemsDoc = shipment.getItemDoc(siteName);
                        dataController.deleteItemDoc(shipment.getID(),siteName);
                        shipmentM.turnItemDocIntoOrder(itemsDoc,shipment.getSource().getName());
                        shipment.deleteItemDoc(itemsDoc);
                        return;
                    }
                }
            }
        }
    }

    /**
     * checks if there is a shipment in the given day and a destination like the one given
     * @param day day of the week
     * @param siteName a branch name
     * @return yes if found, false otherwise
     */
    public boolean checkShipment(LocalDate day, String siteName)
    {
        for(Shipment shipment : shipments)
        {
            if(shipment.getDate() == day)
            {
                for(Site site: shipment.getDestinations())
                {
                    if(site.getName().equals(siteName))
                    {
                        return true;
                    }
                }

            }
        }
        return false;
    }
   public List<String[]> getSiteData()
   {
       List<String[]> SitesD = new ArrayList<>();
       dataController.loadAllVendors();
       for(Vendor vendor: vendors.values())
       {
           String[] data = new String[5];
           data[0] = vendor.getName();
           data[1] = vendor.getAddress();
           data[2] = vendor.getPhoneNumber();
           data[3] = vendor.getContactName();
           SitesD.add(data);
       }
       for(List<String> site : shipmentService.getAllSites())
       {
           String[] data = new String[5];
           data[0] = site.get(0);
           data[1] = site.get(1);
           data[2] = site.get(2);
           data[3] = site.get(3);
           data[4] = site.get(4);
           SitesD.add(data);
       }
       return SitesD;
   }
   public List<List<String>> getDriversData()
   {
       return shipmentService.getDriversInfo();
   }
   public List<String[]> getOrdersData()
   {
       List<String[]> ordersD = new ArrayList<>();
       dataController.loadAllOrders();
       for(Order order: orders.values())
       {
           String[] data = new String[4];
           data[0] = order.getID();
           data[1] = order.getSource();
           data[2] = order.getDestination();
           data[3] = order.getZone().toString();
           ordersD.add(data);
       }
       return ordersD;
   }
    public List<String[]> getOrdersItemsData()
    {
        List<String[]> ItemsD = new ArrayList<>();
        dataController.loadAllOrders();
        for(Order order: orders.values())
        {
            String[] data = new String[4];
            data[0] = order.getID();
            for(Item item: order.getItemList()) {
                data[1] = item.getName();
                data[2] = Integer.toString(item.getQuantity());
                data[3] = item.getStorageCondition().toString();

            }
            ItemsD.add(data);
        }
        return ItemsD;
    }
    public List<String[]> getTrucksData()
    {
        List<String[]> trucksD = new ArrayList<>();

        dataController.loadAllTrucks();
        for(Truck truck: trucks.values())
        {
            String[] data = new String[5];
            data[0] = truck.getTruckNumber();
            data[1] = truck.getModel();
            data[2] = Integer.toString(truck.getTruckWeight());
            data[3] = Integer.toString(truck.getTotalWeight());
            data[4] = truck.getStorageType().toString();
            trucksD.add(data);
        }
        return trucksD;
    }



    //prints all available shipments
    public void printAllShipments()
    {
        for(Shipment shipment : shipments)
            shipment.printShipment();
    }

    //this function find drivers for all shipments without a driver if there is an available driver
    public void findDriversForShipment()
    {

        ShipmentService shipmentService = ShipmentService.getInstance();
        List<String> driverDetails;
        List<String> branchesNames = new ArrayList<>();
        for(Shipment shipment:shipments)
        {
            if (shipment.getDriver() == null)
            {
                for (Site site : shipment.getDestinations())
                    branchesNames.add(site.getName());
                char licence = trucks.get(shipment.getTruckNumber()).getLicenceType();
                int training = trucks.get(shipment.getTruckNumber()).getStorageType().ordinal();
                int day = shipment.getDayOfTheWeek().ordinal();
                driverDetails = shipmentService.askForDriver(licence, training, day, branchesNames);
                if (driverDetails != null) {
                    Driver driver = new Driver(driverDetails.get(0), driverDetails.get(1),
                            driverDetails.get(2).charAt(0), Training.valueOf(driverDetails.get(3)));
                    shipment.setDriver(driver);
                }
            }
        }
    }
    // this function deletes all orders that their destination is the site deleted
    public void deleteOrdersBySite(String siteName)
    {
        Iterator<Order> iterator = orders.values().iterator();

        while (iterator.hasNext())
        {
            Order order = iterator.next();
            if(Objects.equals(order.getDestination(), siteName))
                iterator.remove();
        }
    }

    public String[] getSitesNames(){
        Shipment shipment = shipments.get(0);
        String[] ret = new String[shipment.getDestinations().size()];
        for(int i=0; i < shipment.getDestinations().size(); i ++){
            ret[i] = shipment.getDestinations().get(i).getName();
        }
        return ret;
    }
    public List<String> getItemsFromDoc(String siteName){
        Shipment shipment = shipments.get(0);
        List<String> ret = new ArrayList<>();
        for(Item item : shipment.getItemDoc(siteName).getItemList()){
            ret.add(item.getName());
            ret.add(String.valueOf(item.getQuantity()));
        }
        return ret;
    }
}
