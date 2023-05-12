package Shipment.Bussiness;

import HR.Service.ShipmentService;
import Shipment.DataAccess.DataController;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// todo will be singleton in the end
public class ServiceController {
    private static  ServiceController instance;
    private DataController dataController;
    private Map<String,Shipment> shipments;
    private Map<String,Order> orders;
    private Map<String,Truck> trucks;


    public static ServiceController getInstance() {
        if (instance == null)
            instance = new ServiceController();
        return instance;
    }
    private ServiceController(){
        dataController = DataController.getInstance();
        shipments = dataController.getAvailableShipments();
        orders = dataController.getOrderMap();
        trucks = dataController.getTrucksMap();
    }

    /**
     * prints all shipments that leave on the given day and contains the destination given
     * @param day day of the week
     * @param siteName destination name
     */
    public void printShipmentsDS(Days day, String siteName)
    {
        for(Shipment shipment : shipments.values())
        {
            if(shipment.getDayOfTheWeek() == day)
            {
                for(Site site: shipment.getDestinations())
                {
                    if(site.getName().equals(siteName))
                    {
                        shipment.printShipment();
                        return;
                    }
                }

            }
        }
    }

    /**
     * deletes all shipments that leave on the given day and contains the destination given
     * @param day day of the week
     * @param siteName destination name
     */
    public void deleteShipmentsDS(Days day, String siteName)
    {
        Iterator<Shipment> iterator = shipments.values().iterator();

        while (iterator.hasNext())
        {
            Shipment shipment = iterator.next();
            if (shipment.getDayOfTheWeek() == day)
            {
                for(Site site: shipment.getDestinations())// check if to replace with iterator
                {
                    if(site.getName().equals(siteName))
                    {
                        iterator.remove();
                        break;
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
    public  boolean checkShipment(Days day, String siteName)
    {
        for(Shipment shipment : shipments.values())
        {
            if(shipment.getDayOfTheWeek() == day)
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

    //prints all available shipments
    public void printAllShipments()
    {
        for(Shipment shipment : shipments.values())
            shipment.printShipment();
    }

    //this function find drivers for all shipments without a driver if there is an available driver
    public void findDriversForShipment()
    {
        ShipmentService shipmentService = ShipmentService.getInstance();
        List<String> driverDetails;
        List<String> branchesNames = null;
        for(Shipment shipment:shipments.values())
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
}
