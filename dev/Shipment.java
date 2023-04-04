import java.util.Date;

public class Shipment {

    private String ID, truckNumber, driverName;
    private Date date, departureTime;
    private Site source;
    private Site[] destinations;
    private ItemsDoc[] docs;
    private Status shipmentStatus;

    public Shipment(String ID, String truckNumber, String driverName, Date date, Site source, Site[] destinations, ItemsDoc[] docs) {
        this.ID = ID;
        this.truckNumber = truckNumber;
        this.driverName = driverName;
        this.date = date;
        this.source = source;
        this.destinations = destinations;
        this.docs = docs;
        this.shipmentStatus = null;
    }



    public String getID() {
        return ID;
    }

    public String getDriverName() {
        return driverName;
    }

    public Date getDate() {
        return date;
    }

    public Site getSource() {
        return source;
    }

    public Status getShipmentStatus() {
        return shipmentStatus;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public void setShipmentStatus(Status shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }


}
