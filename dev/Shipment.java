import java.time.LocalTime;

import java.util.List;

public class Shipment {

    private String ID, truckNumber, driverName;
    private Days dayOfTheWeek;

    private LocalTime departureTime;
    private Site source;
    private List<Site> destinations;
    private List<ItemsDoc> docs;
    private Status shipmentStatus;

    public Shipment(String ID, String truckNumber, String driverName, Days day, Site source, List<Site> destinations, List<ItemsDoc> docs) {
        this.ID = ID;
        this.truckNumber = truckNumber;
        this.driverName = driverName;
        this.dayOfTheWeek = day;
        this.source = source;
        this.destinations = destinations;
        this.docs = docs;
        this.shipmentStatus = Status.NoChanges;
        departureTime = null;
    }

    public void printShipment()
    {
        System.out.println("****** Shipment details ******");
        System.out.println("Shipment ID: " + ID);
        if (departureTime != null) {
            System.out.println("shipment occurred on " + dayOfTheWeek.toString() + " at " + departureTime);
        }
        else{System.out.println("shipment scheduled for this " + dayOfTheWeek.toString());}
        System.out.println("Truck number: " + truckNumber);
        System.out.println("Driver Name: "+ driverName);
        System.out.println("Vendor: "+ source.getName());
        if(shipmentStatus == Status.NoChanges)
            System.out.println("There isn't any changes in this shipment as of this moment");
        else {System.out.println("This shipment has undergone some changes: " + shipmentStatus.toString());}
        System.out.println("Destinations:");
        for(Site site : destinations)
            site.printSite();
        System.out.println("Item doc:");
        for(ItemsDoc itemsDoc : docs)
            itemsDoc.printItemsDoc();

    }


    public String getID() {
        return ID;
    }

    public String getDriverName() {
        return driverName;
    }

    public Days getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public Site getSource() {
        return source;
    }

    public String getTruckNumber(){return truckNumber;}


    public List<Site> getDestinations() {
        return destinations;
    }

    public List<ItemsDoc> getDocs() {
        return docs;
    }

    public Status getShipmentStatus() {
        return shipmentStatus;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setShipmentStatus(Status shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public void deleteItemDoc(ItemsDoc itemsDoc){
        docs.remove(itemsDoc);
    }
    public boolean checkItemDocEmpty(){
        return docs.isEmpty();
    }
    public void removeSite(Site site){
        destinations.remove(site);
    }

    public void setTruckNumber(String number)
    {
        this.truckNumber = number;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}
