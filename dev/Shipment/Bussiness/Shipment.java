package Shipment.Bussiness;


import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;

public class Shipment {

    private String ID, truckNumber;

    private LocalDate date;
    private Driver driver;
    private Days dayOfTheWeek;

    private LocalTime departureTime;
    private Site source;
    private List<Site> destinations;
    private List<ItemsDoc> docs;
    private Status shipmentStatus;

    public Shipment(String ID, String truckNumber, Days day, Site source, List<Site> destinations, List <ItemsDoc> docs, LocalDate date){
        this.ID = ID;
        this.truckNumber = truckNumber;
        this.dayOfTheWeek = day;
        this.source = source;
        this.driver = null;
        this.destinations = destinations;
        this.docs = docs;
        this.shipmentStatus = Status.NoChanges;
        this.date = date;
        departureTime = null;

    }
    public Shipment(String ID, String truckNumber, Driver driver, Days day, Site source, List<Site> destinations, List<ItemsDoc> docs, LocalDate date) {
        this.ID = ID;
        this.truckNumber = truckNumber;
        this.driver = driver;
        this.dayOfTheWeek = day;
        this.source = source;
        this.destinations = destinations;
        this.docs = docs;
        this.shipmentStatus = Status.NoChanges;
        this.date = date;
        departureTime = null;
    }

    public void printShipment()
    {
        System.out.println("****** Shipment details ******");
        System.out.println("Shipment ID: " + ID);
        System.out.println("Date: " + date);
        if (departureTime != null) {
            System.out.println("shipment occurred on " + dayOfTheWeek.toString() + " at " + departureTime);
        }
        else{System.out.println("shipment scheduled for this " + dayOfTheWeek.toString());}
        System.out.println("Truck number: " + truckNumber);
        if (driver != null) {
            System.out.println("Driver Name: " + driver.getName());
        }
        else{System.out.println("No driver at the moment");}
        System.out.println("Vendor: "+ source.getName());
        System.out.print("Status: ");
        if(shipmentStatus == Status.Available)
            System.out.println("The shipment is ready to be executed");
        else if (shipmentStatus == Status.NoChanges) {
            System.out.println("There isn't any changes in this shipment as of this moment");
        }
        else {System.out.println("This shipment has undergone some changes: " + shipmentStatus.toString());}
        System.out.println("Destinations:");
        for(Site site : destinations)
            site.printSite();
        System.out.println("Item doc:");
        for(ItemsDoc itemsDoc : docs)
            itemsDoc.printItemsDoc();

    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("****** Shipment details ******\n");
        sb.append("Shipment ID: ").append(ID).append("\n");
        sb.append("Date: ").append(date).append("\n");
        if (departureTime != null) {
            sb.append("Shipment occurred on ").append(dayOfTheWeek.toString()).append(" at ").append(departureTime).append("\n");
        } else {
            sb.append("Shipment scheduled for this ").append(dayOfTheWeek.toString()).append("\n");
        }
        sb.append("Truck number: ").append(truckNumber).append("\n");
        if (driver != null) {
            sb.append("Driver Name: ").append(driver.getName()).append("\n");
        } else {
            sb.append("No driver at the moment").append("\n");
        }
        sb.append("Vendor: ").append(source.getName()).append("\n");
        sb.append("Status: ");
        if (shipmentStatus == Status.Available) {
            sb.append("The shipment is ready to be executed").append("\n");
        } else if (shipmentStatus == Status.NoChanges) {
            sb.append("There isn't any changes in this shipment as of this moment").append("\n");
        } else {
            sb.append("This shipment has undergone some changes: ").append(shipmentStatus.toString()).append("\n");
        }
        sb.append("Destinations:\n");
        for (Site site : destinations) {
            sb.append(site.toString()).append("\n");
        }
        sb.append("Item doc:\n");
        for (ItemsDoc itemsDoc : docs) {
            sb.append(itemsDoc.toString()).append("\n");
        }
        return sb.toString();
    }


    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public String getID() {
        return ID;
    }

    public Driver getDriver() {
        return driver;
    }

    public Days getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public Site getSource() {
        return source;
    }

    public String getTruckNumber(){return truckNumber;}

    public ItemsDoc getItemDoc(String des)
    {
        for(ItemsDoc itemsDoc: docs)
        {
            if(itemsDoc.getSiteName().equals(des))
                return itemsDoc;
        }
        return null;
    }

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

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public LocalDate getDate(){return date;}
}
